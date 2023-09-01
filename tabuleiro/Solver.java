package tabuleiro;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = null;
        while(true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if(ui[0].equals("end")) { break;}
            else if (ui[0].equals("init"))   { board = new Board(Integer.parseInt(ui[1]), Integer.parseInt(ui[2])); }
            else if (ui[0].equals("addTrap")) { board.addTrap(Integer.parseInt(ui[1])); }
            else if (ui[0].equals("roll")) { board.rollDice(Integer.parseInt(ui[1])); }
            else if (ui[0].equals("show"))   { System.out.println(board); }
            else                                      { System.out.println("fail: comando invalido"); }
        }
        scanner.close();
    }
}
