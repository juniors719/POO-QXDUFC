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
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%2s", this.index)).append(":");
        sb.append(String.format("%9s", this.label)).append(":");
        sb.append(String.format("%5s", this.value)).append(":");
        sb.append(String.format("%5s", this.balance));
        return sb.toString();
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
