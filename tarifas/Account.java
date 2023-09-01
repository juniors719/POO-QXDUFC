package tarifas;

public class Account {

    private BalanceManager balanceManager;
    private int id;

    public Account(int id) {
        // todo
    }

    public void deposit(int value) {
        // todo
    }

    public void fee(int value) {
        // todo
    }

    public void reverse(int index) {
        // todo
    }

    public void withdraw(int value) {
        // todo
    }

    @Override
    public String toString() {
        return "Account [balanceManager=" + balanceManager + ", id=" + id + "]";
    }

    public BalanceManager getBalanceManager() {
        return balanceManager;
    }

}
