package motoca;

public class Motorcycle {

    private Person person;
    private int power;
    private int time;

    public Motorcycle(int power) {
        this.power = power;
        this.person = null;
        this.time = 0;
    }

    public boolean insertPerson(Person person) {
        if (this.person != null) {
            System.out.println("fail: busy motorcycle");
            return false;
        }
        this.person = person;
        return true;
    }

    public Person remove() {
        if (this.person == null) {
            System.out.println("fail: empty motorcycle");
            return null;
        }
        Person removedPerson = this.person;
        System.out.println(removedPerson);
        this.person = null;
        return removedPerson;
    }

    public void buyTime(int time) {
        this.time += time;
    }

    public void drive(int time) {
        // erro: não tem tempo
        if (this.time == 0) {
            System.out.println("fail: buy time first");
            return;
        }
        // erro: ninguém na motoca
        if (this.person == null) {
            System.out.println("fail: empty motorcycle");
            return;
        }
        // erro: maior que 10 anos
        if (this.person.getAge() > 10) {
            System.out.println("fail: too old to drive");
            return;
        }
        // erro: tempo acaba antes
        if (time > this.time) {
            System.out.println("fail: time finished after " + this.time + " minutes");
            this.time = 0;
        }

        this.time -= time;

    }

    public void honk() {
        if (this.person == null) {
            System.out.println("fail: empty motorcycle");
            return;
        }
        String saida = "P";
        for (int i = 0; i < this.power; i++) {
            saida += "e";
        }
        saida += "m";
        System.out.println(saida);
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
        return "power:" + this.power + ", time:" + this.time + ", person:(" + ((person == null) ? "empty" : person)
                + ")";
    }

}
