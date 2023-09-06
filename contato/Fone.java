package contato;

public class Fone {

    private String id;
    private String number;

    public Fone(String id, String number) {
        this.id = id;
        this.number = number;
    }

    public boolean isValid() {
        String validChars = "0123456789().";
        for (int i = 0; i < this.number.length(); i++) {
            char c = this.number.charAt(i);
            if (validChars.indexOf(c) == -1) {
                System.out.println("fail: invalid number");
                return false;
            }
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return this.id + ":" + this.number;
    }

}
