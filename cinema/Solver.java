package cinema;

import java.util.*;

public class Solver {
<<<<<<< HEAD
    static Shell sh = new Shell();
    static Sala sala = new Sala(0);

    public static void main(String args[]) {
        sh.chain.put("init", () -> {
            sala = new Sala(getInt(1));
        });
        sh.chain.put("show", () -> {
            System.out.println(sala);
        });
        sh.chain.put("reservar", () -> {
            sala.reservar(getStr(1), getStr(2), getInt(3));
        });
        sh.chain.put("cancelar", () -> {
            sala.cancelar(getStr(1));
        });

        sh.execute();
    }

    static int getInt(int pos) {
        return Integer.parseInt(sh.param.get(pos));
    }

    static String getStr(int pos) {
        return sh.param.get(pos);
    }
}

class Shell {
    public Scanner scanner = new Scanner(System.in);
    public HashMap<String, Runnable> chain = new HashMap<>();
    public ArrayList<String> param = new ArrayList<>();

    public Shell() {
        Locale.setDefault(new Locale("en", "US"));
    }

    public void execute() {
        while (true) {
            param.clear();
            String line = scanner.nextLine();
            Collections.addAll(param, line.split(" "));
            System.out.println("$" + line);
            if (param.get(0).equals("end")) {
                break;
            } else if (chain.containsKey(param.get(0))) {
                chain.get(param.get(0)).run();
            } else {
                System.out.println("fail: comando invalido");
            }
        }
    }
=======

>>>>>>> 58cb8717097b7bf62e140563fac48d32e1270448
}
