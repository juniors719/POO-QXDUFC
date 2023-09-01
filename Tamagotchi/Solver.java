import java.util.*;

class Pet {
    private boolean alive;
    private int clean;
    private int cleanMax;
    private int energy;
    private int energyMax;
    private int hungry;
    private int hungryMax;
    private int age;
    private int diamonds;

    public int getClean() {
        return clean;
    }

    public int getCleanMax() {
        return cleanMax;
    }

    public int getEnergy() {
        return energy;
    }

    public int getEnergyMax() {
        return energyMax;
    }

    public int getHungry() {
        return hungry;
    }

    public int getHungryMax() {
        return hungryMax;
    }

    public void setClean(int clean) {
        this.clean = clean;
        if (this.clean <= 0) {
            System.out.println("fail: pet morreu de sujeira");
            this.clean = 0;
            this.alive = false;
        } else if (this.clean > this.cleanMax) {
            this.clean = this.cleanMax;
        }
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        if (this.energy <= 0) {
            System.out.println("fail: pet morreu de fraqueza");
            this.energy = 0;
            this.alive = false;
        } else if (this.energy > this.energyMax) {
            this.energy = this.energyMax;
        }
    }

    public void setHungry(int hungry) {
        this.hungry = hungry;
        if (this.hungry <= 0) {
            System.out.println("fail: pet morreu de fome");
            this.hungry = 0;
            this.alive = false;
        } else if (this.hungry > this.hungryMax) {
            this.hungry = this.hungryMax;
        }
    }

    public Pet(int energyMax, int hungryMax, int cleanMax) {
        this.diamonds = 0;
        this.age = 0;
        this.hungryMax = hungryMax;
        this.hungry = hungryMax;
        this.energyMax = energyMax;
        this.energy = energyMax;
        this.cleanMax = cleanMax;
        this.clean = cleanMax;
        this.alive = true;
    }

    @Override
    public String toString() {
        return "E:" + this.energy + "/" + this.energyMax +
                ", S:" + this.hungry + "/" + this.hungryMax +
                ", L:" + this.clean + "/" + this.cleanMax +
                ", D:" + this.diamonds +
                ", I:" + this.age;
    }

    public void eat() {
        if (testAlive()) {
            setEnergy(getEnergy() - 1);
            setHungry(getHungry() + 4);
            setClean(getClean() - 2);
            this.age++;
        }
    }

    public void play() {
        if (!testAlive())
            return;
        setEnergy(getEnergy() - 2);
        setHungry(getHungry() - 1);
        setClean(getClean() - 3);
        this.diamonds++;
        this.age++;
    }

    public void shower() {
        if (!testAlive())
            return;
        setEnergy(getEnergy() - 3);
        setHungry(getHungry() - 1);
        setClean(getCleanMax());
        this.age += 2;
    }

    public void sleep() {
        if (!testAlive())
            return;
        if (getEnergyMax() - getEnergy() < 5) {
            System.out.println("fail: nao esta com sono");
            return;
        }
        this.age += getEnergyMax() - getEnergy();
        setEnergy(getEnergyMax());
        setHungry(getHungry() - 1);
    }

    private boolean testAlive() {
        if (this.alive == true)
            return true;
        System.out.println("fail: pet esta morto");
        return false;
    }

}

public class Solver {
    public static void main(String[] a) {
        Pet pet = new Pet(0, 0, 0);

        while (true) {
            var line = input();
            write("$" + line);
            var args = line.split(" ");

            if (args[0].equals("end")) {
                break;
            } else if (args[0].equals("show")) {
                write(pet.toString());
            } else if (args[0].equals("init")) {
                pet = new Pet((int) number(args[1]), (int) number(args[2]), (int) number(args[3]));
            } else if (args[0].equals("play")) {
                pet.play();
            } else if (args[0].equals("eat")) {
                pet.eat();
            } else if (args[0].equals("sleep")) {
                pet.sleep();
            } else if (args[0].equals("shower")) {
                pet.shower();
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