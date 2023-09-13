package mensagem;

import java.util.HashMap;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, User> users = new HashMap<String, User>();

        while (true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if (line.equals("end")) {
                break;
            } else if (ui[0].equals("addUser")) {
                users.put(ui[1], new User(ui[1]));
            } else if (ui[0].equals("sendMsg")) {
                String msg = "";
                int uiLenght = ui.length;
                for (int i = 3; i < uiLenght; i++) {
                    msg += ui[i];
                    if (i < uiLenght - 1)
                        msg += " ";
                }
                users.get(ui[1]).sendMsg(users.get(ui[2]), msg);
            } else if (ui[0].equals("inbox")) {
                System.out.print(users.get(ui[1]).getUnread());
            }
        }
        scanner.close();
    }
}
