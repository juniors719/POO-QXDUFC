package tarifas;

public class Operation {

    private int index;
    private Label label;
    private int value;
    private int balance;

    public Operation(int index, Label label, int value, int balance) {
        this.index = index;
        this.label = label;
        this.value = value;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Operation [index=" + index + ", label=" + label + ", value=" + value + ", balance=" + balance + "]";
    }

    public int getIndex() {
        return index;
    }

    public Label getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    public int getBalance() {
        return balance;
    }

}
