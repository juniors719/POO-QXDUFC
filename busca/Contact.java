package busca;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Contact {

    private boolean favorited;
    private ArrayList<Fone> fones;
    private String name = "";

    public Contact(String name) {
        this.name = name;
        this.favorited = false;
        fones = new ArrayList<Fone>();
    }

    public void addFone(String id, String number) {
        Fone f = new Fone(id, number);
        if (f.isValid()) {
            fones.add(f);
            return;
        }
    }

    public void rmFone(int index) {
        if (index < this.fones.size()) {
            this.fones.remove(index);
            return;
        }
        System.out.println("fail: invalid fone");
    }

    public void toggleFavorited() {
        if (isFavorited())
            favorited = false;
        else
            favorited = true;
    }

    public boolean isFavorited() {
        return this.favorited;
    }

    public ArrayList<Fone> getFones() {
        return fones;
    }

    public String getName() {
        return name;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public void setFones(ArrayList<Fone> fones) {
        this.fones = fones;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((isFavorited()) ? "@" : "-");
        sb.append(" " + this.name + " ");
        sb.append("[" + this.fones.stream().map(f -> (f + "")).collect(Collectors.joining(", ")) + "]");
        return sb.toString();
    }

}
