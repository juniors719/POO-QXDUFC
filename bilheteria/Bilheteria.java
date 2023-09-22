package bilheteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Bilheteria
 */
public class Bilheteria {
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