package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final ReentrantLock[][] board;
    private final Bomberman bomberman;
    private final int size;
    Random random = new Random();
    List<Heroes> badHeroes = new ArrayList<>();

    public Board(int size, int difficult) {
        this.size = size;
        this.board = new ReentrantLock[size][size];
        bomberman = new Bomberman(new Cell(0, 0), this);
        levelOfDifficulti(difficult);
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

    private void levelOfDifficulti(int diff) {
        for (int i = 0; i < diff; i++) {
            badHeroes.add(new Demon(new Cell(random.nextInt(size), random.nextInt(size)), this));
            badHeroes.add(new FieldBlocks(new Cell(random.nextInt(size), random.nextInt(size)), this));
        }
    }

    public Bomberman getBomberman() {
        return bomberman;
    }

    public List<Heroes> getBadHeroes() {
        return badHeroes;
    }
}
