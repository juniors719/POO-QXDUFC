package cinema;

import java.util.*;

public class Sala {
    private HashMap<Integer, Client> cadeiras;
    private HashMap<String, Client> clientes;

    private int procurar(String nome) {
        int saida = -1;
        for (int i = 0; i < cadeiras.size(); i++) {
            if (cadeiras.get(i) != null && cadeiras.get(i).getId().equals(nome)) {
                saida = i;
                break;
            }
        }
        return saida;
    }

    private boolean verificarIndice(int indice) {
        if (indice > cadeiras.size() || indice < 0) {
            System.out.println("fail: cadeira nao existe");
            return false;
        }
        return true;
    }

    public Sala(int capacidade) {
        cadeiras = new HashMap<>(capacidade);
<<<<<<< HEAD
        clientes = new HashMap<>();
        for (int i = 0; i < capacidade; i++) {
            cadeiras.put(i, null);
        }
=======
        for (String c : cadeiras.keySet())
            cadeiras.put(c, null);
>>>>>>> 58cb8717097b7bf62e140563fac48d32e1270448
    }

    public List<Client> getCadeiras() {
        if (cadeiras.size() > 0) {
            return new ArrayList<>(cadeiras.values());
        }
        return null;
    }

    public boolean reservar(String id, String fone, int ind) {
        if (verificarIndice(ind)) {
            if (cadeiras.get(ind) == null) {
                if (procurar(id) == -1) {
                    Client cliente = new Client(id, fone);
                    cadeiras.put(ind, cliente);
                    clientes.put(id, cliente);
                    return true;
                } else {
                    System.out.println("fail: cliente ja esta no cinema");
                }
            } else {
                System.out.println("fail: cadeira ja esta ocupada");
            }
        }
        return false;
    }

    public void cancelar(String id) {
        int indice = procurar(id);
        if (indice != -1) {
            cadeiras.put(indice, null);
            clientes.remove(id);
        } else {
            System.out.println("fail: cliente nao esta no cinema");
        }
    }

    @Override
    public String toString() {
        String saida = "[";
        for (int i = 0; i < cadeiras.size(); i++) {
            if (i != 0) {
                saida += " ";
            }
            if (cadeiras.get(i) == null) {
                saida += "-";
            } else {
                saida += cadeiras.get(i);
            }
        }
        saida += "]";
        return saida;
    }

}
