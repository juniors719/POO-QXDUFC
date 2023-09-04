/**
 * 1. Fiz tudo, e passou em todos os testes
 * 2. Fiz sozinho
 * 3. Construtores
 * 4. 30 minutos
 */

package grafite;

import java.util.Scanner;

class Pencil {

    private float thickness;
    private Lead tip;

    public Pencil(float thickness) {
        this.thickness = thickness;
        this.tip = null;
    }

    public boolean hasGrafite() {
<<<<<<< HEAD
        return this.tip != null;
=======
        if (this.tip != null)
            return true;
        return false;
>>>>>>> 58cb8717097b7bf62e140563fac48d32e1270448
    }

    public boolean insert(Lead lead) {
        if (hasGrafite()) {
            System.out.println("fail: ja existe grafite");
            return false;
        } else if (this.thickness != lead.getThickness()) {
            System.out.println("fail: calibre incompativel");
            return false;
        }
        this.tip = lead;
        return true;
    }

    public Lead remove() {
        if (hasGrafite()) {
            Lead removedLead = this.tip;
            this.tip = null;
            return removedLead;
        } else {
            System.out.println("fail: nao existe grafite");
        }
        return null;
    }

    public void writePage() {
        if (hasGrafite()) {
            int usagePerSheet = tip.usagePerSheet();
            int size = this.tip.getSize();
            if (usagePerSheet <= size && size > 10) {
                if ((size - usagePerSheet) < 10) {
                    System.out.println("fail: folha incompleta");
                    this.tip.setSize(10);
                } else
                    this.tip.setSize(size - usagePerSheet);

            } else {
                System.out.println("fail: tamanho insuficiente");
            }
        } else {
            System.out.println("fail: nao existe grafite");
        }
    }

    @Override
    public String toString() {
        String saida = "";
        saida += "calibre: " + thickness + ", grafite: ";
        if (hasGrafite())
            saida += "[" + tip.getThickness() + ":" + tip.getHardness() + ":" + tip.getSize() + "]";
        else
            saida += "null";
        return saida;
    }

}

class Lead {

    private float thickness;
    private String hardness;
    private int size;

    public Lead(float thickness, String hardness, int size) {
        this.thickness = thickness;
        this.hardness = hardness;
        this.size = size;
    }

    public int usagePerSheet() {
        switch (this.hardness) {
            case "HB":
                return 1;
            case "2B":
                return 2;
            case "4B":
                return 4;
            case "6B":
                return 6;
            default:
                return 0;
        }
    }

    public float getThickness() {
        return thickness;
    }

    public String getHardness() {
        return hardness;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}

public class Solver {
    public static void main(String[] args) {
        Pencil lap = new Pencil(0.5f);

        while (true) {
            String line = input();
            String[] argsL = line.split(" ");
            write('$' + line);

            if ("end".equals(argsL[0])) {
                break;
            } else if ("init".equals(argsL[0])) {
                lap = new Pencil(number(argsL[1]));
            } else if ("insert".equals(argsL[0])) {
                lap.insert(new Lead(number(argsL[1]), argsL[2], (int) number(argsL[3])));
            } else if ("remove".equals(argsL[0])) {
                lap.remove();
            } else if ("write".equals(argsL[0])) {
                lap.writePage();
            } else if ("show".equals(argsL[0])) {
                write(lap.toString());
            }
        }
    }

    static Scanner scanner = new Scanner(System.in);

    public static String input() {
        return scanner.nextLine();
    }

    public static void write(String value) {
        System.out.println(value);
    }

    public static float number(String str) {
        return Float.parseFloat(str);
    }
}