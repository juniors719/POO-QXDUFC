package junkfood;

import java.util.Locale;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        Scanner scanner = new Scanner(System.in);
        VendingMachine maquina = new VendingMachine(0);
        while (true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("init")) {
                maquina = new VendingMachine(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("set")) {
                maquina.setSlot(Integer.parseInt(ui[1]),
                        new Slot(ui[2], Integer.parseInt(ui[3]), Float.parseFloat(ui[4])));
            } else if (ui[0].equals("limpar")) {
                maquina.clearSlot(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("dinheiro")) {
                maquina.insertCash(Float.parseFloat(ui[1]));
            } else if (ui[0].equals("troco")) {
                maquina.withdrawCash();
            } else if (ui[0].equals("comprar")) {
                maquina.buyItem(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("show")) {
                System.out.print(maquina);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
