package ru.job4j.bomberman;

import java.util.Random;

public abstract class Heroes extends Thread {
    private Cell position;
    private Board board;
    private Random random = new Random();

    public Heroes(Cell position, Board board) {
        this.position = position;
        this.board = board;
    }

    @Override
    public void run() {
        moveHero();
    }

    public void moveHero() {
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

    public Board getBoardForHero() {
        return board;
    }

    public Cell getPosition() {
        return position;
    }
}
