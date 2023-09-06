package motoca;

import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Motorcycle motoca = new Motorcycle(1);

        while (true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if (line.equals("end")) {
                break;
            } else if (ui[0].equals("init")) {
                motoca = new Motorcycle(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("show")) {
                System.out.println(motoca);
            } else if (ui[0].equals("enter")) {
                motoca.insertPerson(new Person(ui[1], Integer.parseInt(ui[2])));
            } else if (ui[0].equals("leave")) {
                motoca.remove();
            } else if (ui[0].equals("buy")) {
                motoca.buyTime(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("drive")) {
                motoca.drive(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("honk")) {
                motoca.honk();
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
