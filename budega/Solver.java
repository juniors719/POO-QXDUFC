package budega;

import java.util.Scanner;

public class Solver {
    public static void main(String[] a) {

        Mercantil mercantil = null;

        while (true) {
            var line = input();
            write("$" + line);
            var args = line.split(" ");

            if (args[0].equals("end")) {
                break;
            } else if (args[0].equals("init")) {
                mercantil = new Mercantil(number(args[1]));
            } else if (args[0].equals("show")) {
                System.out.println(mercantil);
            } else if (args[0].equals("arrive")) {
                mercantil.chegar(new Pessoa(args[1]));
            } else if (args[0].equals("call")) {
                mercantil.chamar(number(args[1]));
            } else if (args[0].equals("finish")) {
                mercantil.finalizar(number(args[1]));
            } else {
                write("fail: comando invalido");
            }
        }
    }

    private static Scanner scanner = new Scanner(System.in);

    private static String input() {
        return scanner.nextLine();
    }

    private static int number(String value) {
        return Integer.parseInt(value);
    }

    private static void write(String value) {
        System.out.println(value);
    }
}
