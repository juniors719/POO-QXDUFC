package junkfood;

import java.util.ArrayList;

public class VendindMachine {

    private ArrayList<Slot> slots = null;
    private float profit;
    private float cash;
    private int capacity;
    
    public VendindMachine(int capacity) {
        this.slots = new ArrayList<>(capacity);
        this.slots.addAll(null);
    }

    public Slot getSlot(int index) {
        return null;
    }

    public void setSlot(int index, Slot slot) {

    }

    public void clearSlot() {

    }

    public void insertCash(float cash) {

    }

    public float withdrawCash() {
        return 0;
    }

    public float getCash() {
        return this.cash;
    }

    public float getProfit() {
        return this.profit;
    }

    public void buyItem(int index) {

    }

    @Override
    public String toString() {
        return "VendindMachine [slots=" + slots + ", profit=" + profit + ", cash=" + cash + ", capacity=" + capacity
                + "]";
    }

}
