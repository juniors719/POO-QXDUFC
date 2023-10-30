package cofre;

import java.util.*;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

// O sistema deverá:

// Gerenciar um cofrinho do tipo Porquinho capaz de guardar moedas e itens.
// As moedas devem ser criadas através de uma enum.
// Ambos moedas e itens deve implementar a Interaface Valuable.
// O volume do cofre incrementa conforme ele recebe itens e moedas.
// A lógica da utilização do cofre é:
// Para inserir moedas e itens, o cofre deve estar inteiro.
// Para obter moedas e itens, o cofre deve estar quebrado.
// Ao quebrar, o volume do porco deve ser zerado e o status de broken deve ser alterado para true.
// Ao obter moedas e itens, você deve retornar os objetos armazenados.
// Calcular o valor e o volume atual do porco deve ser feito através do método getValue() e getVolume().
// Moedas e Itens devem ser armazenados em uma mesma lista de Valuables.

enum Coin {
    C10(0.10, 1, "M10"),
    C25(0.25, 2, "M25"),
    C50(0.50, 3, "M50"),
    C100(1.00, 4, "M100");

    private double value;
    private int volume;
    private String label;

    private Coin(double value, int volume, String label) {
        this.value = value;
        this.volume = volume;
        this.label = label;
    }

    public double getValue() {
        return this.value;
    }

    public int getVolume() {
        return this.volume;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00");
        return this.label + ":" + d.format(this.value) + ":" + this.volume;
    }
}

class Item {

    private String label;
    private int volume;
    private double value;

    public Item(String label, double value, int volume) {
        this.label = label;
        this.volume = volume;
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00");
        return this.label + ":" + d.format(this.value) + ":" + this.volume;
    }
}

class Pig {

    private boolean broken;
    private List<Coin> coins;
    private List<Item> items;
    private int volumeMax;

    public Pig(int volumeMax) {
        this.broken = false;
        this.coins = new ArrayList<Coin>();
        this.items = new ArrayList<Item>();
        this.volumeMax = volumeMax;
    }

    public Coin createCoin(String valor) {
        switch (valor) {
            case "10":
                return Coin.C10;
            case "25":
                return Coin.C25;
            case "50":
                return Coin.C50;
            case "100":
                return Coin.C100;
            default:
                return null;
        }
    }

    public boolean addCoin(Coin coin) throws Exception {
        if (this.broken) {
            throw new Exception("fail: the pig is broken");
        }

        if (coin.getVolume() > this.getVolumeRestante()) {
            throw new Exception("fail: the pig is full");
        }

        this.coins.add(coin);
        return true;
    }

    public boolean addItem(Item item) throws Exception {
        if (this.broken) {
            throw new Exception("fail: the pig is broken");
        }

        if (item.getVolume() > this.getVolumeRestante()) {
            throw new Exception("fail: the pig is full");
        }

        this.items.add(item);
        return true;
    }

    public boolean breakPig() throws Exception {
        if (this.broken) {
            throw new Exception("fail: the pig is broken");
        }

        this.broken = true;
        return true;
    }

    public ArrayList<String> extractCoins() throws Exception {
        if (!this.broken) {
            throw new Exception("fail: you must break the pig first");
        }

        ArrayList<String> labels = new ArrayList<String>();

        for (Coin c : this.coins) {
            labels.add(c.toString());
        }

        this.coins.clear();
        return labels;
    }

    public ArrayList<String> extractItems() throws Exception {
        if (!this.broken) {
            throw new Exception("fail: you must break the pig first");
        }

        ArrayList<String> labels = new ArrayList<String>();

        for (Item i : this.items) {
            labels.add(i.toString());
        }

        this.items.clear();
        return labels;
    }

    // [] : 0.00$ : 0/20 : intact
    @Override
    public String toString() {
        DecimalFormat d = new DecimalFormat("0.00");
        String s = "[" + this.coins.stream().map(c -> c.toString()).collect(Collectors.joining(", "))
                + (this.items.isEmpty() ? "" : ", ") +
                this.items.stream().map(c -> c.toString()).collect(Collectors.joining(", ")) + "] : "
                + d.format(this.getValue()) + "$ : " + this.getVolume() + "/" + this.getVolumeMax() + " : "
                + (this.isBroken() ? "broken" : "intact");
        return s;
    }

    public int getVolume() {
        if (this.isBroken()) {
            return 0;
        }

        int volume = 0;
        for (Coin c : this.coins) {
            volume += c.getVolume();
        }
        for (Item i : this.items) {
            volume += i.getVolume();
        }
        return volume;
    }

    public double getValue() {
        double value = 0;
        for (Coin c : this.coins) {
            value += c.getValue();
        }
        for (Item i : this.items) {
            value += i.getValue();
        }
        return value;
    }

    public int getVolumeMax() {
        return this.volumeMax;
    }

    public int getVolumeRestante() {
        return this.getVolumeMax() - this.getVolume();
    }

    public boolean isBroken() {
        return this.broken;
    }
}

public class Solver {
    public static void main(String[] arg) {
        Pig pig = new Pig(5);

        while (true) {
            String line = input();
            println("$" + line);
            String[] args = line.split(" ");

            try {
                if (args[0].equals("end")) {
                    break;
                } else if (args[0].equals("init")) {
                    pig = new Pig((int) number(args[1]));
                } else if (args[0].equals("show")) {
                    println(pig);
                } else if (args[0].equals("addCoin")) {
                    pig.addCoin(pig.createCoin(args[1]));
                } else if (args[0].equals("addItem")) {
                    pig.addItem(new Item(args[1], number(args[2]), (int) number(args[3])));
                } else if (args[0].equals("break")) {
                    pig.breakPig();
                } else if (args[0].equals("extractCoins")) {
                    println("[" + String.join(", ", pig.extractCoins()) + "]");
                } else if (args[0].equals("extractItems")) {
                    println("[" + String.join(", ", pig.extractItems()) + "]");
                } else {
                    println("fail: comando invalido");
                }
            } catch (Exception e) {
                println(e.getMessage());
                // e.printStackTrace(System.out);
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