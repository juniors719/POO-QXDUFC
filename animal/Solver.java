package animal;

import java.util.Scanner;

class Animal {

    public String especie;
    public String barulho;
    public int idade;

    public Animal(String especie, String barulho) {
        this.especie = especie;
        this.barulho = barulho;
        this.idade = 0;
    }

    public String fazerBarulho() {
        if(this.idade == 0) return "---";
        else if (idade == 4) return "RIP";
        return barulho;
    }

    public void envelhecer(int nivel) {
        this.idade += nivel;
        if(this.idade >= 4) {
            System.out.println("warning: " + this.especie + " morreu");
            this.idade = 4;
        }
    }

    @Override
    public String toString() {
        return this.especie + ":" + this.idade + ":" + this.barulho;
    }
    

}

public class Solver {

    public static void main(String[] _args) {
        Animal animal = new Animal("", "");

        while (true) {
            String line = input();
            String[] args = line.split(" ");
            write("$" + line);

            if (args[0].equals("init")) {
                animal = new Animal(args[1], args[2]);
            } else if (args[0].equals("grow")) {
                animal.envelhecer(number(args[1]));
            } else if (args[0].equals("noise")) {
                write(animal.fazerBarulho());
            } else if (args[0].equals("show")) {
                write(animal.toString());
            } else if (args[0].equals("end")) {
                break;
            } else {
                write("fail: comando invalido");
            }
        }
    }

    static Scanner scanner = new Scanner(System.in);
    public static String input()           { return scanner.nextLine();    }
    public static void write(String value) { System.out.println(value);    }
    public static int number(String str)   { return Integer.parseInt(str); }

}
