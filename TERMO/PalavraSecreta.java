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

    static String vermelho = "\u001B[31m";
    static String verde = "\u001B[32m";
    static String amarelo = "\u001B[33m";
    static String reset = "\u001B[0m";

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

    /**
     * 
     * @brief Retorna uma string com a tentativa do usuário, com as cores
     * 
     * @param tentativa tentativa do usuário
     * @return
     */
    public String row(String tentativa) {
        // 0 representa vermelho, 1 representa verde, 2 representa amarelo
        int[] booleanChars = new int[5];
        int[] booleanCharsWordOfDay = new int[5];
        // palavra usada para remover as letras verdes e amarelas
        ArrayList<Character> auxWordList = new ArrayList<>();
        String resultado = "";
        tentativa = tentativa.toLowerCase();
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
        // procurar primeiro todas as letras verdes
        for (int i = 0; i < 5; i++) {
            if (this.wordOfTheDay.charAt(i) == tentativa.charAt(i)) {
                booleanChars[i] = 1; // 1 representa o verde
                auxWordList.remove(i); // remover a letra da palavra
            }
        }
        // procurar agora as letras amarelas, mas ignorando as letras verdes e amaerelas
        for (int i = 0; i < 5; i++) {
            if (booleanChars[i] == 0) {
                for (char c : auxWord) {
                    if (c == tentativa.charAt(i)) {
                        booleanCharsWordOfDay[i] = 2; // 1 representa o amarelo
                        auxWord[i] = ' '; // remover a letra da palavra
                        break;
                    }
                }
            }
        }
        for (char c : auxWord)
            System.out.println(c);

        // montar a string de resultado
        for (int i = 0; i < 5; i++) {
            if (booleanChars[i] == 0) {
                resultado += vermelho + tentativa.charAt(i) + reset;
            } else if (booleanChars[i] == 1) {
                resultado += verde + tentativa.charAt(i) + reset;
            } else {
                resultado += amarelo + tentativa.charAt(i) + reset;
            }
        }
        this.player.setTentativas(this.player.getTentativas() + 1);
        return resultado;
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
