package topic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Topic {

    private ArrayList<Pass> normalSeats;
    private ArrayList<Pass> prioritySeats;

    private static int findFirstFreePos(List<Pass> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                return i;
            }
        }
        return -1;
    }

    private static int findName(ArrayList<Pass> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null)
                continue;
            if (name.equals(list.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    private static boolean insertOnList(Pass pass, ArrayList<Pass> list) {
        int freePos = findFirstFreePos(list);
        if (freePos != -1) {
            list.set(freePos, pass);
            return true;
        }
        return false;
    }

    private static boolean removeFromList(String name, ArrayList<Pass> list) {
        int posRemove = findName(list, name);
        if (posRemove != -1) {
            list.set(posRemove, null);
            return true;
        }
        return false;
    }

    public Topic(int capacity, int qtdPriority) {
        this.prioritySeats = new ArrayList<Pass>(qtdPriority);
        for (int i = 0; i < qtdPriority; i++) {
            prioritySeats.add(i, null);
        }
        this.normalSeats = new ArrayList<Pass>(capacity - qtdPriority);
        for (int i = 0; i < (capacity - qtdPriority); i++) {
            normalSeats.add(i, null);
        }
    }

    public boolean insert(Pass pass) {
        if (findName(this.normalSeats, pass.getName()) != -1 || findName(prioritySeats, pass.getName()) != -1) {
            System.out.println("fail: " + pass.getName() + " ja esta na topic");
            return false;
        }
        if (pass.isPriority()) { // É PRIORIDADE
            if (!insertOnList(pass, prioritySeats)) {
                if (!insertOnList(pass, normalSeats)) {
                    System.out.println("fail: topic lotada");
                    return false;
                }
            }
        } else { // NÃO É PRIORIDADE
            if (!insertOnList(pass, normalSeats)) {
                if (!insertOnList(pass, prioritySeats)) {
                    System.out.println("fail: topic lotada");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean remove(String name) {
        if (!removeFromList(name, normalSeats)) {
            if (!removeFromList(name, prioritySeats)) {
                System.out.println("fail: " + name + " nao esta na topic");
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "[" + Stream.concat(
                this.prioritySeats.stream().map(p -> ("@" + ((p == null) ? ("") : ("" + p)))),
                this.normalSeats.stream().map(p -> ("=" + ((p == null) ? ("") : ("" + p)))))
                .collect(Collectors.joining(" ")) + "]";
    }

}
