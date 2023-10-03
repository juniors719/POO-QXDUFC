package estudo_prova.lapiseira;

import java.util.*;

// ArrayList   -> quando o índice importa
// LinkedList  -> quando vc n precisa se importar com o índice

class Pencil {

    // atributos
    private float thickness;
    private Lead tip;
    private LinkedList<Lead> barrel;

    // cosntrutor
    public Pencil(float thickness) {
        // INICIALIZAR OS VETORES, SE TIVER
        barrel = new LinkedList<Lead>();
        this.thickness = thickness;
    }

    // ' insere um grafite no final do tambor
    // ' verifica se o grafite tem calibre compatível
    public boolean insert(Lead lead) {

        // trato primeiro os erros
        if (lead.getThickness() != this.thickness) {
            System.out.println("fail: calibre incompatível");
            return false;
        }

        barrel.add(lead);
        return true;

    }

    // ' remove o grafite da ponta
    // ' verifica se existe grafite na ponta
    // ' retorna o grafite removido
    // ' ou null se não tinha grafite
    // ' remover significa colocar o atributo tip para null
    public Lead remove() {
        // tratar os erros
        if (tip == null) {
            System.out.println("fail: nao existe grafite no tambor");
            return null;
        }

        Lead grafiteRemovido = tip;
        tip = null;
        return grafiteRemovido;
    }

    // ' remove primeiro grafite do tambor e insere na ponta
    // ' precisa existir algum grafite no tambor
    // ' precisa a ponta estar vazia
    public boolean pull() {
        // tratar os erros
        if (barrel.isEmpty()) {
            System.out.println("fail: nao existe grafite no tambor");
            return false;
        }

        if (tip != null) {
            System.out.println("fail: ja existe grafite no bico");
            return false;
        }

        tip = barrel.removeFirst();
        return true;
    }

    // ' escreve na folha gastando o grafite
    // ' verifica se existe grafite na ponta
    // ' tenta diminuir o tamanho do grafite
    // ' utilizando os métodos getSize() e setSize()
    // ' escrever uma folha gasta tip.usagePerSheet() mm
    // ' verifica se existe tamanho para escrever a folha inteira
    public void writePage() {
        // TRATAR OS ERROS
        if (tip == null) {
            System.out.println("fail: nao existe grafite no bico");
            return;
        }

        if (tip.getSize() <= 10) {
            System.out.println("fail: tamanho insuficiente");
            return;
        }

        if (tip.getSize() - 10 < tip.usagePerSheet()) {
            tip.setSize(10);
            System.out.println("fail: folha incompleta");
            return;
        }

        tip.setSize(tip.getSize() - tip.usagePerSheet());
    }

    // toString
    @Override
    public String toString() {
        String saida = "";

        // calibre: 0.5, bico: [], tambor: {}
        saida += "calibre: " + this.thickness;
        saida += ", bico: [";
        if (tip != null)
            saida += tip.toString();
        saida += "], tambor: {";
        for (int i = 0; i < barrel.size(); i++) {
            saida += "[";
            saida += barrel.get(i).toString(); // barrel[i]
            saida += "]";
        }
        saida += "}";
        return saida;
    }

}

class Lead {

    // atributos
    private float thickness;
    private String hardness;
    private int size;

    // construtor
    public Lead(float thickness, String hardness, int size) {
        this.thickness = thickness;
        this.hardness = hardness;
        this.size = size;
    }

    public int usagePerSheet() {
        if (hardness.equals("HB")) {
            return 1;
        } else if (hardness.equals("2B")) {
            return 2;
        } else if (hardness.equals("4B")) {
            return 4;
        } else if (hardness.equals("6B")) {
            return 6;
        } else {
            return 0;
        }
    }

    // getters (MESMO TIPO DO ATRIBUTO)
    public String getHardness() {
        return hardness;
    }

    public int getSize() {
        return size;
    }

    public float getThickness() {
        return thickness;
    }

    // setter (SEMPRE VOID)
    public void setSize(int size) {
        this.size = size;
    }

    // toString
    @Override
    public String toString() {
        return this.thickness + ":" + this.hardness + ":" + this.size;
    }

}

public class Solver {

    public static void main(String[] args) {
        Scanner batata = new Scanner(System.in);
        Pencil lapiseira = new Pencil(0.5f);
        while (true) {
            String line = batata.nextLine(); // le a linha
            System.out.println("$" + line); // imprime com o cifrão
            String[] a = line.split(" "); // fatiar a String no espaço

            if (a[0].equals("end")) {
                break;
            } else if (a[0].equals("init")) {
                lapiseira = new Pencil(Float.parseFloat(a[1]));
            } else if (a[0].equals("show")) {
                System.out.println(lapiseira);
            } else if (a[0].equals("insert")) {
                lapiseira.insert(new Lead(Float.parseFloat(a[1]), a[2], Integer.parseInt(a[3])));
            } else if (a[0].equals("pull")) {
                lapiseira.pull();
            } else if (a[0].equals("remove")) {
                lapiseira.remove();
            } else if (a[0].equals("write")) {
                lapiseira.writePage();
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        batata.close();
    }

}
