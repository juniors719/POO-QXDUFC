package bilheteria;

public class Pessoa {

    private String name;
    private boolean meia;

    public Pessoa(String name, boolean meia) {
        this.name = name;
        this.meia = meia;
    }

    public String getName() {
        return name;
    }

    public boolean isMeia() {
        return meia;
    }

    @Override
    public String toString() {
        String saida = "";
        saida += this.name;
        saida += ":";
        saida += this.meia ? "meia" : "inteira";
        return saida;
    }

}
