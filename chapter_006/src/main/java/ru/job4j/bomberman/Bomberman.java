package ru.job4j.bomberman;

import java.util.Random;

public class Bomberman extends Thread {
    private Cell position;
    private Board board;
    private Random random = new Random();

    public Bomberman(Cell position, Board board) {
        this.position = position;
        this.board = board;
    }

    @Override
    public void run() {
       moveBomderman();
    }

    public void moveBomderman() {
        board.getBoard()[position.getX()][position.getY()].lock();
        while (!Thread.currentThread().isInterrupted()) {
            Cell newPosition = new Cell(random.nextInt(board.getSize()), random.nextInt(board.getSize()));
            if (board.move(position, newPosition)) {
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
