
package agiota;

import java.util.ArrayList;

public class Agiota {

    private ArrayList<Client> alive_list;
    private ArrayList<Client> death_list;
    // alive_oper = List<Operation*>
    private ArrayList<Operation> alive_oper;
    // alive_oper = List<Operation*>
    private ArrayList<Operation> death_oper;
    private int nextOper;

    private int searchClient(String name) {
        for (int i = 0; i < this.alive_list.size(); i++) {
            if (this.alive_list.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }

    // usar tratamento de exceção try/catch
    private void pushOperation(Client client, String name, Label label, int value) {
        // verifica se o cliente tem saldo suficiente
        if (label == Label.TAKE && client.getBalance() < value) {
            throw new RuntimeException("fail: saldo insuficiente");
        }
        // verifica se o cliente tem limite suficiente
        if (label == Label.GIVE && client.getLimite() < client.getBalance() + value) {
            throw new RuntimeException("fail: limite excedido");
        }
        // cria a operação
        // adiciona a operação na lista de operações vivas
        client.addOperation(this.nextOper, name, label, value);
        this.alive_oper.add(client.getOperations().get(client.getOperations().size() - 1));
        // adiciona a operação na lista de operações do cliente
        // incrementa o id da próxima operação
        if (client.getBalance() + value > client.getLimite()) {
            this.kill(name);
        }
        this.nextOper += 1;
    }

    public Agiota() {
        this.alive_list = new ArrayList<>();
        this.death_list = new ArrayList<>();
        this.alive_oper = new ArrayList<>();
        this.death_oper = new ArrayList<>();
        this.nextOper = 0;
    }

    // usar tratamento de exceção try/catch
    public Client getClient(String name) {
        // verifica se o cliente existe
        int index = this.searchClient(name);
        if (index == -1)
            throw new RuntimeException("fail: cliente nao existe");
        return this.alive_list.get(index);
    }

    public void addClient(String name, int limite) {
        // verifica se o cliente já existe
        int index = this.searchClient(name);
        if (index == -1) {
            this.alive_list.add(new Client(name, limite));
            return;
        } else {
            throw new RuntimeException("fail: cliente ja existe");
        }
    }

    // usar tratamento de exceção try/catch
    public void give(String name, int value) {
        // procurar o cliente
        try {
            Client client = this.getClient(name);
            // adiciona a operação
            this.pushOperation(client, name, Label.GIVE, value);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void take(String name, int value) {
        // procurar o cliente
        Client client = this.getClient(name);
        // adiciona a operação
        try {
            this.pushOperation(client, name, Label.TAKE, value);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void plus() {
        // aumentar a divida de todos os clientes em 10%
        for (Client client : this.alive_list) {
            // calcula o valor do juros
            int value = (int) (client.getBalance() * 0.1);
            // adiciona a operação
            this.pushOperation(client, client.getName(), Label.PLUS, value);
        }
    }

    public void kill(String name) {
        Client client = this.getClient(name);
        this.death_list.add(client);
        this.alive_list.remove(client);
        this.alive_oper.removeAll(client.getOperations());
        this.death_oper.addAll(client.getOperations());
    }

    public String toString() {
        StringBuilder saida = new StringBuilder();
        // percorre a lista de clientes vivos
        this.alive_list.sort((a, b) -> a.getName().compareTo(b.getName()));
        for (Client client : this.alive_list) {
            saida.append(":) ");
            saida.append(client.getName() + " " + client.getBalance() + "/" + client.getLimite() + "\n");
        }
        // sort
        this.alive_oper.sort((a, b) -> a.getId() - b.getId());
        for (Operation oper : this.alive_oper) {
            saida.append("+ ");
            saida.append(oper.toString());
            saida.append("\n");
        }

        // percorre a lista de clientes mortos
        this.death_list.sort((a, b) -> a.getName().compareTo(b.getName()));
        for (Client client : this.death_list) {
            saida.append(":( ");
            saida.append(client.getName() + " " + client.getBalance() + "/" + client.getLimite() + "\n");
        }
        this.death_oper.sort((a, b) -> a.getId() - b.getId());
        for (Operation oper : this.death_oper) {
            saida.append("- ");
            saida.append(oper.toString());
            saida.append("\n");
        }
        saida.deleteCharAt(saida.length() - 1);
        return saida.toString();
    }

}
