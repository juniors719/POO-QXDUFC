package cinema;

public class Client {

    private String id;
    private String fone;

    public Client(String id, String fone) {
        this.id = id;
        this.fone = fone;
    }

    // GETTERS AND SETTERS
    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id + ":" + this.fone;
    }

}
