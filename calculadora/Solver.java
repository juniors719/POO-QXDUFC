package calculadora;

import java.util.*;
import java.text.*;

class Calculator {

    public int battery;
    public int batteryMax;
    public float display;

    public Calculator(int batteryMax) {
        // TODO
    }

    public void chargeBattery(int value) {
        int emptyBattery = this.batteryMax - this.battery;
        if (value > emptyBattery)
            this.battery = this.batteryMax;
        else
            this.battery += value;
    }

    public void sum(int a, int b) {
        if (this.useBattery()) {
            this.display = a + b;
        }
    }

    public boolean useBattery() {
        if (this.battery == 0) {
            System.out.println("fail: bateria insuficiente");
            return false;
        }
        this.battery -= 1;
        return true;
    }

    public void division(int num, int den) {
        if (!useBattery())
            return;
        if (den == 0) {
            System.out.println("fail: divisao por zero");
        } else
            this.display = (float) num / den;
    }

    public String toString() {
        DecimalFormat form = new DecimalFormat("0.00");
        return "display = " + form.format(this.display).replace(",", ".") + ", battery = " + this.battery;
    }
}

public class Solver {
    static Calculator calc = new Calculator(0);

    public static void main(String[] args) {
        while (true) {
            String line = input();
            String[] argsL = line.split(" ");
            write('$' + line);

            if ("show".equals(argsL[0])) {
                write(calc.toString());
            } else if ("init".equals(argsL[0])) {
                calc = new Calculator(number(argsL[1]));
            } else if ("charge".equals(argsL[0])) {
                calc.chargeBattery(number(argsL[1]));
            } else if ("sum".equals(argsL[0])) {
                calc.sum(number(argsL[1]), number(argsL[2]));
            } else if ("div".equals(argsL[0])) {
                calc.division(number(argsL[1]), number(argsL[2]));
            } else if ("end".equals(argsL[0])) {
                break;
            } else {
                write("fail: comando invalido");
            }
        }
        scanner.close();
    }

    public static String input() {
        String input = scanner.nextLine();
        // scanner.close();
        return input;
    }

    static Scanner scanner = new Scanner(System.in);

    public static void write(String value) {
        System.out.println(value);
    }

    public static int number(String str) {
        return Integer.parseInt(str);
    }
}