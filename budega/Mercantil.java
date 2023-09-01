package budega;

import java.util.*;
import java.util.stream.Collectors;

public class Mercantil {

    private ArrayList<Pessoa> caixas;
    private LinkedList<Pessoa> esperando = null;

    private boolean validarIndice(int indice) {
        if (indice < this.caixas.size())
            return true;
        return false;
    }

    public Mercantil(int quantCaixas) {
        if (quantCaixas > 0) {
            this.caixas = new ArrayList<>(quantCaixas);
            for (int i = 0; i < quantCaixas; i++)
                this.caixas.add(i, null);
        } else
            this.caixas = null;
        this.esperando = new LinkedList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Caixas: [");
        if (caixas != null)
            sb.append(caixas.stream().map(c -> c == null ? "-----" : c.getNome()).collect(Collectors.joining(", ")));
        sb.append("]\nEspera: [");
        if (esperando != null)
            sb.append(esperando.stream().map(c -> c.getNome()).collect(Collectors.joining(", ")));
        sb.append("]");
        return sb.toString();
    }

    public void chegar(Pessoa pessoa) {
        this.esperando.addLast(pessoa);
    }

    public boolean chamar(int indice) {
        if (validarIndice(indice)) {
            if (caixas.get(indice) == null) {
                if (!esperando.isEmpty()) {
                    caixas.set(indice, esperando.getFirst());
                    esperando.removeFirst();
                } else {
                    System.out.println("fail: sem clientes");
                }
            } else {
                System.out.println("fail: caixa ocupado");
            }
        } else {
            System.out.println("fail: caixa inexistente");
        }
        return false;
    }

    public Pessoa finalizar(int indice) {
        if (validarIndice(indice)) {
            if (caixas.get(indice) != null) {
                Pessoa clienteFinalizado = caixas.get(indice);
                caixas.set(indice, null);
                return clienteFinalizado;
            } else {
                System.out.println("fail: caixa vazio");
            }
        } else {
            System.out.println("fail: caixa inexistente");
        }
        return null;
    }

    public boolean furarFila(Pessoa furao, String besta) {
        int posicaoBesta = -1;
        for (int i = 0; i < esperando.size(); i++) {
            if (besta.equals(furao.getNome()))
                posicaoBesta = i;
        }
        if (posicaoBesta >= 0) {
            esperando.add(posicaoBesta, furao);
            return true;
        }
        return false;
    }

    public boolean desistir(String desistente) {
        int posicaoDesistente = -1;
        for (int i = 0; i < esperando.size(); i++) {
            if (esperando.get(i).getNome().equals(desistente)) {
                posicaoDesistente = i;
            }
            if (posicaoDesistente >= 0) {
                esperando.remove(posicaoDesistente);
                return true;
            }
        }
        return false;
    }

    public boolean revoltar(String nome) {
        // todo
        return false;
    }
}
