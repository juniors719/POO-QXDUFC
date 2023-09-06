package tarifas;

import java.util.*;

public class Account {

    private BalanceManager balanceManager;
    private int id;

    public Account(int id) {
        this.id = id;
        balanceManager = new BalanceManager();
    }

    // ' adiciona valor à conta
    public void deposit(int value) {
        if (value <= 0) {
            System.out.println("fail: invalid value");
            return;
        }
        balanceManager.addOperation(Label.deposit, value);
    }

    // ' retira o dinheiro, mesmo que o balance fique negativo
    public void fee(int value) {
        balanceManager.addOperation(Label.fee, (value * -1));
    }

    // ' se o índice for válido e representar uma operação de tarifa
    // ' adicione o mesmo valor tarifado, mas com label de reverse(extorno)
    public void reverse(int index) {
        List<Operation> extract = new ArrayList<Operation>();
        extract = balanceManager.getExtract(-1);
        if (index >= extract.size()) {
            System.out.println("fail: index " + index + " invalid");
            return;
        }
        Operation operacao = extract.get(index);
        if (operacao.getLabel() != Label.fee) {
            System.out.println("fail: index " + index + " is not a fee");
            return;
        }
        int valueReverse = operacao.getValue();
        balanceManager.addOperation(Label.reverse, valueReverse);
    }

    // ' só realiza a operação se houver dinheiro suficiente na conta
    public void withdraw(int value) {
        int balance = this.balanceManager.getBalance();
        if (balance < value) {
            System.out.println("fail: insufficient balance");
            return;
        }
        this.balanceManager.addOperation(Label.withdraw, (value * -1));
    }

    @Override
    public String toString() {
        return "account:" + this.id + " balance:" + this.balanceManager.getBalance();
    }

    public BalanceManager getBalanceManager() {
        return balanceManager;
    }

}
