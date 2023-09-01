package lapiseira;

import java.util.*;

public class Pencil {

    private float thickness;
    private Lead tip;
    private LinkedList<Lead> barrel;

    public Pencil(float thickness) {
        this.thickness = thickness;
        tip = null;
        barrel = new LinkedList<>();
    }

    public boolean insert(Lead lead) {
        if (lead.getThickness() == this.thickness) {
            barrel.add(lead);
            return true;
        }
        System.out.println("fail: calibre incompatível");
        return false;
    }

    public Lead remove() {
        if (this.tip != null) {
            Lead removedLead = tip;
            tip = null;
            return removedLead;
        }
        System.out.println("fail: nao existe grafite no bico");
        return null;
    }

    public boolean pull() {
        if (!barrel.isEmpty()) {
            if (tip == null) {
                tip = barrel.removeFirst();
                return true;
            } else {
                System.out.println("fail: ja existe grafite no bico");
            }
        } else {
            System.out.println("fail: nao existe grafite no barril");
        }
        return false;
    }

    public void writePage() {
        if (tip != null) {
            int usagePerSheet = tip.usagePerSheet();
            int size = tip.getSize();
            if (usagePerSheet <= size && size > 10) {
                if ((size - usagePerSheet) < 10) {
                    System.out.println("fail: folha incompleta");
                    tip.setSize(10);
                } else
                    tip.setSize(size - usagePerSheet);

            } else {
                System.out.println("fail: tamanho insuficiente");
            }
        } else {
            System.out.println("fail: nao existe grafite no bico");
        }
    }

    @Override
    public String toString() {
        String saida = "";
        saida += "calibre: " + thickness + ", bico: ";
        if (tip != null)
            saida += tip.toString();
        else
            saida += "[]";
        saida += ", tambor: {";
        if (!barrel.isEmpty())
            for (Lead lead : barrel)
                saida += lead.toString();
        saida += "}";
        return saida;
    }
}
