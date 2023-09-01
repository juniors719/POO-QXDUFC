package Novamedia;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

class Aluno {
    private String nome;
    private float notas[];
    private float media;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float[] getNotas() {
        return notas;
    }

    public void setNotas(float[] notas) {
        this.notas = notas;
    }

    public float getMedia() {
        return media;
    }

    public void lerDetalhes() {
        Scanner in = new Scanner(System.in);
        setNome(in.nextLine());
        float notas[] = new float[3];
        for (int i = 0; i < 3; i++) {
            notas[i] = Float.parseFloat(in.nextLine());
        }
        setNotas(notas);
        in.close();
    }

    public void calculaMedia() {
        float soma = 0;
        for (int i = 0; i < 3; i++) {
            soma += notas[i];
        }
        media = soma / 3;
    }

    public void imprimirMedia() {
        NumberFormat format = new DecimalFormat("#0,00");
        System.out.println(format.format(getMedia()));
    }

}

public class Novamedia {
    public static void main(String[] args) {
        Aluno aluno = new Aluno();
        aluno.lerDetalhes();
        aluno.calculaMedia();
        aluno.imprimirMedia();
    }
}
