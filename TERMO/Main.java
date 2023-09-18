package TERMO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User player = new User("Anônimo");
        PalavraSecreta ps = new PalavraSecreta(player);
        System.out.println("------------------TERMO------------------");
        System.out.println("Digite seu nome: ");
        player.setNome(sc.nextLine());
        while (player.getTentativas() <= 6 && !player.isWinner()) {
            System.out.println("Tentativas restantes: " + (6 - player.getTentativas()));
            System.out.println("Digite sua tentativa: ");
            String tentativa = sc.nextLine();
            if (ps.isValid(tentativa))
                System.out.println(ps.row(tentativa));
        }
        System.out.println(player);
        sc.close();
    }
}
