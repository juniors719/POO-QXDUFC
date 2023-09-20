// Cadastrar Clientes
// Cada cliente cadastrado tem um codenome único e um limite de crédito que ele pode ficar devendo ao agiota.
// Emprestar Dinheiro.
// Empréstimos são salvos como Transações de GIVE (porque ele dá com todo carinho) e são armazenadas tanto na lista do agiota como nos objetos dos clientes.
// Cada transação deve receber do sistema um identificador numérico crescente.
// A primeira transação tem id 0. A segunda tem id 1 e etc.
// Uma transação tem um id inteiro, um nome de cliente, um label e um valor numérico.
// Os labels das transções podem ser
// GIVE: quando o agiota dá dinheiro pra pessoa.
// TAKE: quando o agiota “pega” o dinheiro da pessoa.
// PLUS: quando o agiota decide que é hora de cobrar juros e as dívidas de todos aumentam em 10%.
// Os valores das transações sempre são positivos. Ptolomeu não entende números negativos. O que define se é entrada ou saída é o label.
// Mostrar todos os clientes com o saldo de cada um.
// Mostrar o histórico de transações de Ptolomeu.
// Receber dinheiro.
// Clientes pagam os empréstimos aos poucos. (As vezes, eles não pagam, mas seu Ptolomeu dá um jeito de pegar).
// Matar um cliente.
// As vezes Ptolomeu dá um chá de sumiço em quem não paga suas dívidas.
// Pra não deixar pontas soltas ele move o cliente da lista de clientes vivos para a lista de clientes mortos.
// Também retira as transações relacionadas ao cliente morto do histórico de transações dos vivos e move para o histórico de transações dos mortos.
// Ele disse que quando você implmentou, você queria apagar complementamente os mortos do sistema, mas ele disse que ia ficar com saudade, por isso pediu a lista dos mortos.
// O Classe cliente:
// Não possui um objeto saldo. Para calcular o saldo, percorra o vetor de operações do cliente somando o que for entrada (GIVE) e retirando do que for saída (TAKE, PLUS).
// Na hora de efetuar os juros.
// Se por acaso alguém, por causa dos juros, tiver devendo mais do que o limite, essa pessoa também vai pro saco. Perdão, pra lista do mortos.
// As transações:
// O mesmo objeto transação é compartilhado entre o histórico do agiota e o histórico do cliente correspondente.
// A lista dos mortos não são mortos de verdade, estão mortos no coração de Ptolomeu apenas, porque ele desistiu de cobrar a dívida. É o que ele disse pra polícia.

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
        this.alive_oper.add(new Operation(this.nextOper, name, label, value));
        // adiciona a operação na lista de operações do cliente
        client.addOperation(this.nextOper, name, label, value);
        // incrementa o id da próxima operação
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
        // procurar o cliente
        Client client = this.getClient(name);
        // move o cliente da lista de clientes vivos para a lista de clientes mortos
        this.alive_list.remove(client);
        this.death_list.add(client);
        // move as transações relacionadas ao cliente morto do histórico de transações
        // dos vivos e move para o histórico de transações dos mortos
        for (Operation oper : alive_oper) {
            if (oper.getName().equals(name)) {
                this.alive_oper.remove(oper);
                this.death_oper.add(oper);
            }
        }
    }

    public String toString() {
        StringBuilder saida = new StringBuilder();
        // percorre a lista de clientes vivos
        for (Client client : this.alive_list) {
            saida.append(":) ");
            saida.append(client.getName() + " " + client.getBalance() + "/" + client.getLimite() + "\n");
        }
        for (Operation oper : this.alive_oper) {
            saida.append("+ ");
            saida.append(oper.toString());
            saida.append("\n");
        }

        // percorre a lista de clientes mortos
        for (Client client : this.death_list) {
            saida.append(":( ");
            saida.append(client.getName() + " " + client.getBalance() + "/" + client.getLimite() + "\n");
        }
        for (Operation oper : this.death_oper) {
            saida.append("- ");
            saida.append(oper.toString());
            saida.append("\n");
        }
        saida.deleteCharAt(saida.length() - 1);
        return saida.toString();
    }

}
