package agiota;

import java.util.ArrayList;

public class Client {

    private String name;
    private int limite;
    private ArrayList<Operation> operations;

    public Client(String name, int limite) {
        this.name = name;
        this.limite = limite;
        this.operations = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();
        saida.append(this.name + " " + this.getBalance() + "/" + this.limite + "\n");
        for (Operation oper : this.operations) {
            saida.append(oper + "\n");
        }
        saida.deleteCharAt(saida.length() - 1);
        return saida.toString();
    }

    public String getName() {
        return name;
    }

    public int getLimite() {
        return limite;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void addOperation(int id, String name, Label label, int value) {
        // cria a operação
        Operation oper = new Operation(id, name, label, value);
        // adiciona a operação na lista de operações
        this.operations.add(oper);
    }

    public int getBalance() {
        // calcular o saldo do cliente
        int balance = 0;
        for (Operation oper : this.operations) {
            if (oper.getLabel() == Label.GIVE || oper.getLabel() == Label.PLUS) {
                balance += oper.getValue();
            } else
                balance -= oper.getValue();
        }
        return balance;
    }

}
