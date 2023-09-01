package tabuleiro;

import java.util.*;

public class Board {

    private ArrayList<Integer> traplist;
    private LinkedList<Player> players;
    private boolean running;
    private int boardSize;

    public Board(int nPlayers, int boardSize) {
        this.traplist = new ArrayList<>();
        this.players = new LinkedList<>();
        this.running = true;
        for (int i = 0; i < nPlayers; i++)
            players.add(i, new Player(i + 1));
        this.boardSize = boardSize + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= players.size(); i++) {
            Player p = null;
            for (Player player : players)
                if (player.getLabel() == i)
                    p = player;
            sb.append("player" + p.getLabel() + ": ");
            for (int j = 0; j < this.boardSize; j++) {
                if (j == p.getPos())
                    sb.append(p.getLabel());
                else
                    sb.append('.');
            }
            sb.append("\n");
        }
        sb.append("traps__: ");
        for (int i = 0; i < this.boardSize; i++) {
            if (traplist.contains(i))
                sb.append("x");
            else
                sb.append(".");
        }
        return sb.toString();
    }

    public void addTrap(int pos) {
        this.traplist.add(pos);
    }

    public void rollDice(int value) {
        if (this.running) {
            Player p = players.removeFirst();
            if (p.isFree()) {
                int newPos = p.getPos() + value;
                int possible = this.boardSize - 1 - p.getPos();
                if (value > possible)
                    newPos = this.boardSize - 1;
                p.setPos(newPos);
                System.out.println("player" + p.getLabel() + " andou para " + newPos);
                if (traplist.contains(newPos)) {
                    System.out.println("player" + p.getLabel() + " caiu em uma armadilha");
                    p.setFree(false);
                }
                if (newPos == this.boardSize - 1) {
                    System.out.println("player" + p.getLabel() + " ganhou");
                    this.running = false;
                }
            } else {
                if (value % 2 == 0) {
                    p.setFree(true);
                    System.out.println("player" + p.getLabel() + " se libertou");
                } else {
                    System.out.println("player" + p.getLabel() + " continua preso");
                }
            }
            players.addLast(p);
        } else {
            System.out.println("game is over");
        }
    }

}
