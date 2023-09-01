package cinema;

import java.lang.reflect.Array;
import java.util.*;

public class Sala {
    ArrayList<Client> cadeiras;

    private int procurar(String nome) {
        if (cadeiras.containsKey(nome)){
            return cadeiras.
        }
        return -1;
    }

    private boolean verificarIndice(int indice) {
        // todo
        return false;
    }

    public Sala(int capacidade) {
        cadeiras = new HashMap<>(capacidade);
        for (String c : cadeiras.keySet())
            cadeiras.put(c, null);
    }

    public boolean reservar(String id, String fone, int id) {
        // todo
    }

    public void cancelar(String id) {
        // todo
    }

    public ArrayList<Client> getCadeiras() {
        // todo
        return null;
    }

    @Override
    public String toString() {
        // todo
        return "";
    }

}
