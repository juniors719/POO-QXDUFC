package estudo_prova.budega;

import java.util.*;

class Pessoa {

    // atributos:
    private String nome;

    // construtora
    public Pessoa(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

class Mercantil {

    // ArrayList -> preciso usar os índices
    // LinkedList -> não importa tanto (último e primeiro)

    // atributos:
    private ArrayList<Pessoa> caixas;
    private LinkedList<Pessoa> esperando;

    private boolean validarIndice(int indice) {
        if (indice < 0 || indice >= caixas.size()) {
            return false;
        }
        return true;
    }

    // construtor:
    public Mercantil(int qtdCaixas) {
        // INICIALIZA AS LISTAS
        caixas = new ArrayList<Pessoa>();
        esperando = new LinkedList<Pessoa>();
        for (int i = 0; i < qtdCaixas; i++) {
            caixas.add(null);
        }
    }

    @Override
    public String toString() {
        // Caixas: [-----, -----]
        // Espera: []

        String saida = "Caixas: [";
        for (int i = 0; i < caixas.size(); i++) {
            if (caixas.get(i) == null) {
                saida += "-----";
            } else {
                saida += caixas.get(i).getNome();
            }
            if (i < caixas.size() - 1) {
                saida += ", ";
            }
        }
        saida += "]\nEspera: [";
        for (int i = 0; i < esperando.size(); i++) {
            saida += esperando.get(i).getNome();
            if (i < esperando.size() - 1) {
                saida += ", ";
            }
        }
        saida += "]";
        return saida;
    }

    public void chegar(Pessoa pessoa) {
        esperando.addLast(pessoa);
    }

    public boolean chamar(int indice) {
        // TRATAR OS ERROS
        if (!validarIndice(indice)) {
            System.out.println("fail: caixa inexistente");
            return false;
        }
        if (esperando.isEmpty()) {
            System.out.println("fail: sem clientes");
            return false;
        }
        if (caixas.get(indice) != null) { // caixas[i]
            System.out.println("fail: caixa ocupado");
            return false;
        }

        caixas.set(indice, esperando.removeFirst());
        return true;
    }

    public Pessoa finalizar(int indice) {
        // TRATAR OS ERROS
        if (!validarIndice(indice)) {
            System.out.println("fail: caixa inexistente");
            return null;
        }

        if (caixas.get(indice) == null) {
            System.out.println("fail: caixa vazio");
            return null;
        }

        Pessoa clienteFinalizado = caixas.get(indice);
        caixas.set(indice, null);
        return clienteFinalizado;
    }

}

public class Solver {
    public static void main(String[] args) {
        Scanner batata = new Scanner(System.in);
        Mercantil merc = new Mercantil(0);
        while (true) {
            String line = batata.nextLine(); // le a linha
            System.out.println("$" + line); // imprime com o cifrão
            String[] a = line.split(" "); // fatiar a String no espaço

            if (a[0].equals("end")) {
                break;
            } else if (a[0].equals("init")) {
                merc = new Mercantil(Integer.parseInt(a[1]));
            } else if (a[0].equals("show")) {
                System.out.println(merc);
            } else if (a[0].equals("arrive")) {
                merc.chegar(new Pessoa(a[1]));
            } else if (a[0].equals("call")) {
                merc.chamar(Integer.parseInt(a[1]));
            } else if (a[0].equals("finish")) {
                merc.finalizar(Integer.parseInt(a[1]));
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        batata.close();
    }
}
