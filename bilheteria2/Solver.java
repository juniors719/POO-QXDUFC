package bilheteria2;

import java.util.Locale;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        Scanner scanner = new Scanner(System.in);
        Bilheteria bilheteria = new Bilheteria();

        while (true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String[] ui = line.split(" ");

            if (line.equals("end")) {
                break;

            } else if (ui[0].equals("addPessoa")) {
                try {
                    bilheteria.addPessoa(ui[1], ui[2].equals("meia"));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if (ui[0].equals("addEvento")) {
                try {
                    bilheteria.addEvento(ui[1]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if (ui[0].equals("addSetor")) {
                try {
                    bilheteria.addSetor(ui[1], ui[2], Double.parseDouble(ui[3]));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if (ui[0].equals("show")) {
                System.out.println(bilheteria);

            } else if (ui[0].equals("vender")) {
                try {
                    bilheteria.vender(ui[1], ui[2], ui[3]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if (ui[0].equals("eventos")) {
                Evento[] eventos = bilheteria.getRepEventos();
                StringBuilder saida = new StringBuilder();
                for (int i = 0; i < eventos.length; i++) {
                    saida.append(eventos[i]);
                    if (i < eventos.length - 1)
                        saida.append("\n");
                }
                System.out.println(saida);

            } else if (ui[0].equals("pessoas")) {
                Pessoa[] pessoas = bilheteria.getRepPessoas();
                StringBuilder saida = new StringBuilder();
                saida.append("[");
                for (int i = 0; i < pessoas.length; i++) {
                    saida.append(pessoas[i]);
                    if (i < pessoas.length - 1)
                        saida.append(", ");
                }
                saida.append("]");
                System.out.println(saida);

            } else if (ui[0].equals("vendas")) {
                Venda[] vendas = bilheteria.getRepVendas();
                StringBuilder saida = new StringBuilder();
                for (int i = 0; i < vendas.length; i++) {
                    saida.append(vendas[i]);
                    if (i < vendas.length - 1)
                        saida.append("\n");
                }
                System.out.println(saida);

            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
