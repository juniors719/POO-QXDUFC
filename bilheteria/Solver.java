package bilheteria;

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
            String a[] = line.split(" ");
            if (line.equals("end")) {
                break;
            } else if (a[0].equals("addPessoa")) {
                try {
                    bilheteria.addPessoa(a[1], a[2].equals("meia"));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (a[0].equals("addEvento")) {
                try {
                    bilheteria.addEvento(a[1], Double.parseDouble(a[2]));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (a[0].equals("show")) {
                System.out.println(bilheteria);
            } else if (a[0].equals("vender")) {
                try {
                    bilheteria.vender(a[1], a[2]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (a[0].equals("eventos")) {
                Evento eventos[] = bilheteria.getRepEventos();
                StringBuilder saida = new StringBuilder();
                saida.append("[");
                for (int i = 0; i < eventos.length; i++) {
                    saida.append(eventos[i]);
                    if (i < eventos.length - 1)
                        saida.append(", ");
                }
                saida.append("]");
                System.out.println(saida);
            } else if (a[0].equals("pessoas")) {
                Pessoa pessoas[] = bilheteria.getRepPessoas();
                StringBuilder saida = new StringBuilder();
                saida.append("[");
                for (int i = 0; i < pessoas.length; i++) {
                    saida.append(pessoas[i]);
                    if (i < pessoas.length - 1)
                        saida.append(", ");
                }
                saida.append("]");
                System.out.println(saida);
            } else if (a[0].equals("vendas")) {
                Venda vendas[] = bilheteria.getRepVendas();
                StringBuilder saida = new StringBuilder();
                saida.append("[");
                for (int i = 0; i < vendas.length; i++) {
                    saida.append(vendas[i]);
                    if (i < vendas.length - 1)
                        saida.append(", ");
                }
                saida.append("]");
                System.out.println(saida);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
