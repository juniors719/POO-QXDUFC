/**
 * 1. Fiz tudo, e passou em todos os testes
 * 2. Fiz sozinho
 * 3. Leitura usando Scanner e métodos
 * 4. 15 minutos
 */

package Impressao;

import java.util.Scanner;

class Aluno {

    private String nome;
    private int matricula;
    private String disciplina;
    private float nota;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public void mostrarDetalhes() {
        System.out.println("Nome = " + getNome());
        System.out.println("Matrícula = " + getMatricula());
        System.out.println("Disciplina = " + getDisciplina());
        System.out.println("Nota = " + getNota());
    }

    public void lerDetalhes() {
        Scanner in = new Scanner(System.in);
        setNome(in.nextLine());
        setMatricula(Integer.parseInt(in.nextLine()));
        setDisciplina(in.nextLine());
        setNota(Float.parseFloat(in.nextLine()));
        in.close();
    }

}

class Impressao {
    public static void main(String[] args) {

        Aluno aluno = new Aluno();

        aluno.lerDetalhes();

        aluno.mostrarDetalhes();

    }
}