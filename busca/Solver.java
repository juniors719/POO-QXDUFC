package busca;

import java.util.ArrayList;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        while (true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String[] ui = line.split(" ");
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("add")) {
                ArrayList<Fone> fones = new ArrayList<>();
                for (int i = 2; i < ui.length; i++) {
                    String[] infoFone = ui[i].split(":");
                    fones.add(new Fone(infoFone[0], infoFone[1]));
                }
                agenda.addContact(ui[1], fones);
            } else if (ui[0].equals("rmFone")) {
                agenda.getContact(ui[1]).rmFone(Integer.parseInt(ui[2]));
            } else if (ui[0].equals("rm")) {
                agenda.rmContact(ui[1]);
            } else if (ui[0].equals("search")) {
                ArrayList<Contact> contatos = new ArrayList<>();
                contatos = agenda.search(ui[1]);
                if (!contatos.isEmpty()) {
                    for (Contact contact : contatos) {
                        System.out.println(contact);
                    }
                }
            } else if (ui[0].equals("tfav")) {
                agenda.getContact(ui[1]).toggleFavorited();
            } else if (ui[0].equals("favs")) {
                ArrayList<Contact> contatosFavoritted = new ArrayList<>();
                contatosFavoritted = agenda.getFavorited();
                if (!contatosFavoritted.isEmpty()) {
                    for (Contact contact : contatosFavoritted) {
                        System.out.println(contact);
                    }
                }
            } else if (ui[0].equals("show")) {
                System.out.print(agenda);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}