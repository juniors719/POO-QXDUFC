package agiota;

import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agiota agiota = new Agiota();
        while (true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if (line.equals("end")) {
                break;
            } else if (ui[0].equals("addCli")) {
                try {
                    agiota.addClient(ui[1], Integer.parseInt(ui[2]));
                } catch (RuntimeException re) {
                    System.out.println(re.getMessage());
                }
            } else if (ui[0].equals("give")) {
                try {
                    agiota.give(ui[1], Integer.parseInt(ui[2]));
                } catch (RuntimeException re) {
                    System.out.println(re.getMessage());
                }
            } else if (ui[0].equals("take")) {
                try {
                    agiota.take(ui[1], Integer.parseInt(ui[2]));
                } catch (RuntimeException re) {
                    System.out.println(re.getMessage());
                }
            } else if (ui[0].equals("showCli")) {
                try {
                    System.out.println(agiota.getClient(ui[1]));
                } catch (RuntimeException re) {
                    System.out.println(re.getMessage());
                }
            } else if (ui[0].equals("kill")) {
                try {
                    agiota.kill(ui[1]);
                } catch (RuntimeException re) {
                    System.out.println(re.getMessage());
                }
            } else if (ui[0].equals("show")) {
                System.out.println(agiota);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
