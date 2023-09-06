package junkfood;

import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Slot> slots = null;
    private float profit;
    private float cash;
    private int capacity;

    public VendingMachine(int capacity) {
        this.slots = new ArrayList<Slot>();
        this.capacity = capacity;
        for (int i = 0; i < capacity; i++) {
            this.slots.add(null);
        }
    }

    public Slot getSlot(int index) {
        if (index > this.capacity - 1) {
            System.out.println("fail: indice nao existe");
            return null;
        }
        Slot slot = slots.get(index);
        if (slot == null) {
            System.out.println("fail: espiral sem produtos");
            return null;
        }
        return slot;
    }

    public void setSlot(int index, Slot slot) {
        if (index > this.capacity - 1) {
            System.out.println("fail: indice nao existe");
            return;
        }
        slots.set(index, slot);
    }

    public void clearSlot(int index) {
        if (index > this.capacity - 1) {
            System.out.println("fail: indice nao existe");
            return;
        }
        slots.set(index, null);
    }

    public void insertCash(float cash) {
        this.cash += cash;
    }

    public float withdrawCash() {
        float withdraw = this.cash;
        System.out.format("voce recebeu %.2f RS%n", withdraw);
        this.cash = 0;
        return withdraw;
    }

    public float getCash() {
        return this.cash;
    }

    public float getProfit() {
        return this.profit;
    }

    public void buyItem(int index) {
        if (index > this.capacity - 1) {
            System.out.println("fail: indice nao existe");
            return;
        }
        Slot slot = slots.get(index);
        float valorProduto = slot.getPrice();
        if (valorProduto > cash) {
            System.out.println("fail: saldo insuficiente");
            return;
        }
        int slotQuantity = slot.getQuantity();
        if (slotQuantity == 0) {
            System.out.println("fail: espiral sem produtos");
            return;
        }
        System.out.println("voce comprou um " + slot.getName());
        slot.setQuantity(slotQuantity - 1);
        this.cash -= valorProduto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("saldo: ").append(String.format("%.2f", this.cash)).append("\n");

        for (int i = 0; i < this.capacity; i++) {
            Slot slot = slots.get(i);
            sb.append(i).append(" [");
            if (slot != null) {
                sb.append(String.format("%8s", slot.getName())).append(" : ")
                        .append(slot.getQuantity()).append(" U : ")
                        .append(String.format("%.2f RS", slot.getPrice()));
            } else {
                sb.append("   empty : 0 U : 0.00 RS");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

}
