package cinema;

public class Client {

    private String id;
    private String fone;

    public Client(String id, String fone) {
        this.id = id;
        this.fone = fone;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return id + ":" + fone;
=======
    // GETTERS AND SETTERS
    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
>>>>>>> 58cb8717097b7bf62e140563fac48d32e1270448
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

<<<<<<< HEAD
    public String getFone() {
        return this.fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

=======
>>>>>>> 58cb8717097b7bf62e140563fac48d32e1270448
}
