import java.util.*;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

// Cadastrar um cliente com idCliente único
// Quando o cliente é cadastrado no sistema, automaticamente é aberta uma conta corrente e uma conta poupança para ele.
// Mensalmente:
// Contas corrente vão receber uma tarifa de 20 reais podendo inclusive ficar negativas.
// Contas poupança vão aumentar de 1 porcento.
// Sua agência deve ter um mapa de clientes e um mapa de contas.
// O cliente só tem duas contas, mas imagine que no futuro ele poderá ter várias.
// As contas devem ser tratadas utilizando polimorfismo.

class Client {
    private String clientId;
    private ArrayList<Account> accounts;

    public Client(String clientId) {
        this.clientId = clientId;
        this.accounts = new ArrayList<Account>();
    }

    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }

    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    public String getClientId() {
        return this.clientId;
    }

    // ' retorna o nome do cliente e a lista com os ids das contas no formato
    // ' nome [id1, id2, ...]
    @Override
    public String toString() {
        // use streams
        StringBuilder sb = new StringBuilder();
        sb.append(this.clientId);
        sb.append(" [" + this.accounts.stream().map(a -> (a.getAccId() + "")).collect(Collectors.joining(", ")) + "]");
        return sb.toString();
    }
}

abstract class Account {
    protected double balance;
    private static int nextAccountId = 0;
    protected int accId;
    protected String clientId;
    protected String typeId;

    // ' inicializa os atributos
    // ' saldo inicial é 0
    public Account(String clientId, String typeId) {
        this.clientId = clientId;
        this.typeId = typeId;
        this.accId = nextAccountId;
        nextAccountId += 1;
        balance = 0;
    }

    // ' realiza o depósito
    public void deposit(double value) {
        balance += value;
    }

    // ' realiza o saque
    // ' verifique se há saldo suficiente
    public void withdraw(double value) {
        if (balance < value) {
            throw new RuntimeException("fail: saldo insuficiente");
        }
        balance -= value;
    }

    public void transfer(Account other, double value) {
        if (balance < value) {
            throw new RuntimeException("fail: saldo insuficiente");
        }
        balance -= value;
        other.balance += value;
    }

    // ' retorna as informações na conta no formato
    // ' "accId:clientId:balance:typeId"
    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00"); // double x = 4.3; System.out.println( d.format(x) ); //4.30
        return accId + ":" + clientId + ":" + d.format(balance) + ":" + typeId;
    }

    public double getBalance() {
        return this.balance;
    }

    public int getAccId() {
        return this.accId;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public abstract void updateMonthly();
}

class CheckingAccount extends Account {

    protected double monthlyFee;

    public CheckingAccount(int accId, String clientId) {
        super(clientId, "CC");
        this.monthlyFee = 20;
    }

    @Override
    public void updateMonthly() {
        balance -= monthlyFee;
    }

}

class SavingsAccount extends Account {

    protected double monthlyInterest;

    public SavingsAccount(int accId, String clientId) {
        super(clientId, "CP");
        this.monthlyInterest = 0.01;
    }

    @Override
    public void updateMonthly() {
        balance *= 1.01;
    }
}

class Agency {
    private Map<Integer, Account> accounts;
    private Map<String, Client> clients;

    // ' busca pela conta e dispara excessão se não encontrar
    private Account getAccount(int accountId) {
        Account acc = accounts.get(accountId);
        if (acc == null)
            throw new RuntimeException("fail: conta nao encontrada");
        return acc;
    }

    public Agency() {
        this.accounts = new HashMap<Integer, Account>();
        // this.clients = new HashMap<String,Client>();
        this.clients = new LinkedHashMap<String, Client>();
    }

    // cria uma conta para o cliente
    // cria um objeto cliente e insere no mapa de clientes
    // cria uma conta corrente e uma conta poupança e insere no mapa de contas
    // faz o vínculo cruzado colocando as contas dentro do objeto do cliente
    public void addClient(String clientId) {
        Client client = new Client(clientId);
        this.clients.put(clientId, client);
        Account acc1 = new CheckingAccount(client.getAccounts().size(), clientId);
        Account acc2 = new SavingsAccount(client.getAccounts().size(), clientId);
        client.addAccount(acc1);
        client.addAccount(acc2);
        this.accounts.put(acc1.getAccId(), acc1);
        this.accounts.put(acc2.getAccId(), acc2);
    }

    // procura pela conta usando o getAccount e realiza a operação de depósito
    // utiliza o método deposit da classe Account
    public void deposit(int accId, double value) {
        getAccount(accId).deposit(value);
    }

    // procura pela conta e realiza a operação de saque
    // utiliza o método withdraw da classe Account
    public void withdraw(int accId, double value) {
        getAccount(accId).withdraw(value);
    }

    // procura pela conta e realiza a operação de transferência
    // utiliza o método transfer da classe Account
    public void transfer(int fromAccId, int toAccId, double value) {
        Account from = getAccount(fromAccId);
        Account to = getAccount(toAccId);
        from.transfer(to, value);
    }

    // realiza a operação de atualização mensal em todas as contas
    public void updateMonthly() {
        for (Account acc : this.accounts.values()) {
            acc.updateMonthly();
        }
    }

    @Override
    public String toString() {
        String s = "- Clients\n";
        for (Client client : this.clients.values()) {
            s += client;
            s += "\n";
        }
        s += "- Accounts\n";
        for (Account acc : this.accounts.values()) {
            s += acc;
            s += "\n";
        }
        return s;
    }
}

public class Solver {
    public static void main(String[] arg) {
        Agency agency = new Agency();

        while (true) {
            String line = input();
            println("$" + line);
            String[] args = line.split(" ");

            try {
                if (args[0].equals("end")) {
                    break;
                } else if (args[0].equals("show")) {
                    print(agency);
                } else if (args[0].equals("addCli")) {
                    agency.addClient(args[1]);
                } else if (args[0].equals("deposito")) {
                    agency.deposit((int) number(args[1]), number(args[2]));
                } else if (args[0].equals("saque")) {
                    agency.withdraw((int) number(args[1]), number(args[2]));
                } else if (args[0].equals("transf")) {
                    agency.transfer((int) number(args[1]), (int) number(args[2]), number(args[3]));
                } else if (args[0].equals("update")) {
                    agency.updateMonthly();
                } else {
                    println("fail: comando invalido");
                }
            } catch (Exception e) {
                println(e.getMessage());
            }
        }
    }

    private static Scanner scanner = new Scanner(System.in);

    private static String input() {
        return scanner.nextLine();
    }

    private static double number(String value) {
        return Double.parseDouble(value);
    }

    public static void println(Object value) {
        System.out.println(value);
    }

    public static void print(Object value) {
        System.out.print(value);
    }
}