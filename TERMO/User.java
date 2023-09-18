/**
 * Classe que representa um usuário do jogo.
 * 
 * Atributos:
 * - tentativas: número de tentativas do usuário.
 * - nome: nome do usuário.
 * - pontos: número de pontos do usuário.
 * - isWinner: booleano que indica se o usuário venceu o jogo.
 * 
 */

package TERMO;

public class User {

    private int tentativas;
    private String nome;
    private int pontos;
    private boolean isWinner;

    public User(String nome) {
        this.nome = nome;
        this.tentativas = 0;
        this.pontos = 0;
        this.isWinner = false;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nTentativas: " + this.tentativas + "\nPontos: " + this.pontos;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }
}
