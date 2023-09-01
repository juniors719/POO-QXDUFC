package cofrinho;

import java.util.*;
import java.util.stream.Collectors;

enum Coin {

    C10(.10, 1, "C10"),
    C25(.25, 2, "C25"),
    C50(.50, 3, "C50"),
    C100(1.00, 4, "C100");

    private double value;
    private int volume;
    private String label;

    Coin(double value, int volume, String label) {
        this.value = value;
        this.volume = volume;
        this.label = label;
    }

    public int getVolume() {
        return this.volume;
    }

    public double getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }
}

class Item {

    private String label;
    private int volume;

    public Item(String label, int volume) {
        this.label = label;
        this.volume = volume;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Item [label=" + label + ", volume=" + volume + "]";
    }
}

class Pig {

    private int volumeMax;
    private boolean broken;
    private List<Coin> coins;
    private List<Item> items;

    public int getVolume() {
        int totalVolume = 0;
        if (!isBroken()) {
            for (Coin coin : coins) {
                totalVolume += coin.getVolume();
            }
            for (Item item : items) {
                totalVolume += item.getVolume();
            }
        }
        return totalVolume;
    }

    public double getValue() {
        double totalValue = 0;
        for (Coin coin : coins) {
            totalValue += coin.getValue();
        }
        return totalValue;
    }

    public int getVolumeMax() {
        return volumeMax;
    }

    public int getVolumeRestante() {
        return getVolumeMax() - getVolume();
    }

    public boolean isBroken() {
        return broken;
    }

    public Pig(int volumeMax) {
        this.volumeMax = volumeMax;
        this.items = new ArrayList<>();
        this.coins = new ArrayList<>();
        this.broken = false;
    }

    public boolean addCoin(Coin coin) {
        int volumeRestante = getVolumeRestante();
        if (!isBroken()) {
            if (coin.getVolume() <= volumeRestante) {
                coins.add(coin);
                return true;
            } else {
                System.out.println("fail: the pig is full");
            }
        } else {
            System.out.println("fail: the pig is broken");
        }
        return false;
    }

    public boolean addItem(Item item) {
        int volumeRestante = getVolumeRestante();
        if (!isBroken()) {
            if (item.getVolume() <= volumeRestante) {
                items.add(item);
                return true;
            } else {
                System.out.println("fail: the pig is full");
            }
        } else {
            System.out.println("fail: the pig is broken");
        }
        return false;
    }

    public boolean breakPig() {
        if (!broken) {
            broken = true;

            return true;
        }
        return false;
    }

    public List<String> extractCoins() {
        if (this.broken) {
            List<String> extractedCoins = new ArrayList<>();
            for (Coin coin : coins) {
                extractedCoins.add(String.format("%.2f:%d", coin.getValue(), coin.getVolume()));
            }
            coins.clear();
            return extractedCoins;
        }
        System.out.println("fail: you must break the pig first");
        return new ArrayList<>();
    }

    public List<String> extractItems() {
        if (this.broken) {
            List<String> extractedItems = new ArrayList<>();
            for (Item item : items) {
                extractedItems.add(item.getLabel() + ":" + item.getVolume());
            }
            items.clear();
            return extractedItems;
        }
        System.out.println("fail: you must break the pig first");
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        String coinsString = coins.stream()
                .map(coin -> String.format("%.2f:%d", coin.getValue(), coin.getVolume()))
                .collect(Collectors.joining(", "));

        String itemsString = items.stream()
                .map(item -> item.getLabel() + ":" + item.getVolume())
                .collect(Collectors.joining(", "));

        return "state=" + (isBroken() ? "broken" : "intact") +
                " : coins=[" + coinsString + "]" +
                " : items=[" + itemsString + "]" +
                " : value=" + String.format("%.2f", getValue()) +
                " : volume=" + getVolume() + "/" + getVolumeMax();
    }

}

public class Solver {
    public static void main(String[] Args) {
        Locale.setDefault(new Locale("en", "US"));
        Scanner scanner = new Scanner(System.in);
        Pig pig = null;

        while (true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String[] args = line.split(" ");

            if (args[0].equals("end")) {
                break;
            } else if (args[0].equals("addCoin")) {
                Coin coin = null;
                if (args[1].equals("10")) {
                    coin = Coin.C10;
                } else if (args[1].equals("25")) {
                    coin = Coin.C25;
                } else if (args[1].equals("50")) {
                    coin = Coin.C50;
                } else if (args[1].equals("100")) {
                    coin = Coin.C100;
                }

                if (coin != null) {
                    pig.addCoin(coin);
                }
            } else if (args[0].equals("init")) {
                pig = new Pig(number(args[1]));
            } else if (args[0].equals("addItem")) {
                pig.addItem(new Item(args[1], number(args[2])));
            } else if (args[0].equals("break")) {
                pig.breakPig();
            } else if (args[0].equals("extractCoins")) {
                List<String> extractedCoins = pig.extractCoins();
                write("[" + String.join(", ", extractedCoins) + "]");
            } else if (args[0].equals("extractItems")) {
                List<String> extractedItems = pig.extractItems();
                write("[" + String.join(", ", extractedItems) + "]");
            } else if (args[0].equals("show")) {
                write(pig.toString());
            } else {
                write("fail: invalid command");
            }
        }
        scanner.close();
    }

    public static int number(String number) {
        return Integer.parseInt(number);
    }

    public static void write(String str) {
        System.out.println(str);
    }
}
