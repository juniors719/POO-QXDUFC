package cinema;

public class Client {

    private String fone;
    private String id;

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
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
}
