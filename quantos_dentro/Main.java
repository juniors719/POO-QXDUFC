package quantos_dentro;

import java.util.Scanner;

class Ponto {
    float x;
    float y;

    float distancia(Ponto p) {
        float dx = x - p.x;
        float dy = y - p.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    boolean dentro(Circulo c) {
        float d = distancia(c.centro);
        return d <= c.raio;
    }
}

class Circulo {
    Ponto centro;
    float raio;
}

public class Main {
    public static void main(String[] args) {

        int quantDentro = 0;

        Scanner in = new Scanner(System.in);
        int quantPontos = in.nextInt();
        Ponto[] pontos = new Ponto[quantPontos];
        for (int i = 0; i < quantPontos; i++) {
            Ponto p = new Ponto();
            p.x = in.nextFloat();
            p.y = in.nextFloat();
            pontos[i] = p;
        }
        Circulo c = new Circulo();
        c.centro = new Ponto();
        c.centro.x = in.nextFloat();
        c.centro.y = in.nextFloat();
        c.raio = in.nextFloat();
        for (int i = 0; i < quantPontos; i++) {
            if (pontos[i].dentro(c)) {
                quantDentro++;
            }
        }
        System.out.println(quantDentro);
        in.close();        
    }

}