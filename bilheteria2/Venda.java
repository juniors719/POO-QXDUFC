package bilheteria2;

public class Venda {

    private Pessoa pessoa;
    private Setor setor;
    private double valor;

    public Venda(Pessoa pessoa, Evento evento, Setor setor) {
        this.pessoa = pessoa;
        this.setor = setor;
        this.valor = setor.getPreco();
        if (pessoa.isMeia()) {
            this.valor /= 2;
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Setor getSetor() {
        return setor;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        String saida = "";
        saida += this.pessoa.getName();
        saida += ":";
        saida += this.setor.getEvento();
        saida += ":";
        saida += this.setor.getNome();
        saida += ":";
        saida += String.format("%.2f", this.valor);
        return saida;
    }

}
