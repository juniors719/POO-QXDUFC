package mensagem;

import java.util.ArrayList;

public class User {

    private String username;
    private ArrayList<Msg> inbox;
    private int unreadCount;

    public User(String username) {
        this.username = username;
        this.inbox = new ArrayList<>();
        this.unreadCount = 0;
    }

    public void sendMsg(User dest, String text) {
        dest.unreadCount += 1;
        Msg msg = new Msg(this.username, text);
        dest.inbox.add(msg);
    }

    public String getUnread() {
        String saida = "";
        if (unreadCount == 0) {
            return "- empty -\n";
        }
        for (int i = inbox.size() - unreadCount, j = 0; j < unreadCount; i++, j++) {
            saida += inbox.get(i).toString() + '\n';
        }
        unreadCount = 0;
        return saida;
    }

    public String getAllMsg() {
        String saida = "";
        for (Msg msg : inbox) {
            saida += msg.toString() + '\n';
        }
        return saida;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", inbox=" + inbox + ", unreadCount=" + unreadCount + "]";
    }

}
