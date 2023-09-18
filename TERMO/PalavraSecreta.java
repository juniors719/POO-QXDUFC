/**
 * Classe que representa a palavra secreta do jogo.
 * 
 * Atributos:
 * - wordOfTheDay: palavra secreta do dia.
 * - day: dia do mês.
 * - player: jogador.
 * 
 * Métodos:
 * - row(String tentativa): retorna uma string com a tentativa do usuário, com as
 * cores correspondentes.
 * - isValid(String tentativa): verifica se a tentativa do usuário é válida.
 * - save(String resultado): salva o resultado do jogo num arquivo.
 * 
 */

package TERMO;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PalavraSecreta {

    private String wordOfTheDay;
    private int day;
    private User player;

    public PalavraSecreta(User player) {
        LocalDate dataAtual = LocalDate.now();
        this.day = dataAtual.getDayOfMonth();
        this.player = player;

        // Inicializar uma lista para armazenar as palavras do arquivo
        List<String> palavras = new ArrayList<>();

        // Ler o arquivo de palavras
        try (BufferedReader br = new BufferedReader(
                new FileReader("C:\\Users\\junin\\codes\\Java\\POO-QXDUFC\\POO-QXDUFC\\TERMO\\palavras.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                palavras.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verificar se o dia do mês está dentro dos limites da lista de palavras
        if (this.day >= 1 && this.day <= palavras.size()) {
            // Obter a palavra correspondente ao dia do mês
            this.wordOfTheDay = palavras.get(this.day - 1);
        } else {
            System.out.println("Não há palavra disponível para o dia atual.");
        }
    }

    public String row(String tentativa) {
        String booleanCharPosition = "00000";
        String vermelho = "\u001B[31m";
        String verde = "\u001B[32m";
        String amarelo = "\u001B[33m";
        String reset = "\u001B[0m";
        String resultado = "";
        if (this.player.getTentativas() == 6) {
            this.player.setPontos(0);
            this.player.setWinner(false);
            resultado = this.player.getNome() + " - " + this.player.getPontos() + " pontos";
            save(resultado);
        }
        if (isCorrect(tentativa)) {
            this.player.setWinner(true);
            this.player.setTentativas(this.player.getTentativas() + 1);
            this.player.setPontos(calcPoints(this.wordOfTheDay.length(), 0));
            resultado = this.player.getNome() + " - " + this.player.getPontos() + " pontos";
            save(resultado);
            return verde + tentativa + reset + "\nVocê acertou!";
        }
        String saida = "";
        for (int i = 0; i < this.wordOfTheDay.length(); i++) {
            String letra = String.valueOf(this.wordOfTheDay.charAt(i)).toLowerCase();
            String letraTentativa = String.valueOf(tentativa.charAt(i)).toLowerCase();
            if (wordOfTheDay.contains(letraTentativa)) {
                // int index = wordOfTheDay.indexOf(letraTentativa);
                if (letraTentativa.equals(letra)) {
                    // contém e está na posição correta -> verde
                    saida += verde + tentativa.charAt(i) + reset;
                } else {
                    // contém e está na posição incorreta -> amarelo
                    saida += amarelo + tentativa.charAt(i) + reset;
                }
            } else {
                // não contém -> vermelho
                saida += vermelho + tentativa.charAt(i) + reset;
            }
        }
        this.player.setTentativas(this.player.getTentativas() + 1);
        this.player.setPontos(calcPoints(this.wordOfTheDay.length() - tentativa.length(), tentativa.length()));
        return saida;
    }

    private int calcPoints(int qtdVerde, int qtdAmarelo) {
        // a quantidade de tentativas restantes é o peso
        int pontos = 0;
        pontos += qtdVerde * 10;
        pontos += qtdAmarelo * 5;
        pontos += (6 - this.player.getTentativas()) * 10;
        return pontos;
    }

    private boolean isCorrect(String tentativa) {
        return this.wordOfTheDay.equals(tentativa.toLowerCase());
    }

    public boolean isValid(String tentativa) {
        if (tentativa.length() != 5) {
            System.out.println("A tentativa deve conter 5 caracteres.");
            return false;
        }
        return true;
    }

    // metodo para salvar o resultado do jogo num arquivo
    public void save(String resultado) {
        try {
            String nomeArquivo = "C:\\Users\\junin\\codes\\Java\\POO-QXDUFC\\POO-QXDUFC\\TERMO\\resultado.txt";
            FileWriter fw = new FileWriter(nomeArquivo, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(resultado);
            pw.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar o resultado do jogo.");
        }
    }

}
