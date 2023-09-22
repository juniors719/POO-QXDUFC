package bilheteria2;

public class Setor {

    private String evento;
    private String nome;
    private double preco;

    public Setor(String evento, String nome, double preco) {
        this.evento = evento;
        this.nome = nome;
        this.preco = preco;
    }

    public String getEvento() {
        return evento;
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
