package motoca;

import java.util.Scanner;

class Person {

    private int age;
    private String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ":" + age;
    }

}

class Motorcycle {

    private Person person;
    private int power;
    private int time;

    public Motorcycle(int power) {
        this.power = power;
        this.person = null;
        this.time = 0;
    }

    public boolean insertPerson(Person person) {
        if (this.person == null) {
            this.person = person;
            return true;
        } else {
            System.out.println("fail: busy motorcycle");
        }
        return false;
    }

    public Person remove() {
        if (this.person != null) {
            Person removedPerson = this.person;
            this.person = null;
            return removedPerson;
        } else {
            System.out.println("fail: empty motorcycle");
        }
        return null;
    }

    public void buyTime(int time) {
        this.time += time;
    }

    public void drive(int time) {
        if (this.time > 0) {
            if (person != null) {
                if (person.getAge() <= 10) {
                    int saldo = time - this.time;
                    if (saldo > 0) {
                        System.out.println("time finished after " + (time - saldo) + " minutes");
                        this.time = 0;
                        return;
                    }
                    this.time -= time;
                    return;
                } else {
                    System.out.println("fail: too old to drive");
                }
            } else {
                System.out.println("fail: empty motorcycle");
            }
        } else {
            System.out.println("fail: buy time first");
        }
    }

    public void honk() {
        String honk = "P";
        for (int i = 0; i < power; i++)
            honk += "e";
        honk += "m";
        System.out.println(honk);
    }

    public Person getPerson() {
        return person;
    }

    public int getPower() {
        return power;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        String saida = "";
        saida += "power:" + power;
        saida += ", time:" + time;
        saida += ", person:(";
        saida += (person != null) ? person.toString() : "empty";
        saida += ")";
        return saida;
    }

}

public class Solver {
    static Motorcycle motoca = new Motorcycle(1);

    public static void main(String[] args) {
        while (true) {
            String line = input();
            args = line.split(" ");
            write('$' + line);

            if (args[0].equals("show")) {
                System.out.println(motoca);
            } else if (args[0].equals("init")) {
                motoca = new Motorcycle(number(args[1]));
            } else if (args[0].equals("buy")) {
                motoca.buyTime(number(args[1]));
            } else if (args[0].equals("drive")) {
                motoca.drive(number(args[1]));
            } else if (args[0].equals("enter")) {
                motoca.insertPerson(new Person(args[1], number(args[2])));
            } else if (args[0].equals("honk")) {
                motoca.honk();
            } else if (args[0].equals("leave")) {
                Person person = motoca.remove();
                if (person != null) {
                    System.out.println(person.toString());
                }
            } else if (args[0].equals("end")) {
                break;
            } else
                System.out.println("fail: comando invalido");
        }
        scanner.close();
    }

    static Scanner scanner = new Scanner(System.in);

    public static String input() {
        return scanner.nextLine();
    }

    public static void write(String value) {
        System.out.println(value);
    }

    public static int number(String str) {
        return Integer.parseInt(str);
    }
}
