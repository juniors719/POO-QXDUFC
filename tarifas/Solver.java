package tarifas;

import java.util.Locale;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        Scanner scanner = new Scanner(System.in);
        Account ac = new Account(0);
        while (true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("init")) {
                ac = new Account(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("deposit")) {
                ac.deposit(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("withdraw")) {
                ac.withdraw(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("fee")) {
                ac.fee(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("extract")) {

            } else if (ui[0].equals("comprar")) {
            } else if (ui[0].equals("show")) {
                System.out.println(ac);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
