package contato;

import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Contact contact = new Contact("");
        while (true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if (line.equals("end")) {
                break;
            } else if (ui[0].equals("init")) {
                contact = new Contact(ui[1]);
            } else if (ui[0].equals("show")) {
                System.out.println(contact);
            } else if (ui[0].equals("add")) {
                contact.addFone(ui[1], ui[2]);
            } else if (ui[0].equals("rm")) {
                contact.rmFone(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("tfav")) {
                contact.toggleFavorited();
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
