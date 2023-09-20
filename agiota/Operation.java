package agiota;

public class Operation {

    private int id;
    private String name;
    private Label label;
    private int value;

    public Operation(int id, String name, Label label, int value) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        return "id:" + this.id + " " + this.label.name().toLowerCase() + ":" + this.name + " " + this.value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Label getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

}
