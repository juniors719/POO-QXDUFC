/**
 * 1. Fiz tudo, e passou em todos os testes
 * 2. Fiz sozinho
 * 3. Construtores
 * 4. 30 minutos
 */

package carro;

import java.util.*;

class Car {
    public int pass; // Passageiros
    public int passMax; // limite de Passageiros
    public int gas; // tanque
    public int gasMax; // limite do tanque
    public int km; // quantidade de quilometragem

    public Car() {
        this.pass = 0;
        this.passMax = 2;
        this.gas = 0;
        this.gasMax = 100;
        this.km = 0;
    }

    public String toString() {
        return "pass: " + pass + ", gas: " + gas + ", km: " + km;
    }

    public void enter() {
        if (this.pass < this.passMax)
            this.pass += 1;
        else {
            System.out.println("fail: limite de pessoas atingido");
        }
    }

    public void leave() {
        if (this.pass > 0)
            this.pass--;
        else {
            System.out.println("fail: nao ha ninguem no carro");
        }
    }

    public void fuel(int gas) {
        int emptyTank = this.gasMax - this.gas;
        if (emptyTank >= gas)
            this.gas += gas;
        else
            this.gas = this.gasMax;
    }

    public void drive(int km) {
        if (this.pass == 0) {
            System.out.println("fail: nao ha ninguem no carro");
            return;
        } else if (this.gas == 0) {
            System.out.println("fail: tanque vazio");
            return;
        } else if (km >= this.gas) {
            System.out.println("fail: tanque vazio apos andar " + this.gas + " km");
            this.km += this.gas;
            this.gas = 0;
            return;
        } else {
            this.km += km;
            this.gas -= km;
        }
    }
};

public class Solver {
    public static void main(String[] a) {

        Car car = new Car();

        while (true) {
            var line = input();
            write("$" + line);
            var args = line.split(" ");

            if (args[0].equals("end")) {
                break;
            } else if (args[0].equals("show")) {
                System.out.println(car);
            } else if (args[0].equals("enter")) {
                car.enter();
            } else if (args[0].equals("leave")) {
                car.leave();
            } else if (args[0].equals("drive")) {
                car.drive((int) number(args[1]));
            } else if (args[0].equals("fuel")) {
                car.fuel((int) number(args[1]));
            } else {
                write("fail: comando invalido");
            }
        }
    }

    private static Scanner scanner = new Scanner(System.in);

    private static String input() {
        return scanner.nextLine();
    }

    private static double number(String value) {
        return Double.parseDouble(value);
    }

    private static void write(String value) {
        System.out.println(value);
    }
}
