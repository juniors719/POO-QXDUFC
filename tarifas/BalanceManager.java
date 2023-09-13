package tarifas;

import java.util.*;

public class BalanceManager {

    private int balance; // saldo do cliente
    private List<Operation> extract;
    private int nextId; // id da próxima operação

    public BalanceManager() {
        this.extract = new ArrayList<>();
        this.addOperation(Label.opening, 0);
    }

    public void addOperation(Label label, int value) {
        this.balance += value;
        this.extract.add(new Operation(nextId, label, value, this.balance));
        this.nextId++;
    }

    @Override
    public String toString() {
        return "BalanceManager [balance=" + balance + ", extract=" + extract + ", nextId=" + nextId + "]";
    }

    public int getBalance() {
        return balance;
    }

    public List<Operation> getExtract(int qtdOp) {
        if (qtdOp == -1) {
            return this.extract;
        }
        StringBuilder sb = new StringBuilder();
        int tamanho = this.extract.size();
        List<Operation> ext = new ArrayList<Operation>();
        if (qtdOp > 0) {
            for (int i = tamanho - qtdOp; i < tamanho; i++) {
                ext.add(this.extract.get(i));
                sb.append(this.extract.get(i).toString());
                sb.append("\n");
            }
        } else {
            for (int i = 0; i < tamanho; i++) {
                sb.append(this.extract.get(i).toString());
                sb.append("\n");
            }
        }
        System.out.print(sb);
        return extract;
    }

}
