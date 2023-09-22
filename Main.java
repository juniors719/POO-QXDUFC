
import java.util.*;
import java.util.stream.Collectors;

class Evento {

    private String nome;
    private double preco;

    public Evento(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        String saida = "";
        saida += this.nome;
        saida += ":";
        saida += String.format("%.2f", this.preco);
        return saida;
    }

}

class Venda {

    private Pessoa pessoa;
    private Evento evento;
    private double valor;

    public Venda(Pessoa pessoa, Evento evento) {
        if (pessoa.isMeia())
            this.valor = evento.getPreco() / 2;
        else
            this.valor = evento.getPreco();
        this.evento = evento;
        this.pessoa = pessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Evento getEvento() {
        return evento;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        String saida = "";
        saida += this.pessoa.getName();
        saida += ":";
        saida += this.evento.getNome();
        saida += ":";
        saida += String.format("%.2f", this.valor);
        return saida;
    }

}

class Pessoa {

    private String name;
    private boolean meia;

    public Pessoa(String name, boolean meia) {
        this.name = name;
        this.meia = meia;
    }

    public String getName() {
        return name;
    }

    public boolean isMeia() {
        return meia;
    }

    @Override
    public String toString() {
        String saida = "";
        saida += this.name;
        saida += ":";
        saida += this.meia ? "meia" : "inteira";
        return saida;
    }

}

class Bilheteria {
    private ArrayList<Venda> repVendas;
    private HashMap<String, Pessoa> repPessoas;
    private HashMap<String, Evento> repEventos;

    public Bilheteria() {
        this.repPessoas = new HashMap<>();
        this.repEventos = new HashMap<>();
        this.repVendas = new ArrayList<>();
    }

    public Venda[] getRepVendas() {
        if (repVendas.size() == 0) {
            return null;
        }
        Venda[] vendas = new Venda[repVendas.size()];
        int i = 0;
        for (Venda venda : repVendas) {
            vendas[i++] = venda;
        }
        return vendas;
    }

    public Pessoa[] getRepPessoas() {
        // ordenados pela chave
        if (repPessoas.size() == 0) {
            return null;
        }
        Pessoa[] pessoas = new Pessoa[repPessoas.size()];
        int i = 0;
        LinkedHashMap<String, Pessoa> hashMapOrdenado = repPessoas.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
        for (Pessoa pessoa : hashMapOrdenado.values()) {
            pessoas[i++] = pessoa;
        }
        return pessoas;
    }

    public Evento[] getRepEventos() {
        if (repEventos.size() == 0) {
            return null;
        }
        Evento[] eventos = new Evento[repEventos.size()];
        int i = 0;
        LinkedHashMap<String, Evento> hashMapOrdenado = repEventos.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
        for (Evento evento : hashMapOrdenado.values()) {
            eventos[i++] = evento;
        }
        return eventos;
    }

    public Pessoa getPessoa(String nome) {
        if (repPessoas.containsKey(nome)) {
            return repPessoas.get(nome);
        }
        throw new IllegalArgumentException("fail: pessoa " + nome + " nao existe");
    }

    public Evento getEvento(String nome) {
        if (repEventos.containsKey(nome)) {
            return repEventos.get(nome);
        }
        throw new IllegalArgumentException("fail: evento " + nome + " nao existe");
    }

    public void addPessoa(String nome, boolean meia) {
        if (repPessoas.containsKey(nome)) {
            throw new IllegalArgumentException("fail: pessoa " + nome + " ja existe");
        }
        repPessoas.put(nome, new Pessoa(nome, meia));
    }

    public void addEvento(String nome, double preco) {
        if (repEventos.containsKey(nome)) {
            throw new IllegalArgumentException("fail: evento " + nome + " ja existe");
        }
        repEventos.put(nome, new Evento(nome, preco));
    }

    public void vender(String nomePessoa, String nomeEvento) {
        Pessoa pessoa = getPessoa(nomePessoa);
        Evento evento = getEvento(nomeEvento);
        repVendas.add(new Venda(pessoa, evento));
    }

}

class Main {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        Scanner scanner = new Scanner(System.in);
        Bilheteria bilheteria = new Bilheteria();

        while (true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String a[] = line.split(" ");
            if (line.equals("end")) {
                break;
            } else if (a[0].equals("addPessoa")) {
                try {
                    bilheteria.addPessoa(a[1], a[2].equals("meia"));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (a[0].equals("addEvento")) {
                try {
                    bilheteria.addEvento(a[1], Double.parseDouble(a[2]));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (a[0].equals("show")) {
                System.out.println(bilheteria);
            } else if (a[0].equals("vender")) {
                try {
                    bilheteria.vender(a[1], a[2]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (a[0].equals("eventos")) {
                Evento eventos[] = bilheteria.getRepEventos();
                StringBuilder saida = new StringBuilder();
                saida.append("[");
                for (int i = 0; i < eventos.length; i++) {
                    saida.append(eventos[i]);
                    if (i < eventos.length - 1)
                        saida.append(", ");
                }
                saida.append("]");
                System.out.println(saida);
            } else if (a[0].equals("pessoas")) {
                Pessoa pessoas[] = bilheteria.getRepPessoas();
                StringBuilder saida = new StringBuilder();
                saida.append("[");
                for (int i = 0; i < pessoas.length; i++) {
                    saida.append(pessoas[i]);
                    if (i < pessoas.length - 1)
                        saida.append(", ");
                }
                saida.append("]");
                System.out.println(saida);
            } else if (a[0].equals("vendas")) {
                Venda vendas[] = bilheteria.getRepVendas();
                StringBuilder saida = new StringBuilder();
                saida.append("[");
                for (int i = 0; i < vendas.length; i++) {
                    saida.append(vendas[i]);
                    if (i < vendas.length - 1)
                        saida.append(", ");
                }
                saida.append("]");
                System.out.println(saida);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
