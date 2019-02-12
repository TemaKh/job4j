package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final ReentrantLock[][] board;
    private final Bomberman bomberman;
    private final int size;

    public Board(int size) {
        this.size = size;
        this.board = new ReentrantLock[size][size];
        bomberman = new Bomberman(new Cell(0, 0), this);
    }

    public boolean move(Cell sourse, Cell dest) {
        boolean result = false;
        try {
            if (board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {
                board[sourse.getX()][sourse.getY()].unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getSize() {
        return this.size;
    }

    public ReentrantLock[][] getBoard() {
        return this.board;
    }
}
