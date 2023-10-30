/**
 * 1. Fiz tudo, e passou em todos os testes
 * 2. Fiz sozinho
 * 3. Enum
 * 4. 90 minutos
 */

import java.util.*;

class MsgException extends RuntimeException {
    public MsgException(String message) {
        super(message);
    }
}

abstract class Funcionario {
    protected String nome;
    protected int bonus;
    protected int diarias;
    protected int maxDiarias;

    public Funcionario(String nome) {
        this.nome = nome;
        this.bonus = 0;
        this.diarias = 0;
        this.maxDiarias = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    // se não atingiu o máximo, adicione mais uma diária
    // se atingiu o máximo, lance uma MsgException
    // Adicionar diárias
    // Prof podem receber no máximo 2 diárias, Sta no máximo 1 e Ter não podem
    // receber.
    // Diarias aumentam 100 reais no salario
    public void addDiaria() {
        if (diarias < maxDiarias) {
            diarias++;
        } else {
            throw new MsgException("fail: limite de diarias atingido");
        }
    }

    // retorna bonus + diarias * 100
    public int getSalario() {
        return bonus + diarias * 100;
    }
}

class Professor extends Funcionario {
    protected String classe;

    // inicializa classe e muda maxDiarias para 2
    public Professor(String nome, String classe) {
        super(nome);
        this.classe = classe;
        this.maxDiarias = 2;
    }

    public String getClasse() {
        return classe;
    }

    // lógica do salário do professor
    // // usa o super.getSalario para pegar bonus e diarias
    // O salário de professor deve ser calculado com base na sua classe
    // A 3000
    // B 5000
    // C 7000
    // D 9000
    // E 11000
    public int getSalario() {
        int salario = 0;
        switch (classe) {
            case "A":
                salario = 3000;
                break;
            case "B":
                salario = 5000;
                break;
            case "C":
                salario = 7000;
                break;
            case "D":
                salario = 9000;
                break;
            case "E":
                salario = 11000;
                break;
            default:
                break;
        }
        return super.getSalario() + salario;
    }

    // Exemplo
    // prof:david:C:7000
    @Override
    public String toString() {
        return "prof:" + nome + ":" + classe + ":" + getSalario();
    }
}

class STA extends Funcionario {
    protected int nivel;

    public STA(String nome, int nivel) {
        super(nome);
        this.nivel = nivel;
        this.maxDiarias = 1;
    }

    public int getNivel() {
        return nivel;
    }

    public int getSalario() {
        return super.getSalario() + 3000 + nivel * 300;
    }

    // Exemplo
    // sta:gilmario:3:3900
    public String toString() {
        return "sta:" + nome + ":" + nivel + ":" + getSalario();
    }
}

class Tercerizado extends Funcionario {
    protected int horas;
    protected boolean isSalubre = false;

    public Tercerizado(String nome, int horas, String isSalubre) {
        super(nome);
        this.horas = horas;
        if (isSalubre.equals("sim")) {
            this.isSalubre = true;
        }
    }

    public int getHoras() {
        return horas;
    }

    public String getIsSalubre() {
        return isSalubre ? "sim" : "nao";
    }

    public int getSalario() {
        return super.getSalario() + horas * 4 + (isSalubre ? 500 : 0);
    }

    public void addDiaria() {
        throw new MsgException("fail: terc nao pode receber diaria");
    }

    // Exemplo
    // ter:helder:40:sim:660
    public String toString() {
        return "ter:" + nome + ":" + horas + ":" + getIsSalubre() + ":" + getSalario();
    }
}

class UFC {
    private Map<String, Funcionario> funcionarios = new TreeMap<>();

    public String toString() {
        StringBuilder saida = new StringBuilder();
        for (Funcionario funcionario : funcionarios.values()) {
            saida.append(funcionario + "\n");
        }
        saida.deleteCharAt(saida.length() - 1);
        return saida.toString();
    }

    public Funcionario getFuncionario(String nome) {
        if (!funcionarios.containsKey(nome)) {
            throw new MsgException("fail: funcionario nao existe");
        }
        return funcionarios.get(nome);
    }

    public void addFuncionario(Funcionario funcionario) {
        if (funcionarios.containsKey(funcionario.getNome())) {
            throw new MsgException("fail: funcionario ja existe");
        }
        funcionarios.put(funcionario.getNome(), funcionario);
    }

    public void rmFuncionario(String nome) {
        if (!funcionarios.containsKey(nome)) {
            throw new MsgException("fail: funcionario nao existe");
        }
        funcionarios.remove(nome);
    }

    // reparte o bonus para todos os funcionarios
    public void setBonus(int bonus) {
        int bonusFuncionario = bonus / funcionarios.size();
        for (Funcionario funcionario : funcionarios.values()) {
            funcionario.setBonus(bonusFuncionario);
        }
    }
}

class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UFC ufc = new UFC();
        while (true) {
            try {
                String line = scanner.nextLine();
                System.out.println("$" + line);
                String ui[] = line.split(" ");
                if (ui[0].equals("end")) {
                    break;
                } else if (ui[0].equals("addProf")) {
                    ufc.addFuncionario(new Professor(ui[1], ui[2]));
                } else if (ui[0].equals("addSta")) {
                    ufc.addFuncionario(new STA(ui[1], Integer.parseInt(ui[2])));
                } else if (ui[0].equals("addTer")) {
                    ufc.addFuncionario(new Tercerizado(ui[1], Integer.parseInt(ui[2]), ui[3]));
                } else if (ui[0].equals("rm")) {
                    ufc.rmFuncionario(ui[1]);
                } else if (ui[0].equals("showAll")) {
                    System.out.println(ufc);
                } else if (ui[0].equals("show")) {
                    System.out.println(ufc.getFuncionario(ui[1]));
                } else if (ui[0].equals("addDiaria")) {
                    ufc.getFuncionario(ui[1]).addDiaria();
                } else if (ui[0].equals("setBonus")) {
                    ufc.setBonus(Integer.parseInt(ui[1]));
                } else {
                    System.out.print("fail: comando invalido");
                }
            } catch (MsgException me) {
                System.out.println(me.getMessage());
            }
        }
        scanner.close();
    }
}