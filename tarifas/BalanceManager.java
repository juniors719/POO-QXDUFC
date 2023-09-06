package tarifas;

import java.util.*;

public class BalanceManager {

    private int balance; // saldo do cliente
    private List<Operation> extract;
    private int nextId; // id da próxima operação

    public BalanceManager() {
        this.addOperation(Label.opening, 0);
        this.extract = new ArrayList<>();
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
        if (qtdOp > 0) {
            List<Operation> ext = new ArrayList<Operation>();
            for (int i = this.extract.size() - qtdOp; i < qtdOp; i++) {
                ext.add(this.extract.get(i));
                sb.append(this.extract.get(i).toString());
            }
            return ext;
        } else {
            for (int i = 0; i < extract.size(); i++) {

            }
        }
        return extract;
    }

}
