package ru.job4j.bomberman;

import java.util.Random;

public class Bomberman extends Heroes {
    public Bomberman(Cell position, Board board) {
        super(position, board);
    }

    @Override
    public void run() {

    }

    public void moveBomderman(int x, int y) {
        Cell position = getPosition();
        getBoardForHero().getBoard()[position.getX()][position.getY()].lock();
        while (!Thread.currentThread().isInterrupted()) {
            Cell newPosition = new Cell(x, y);
            if (getBoardForHero().move(position, newPosition)) {
                position = newPosition;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
