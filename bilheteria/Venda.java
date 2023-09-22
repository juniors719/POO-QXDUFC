package bilheteria;

public class Venda {

    private Pessoa pessoa;
    private Evento evento;
    private double valor;

    public Venda(Pessoa pessoa, Evento evento) {
        if (pessoa.isMeia())
            this.valor = evento.getPreco() / 2;
        else
            this.valor = evento.getPreco();
        this.evento = evento;
        this.pessoa = pessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Evento getEvento() {
        return evento;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        String saida = "";
        saida += this.pessoa.getName();
        saida += ":";
        saida += this.evento.getNome();
        saida += ":";
        saida += String.format("%.2f", this.valor);
        return saida;
    }

}
