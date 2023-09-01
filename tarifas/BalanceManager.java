package tarifas;

import java.util.*;

public class BalanceManager {

    private int balance;
    private List<Operation> extract;
    private int nextId;

    public BalanceManager() {
        // todo
    }

    public void addOperation(Label label, int value) {
        // todo
    }

    @Override
    public String toString() {
        return "BalanceManager [balance=" + balance + ", extract=" + extract + ", nextId=" + nextId + "]";
    }

    public int getBalance() {
        return balance;
    }

    public List<Operation> getExtract() {
        return extract;
    }

}
