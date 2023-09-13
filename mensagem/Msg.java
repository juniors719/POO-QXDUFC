package mensagem;

public class Msg {

    private String sender;
    private String text;

    public Msg(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    @Override
    public String toString() {
        return sender + ":" + text;
    }

}
