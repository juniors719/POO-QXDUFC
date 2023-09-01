package lapiseira;

import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pencil lapiseira = new Pencil(0.5f);
        while(true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if(ui[0].equals("end")) { break;}
            else if (ui[0].equals("init"))   { lapiseira = new Pencil(Float.parseFloat(ui[1])); }
            else if (ui[0].equals("insert")) { lapiseira.insert(new Lead(Float.parseFloat(ui[1]), ui[2], Integer.parseInt(ui[3]))); }
            else if (ui[0].equals("remove")) { lapiseira.remove(); }
            else if (ui[0].equals("show"))   { System.out.println(lapiseira); }
            else if (ui[0].equals("write"))  { lapiseira.writePage(); }
            else if (ui[0].equals("pull"))   { lapiseira.pull(); }
            else                                      { System.out.println("fail: comando invalido"); }
        }
        scanner.close();
    }
}
