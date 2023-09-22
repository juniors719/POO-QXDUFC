package bilheteria;

public class Evento {

    private String nome;
    private double preco;

    public Evento(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        String saida = "";
        saida += this.nome;
        saida += ":";
        saida += String.format("%.2f", this.preco);
        return saida;
    }

}
