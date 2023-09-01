package tabuleiro;

public class Player {

    private int label;
    private int pos;
    private boolean free;

    public Player(int label) {
        this.label = label;
        this.free = true;
        this.pos = 0;
    }

    public boolean isFree() {
        return this.free;
    }

    // GETTER AND SETTERS

    public int getLabel() {
        return label;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "";
    }

}
