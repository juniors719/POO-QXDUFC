package bilheteria2;

import java.util.HashMap;

public class Evento {

    private String nome;
    private HashMap<String, Setor> setores;

    public Evento(String nome) {
        this.nome = nome;
        this.setores = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public Setor[] getSetores() {
        if (this.setores.size() == 0) {
            return null;
        }
        Setor[] setores = new Setor[this.setores.size()];
        int i = 0;
        for (Setor setor : this.setores.values()) {
            setores[i++] = setor;
        }
        return setores;
    }

    public void addSetor(String nome, double preco) {
        if (this.setores.containsKey(nome)) {
            throw new RuntimeException("fail: setor ja existe");
        }
        Setor setor = new Setor(this.nome, nome, preco);
        this.setores.put(nome, setor);
    }

    public Setor getSetor(String nome) {
        if (this.setores.containsKey(nome)) {
            return this.setores.get(nome);
        }
        throw new RuntimeException("fail: setor " + nome + " nao existe");
    }

    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();
        saida.append(this.nome);
        saida.append(" [");
        Setor[] setores = getSetores();
        if (setores != null) {
            for (int i = 0; i < setores.length; i++) {
                saida.append(setores[i]);
                if (i < setores.length - 1) {
                    saida.append(", ");
                }
            }
        }
        saida.append("]");
        return saida.toString();
    }

}
