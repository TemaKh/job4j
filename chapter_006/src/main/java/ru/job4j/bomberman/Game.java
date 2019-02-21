package ru.job4j.bomberman;

public class Game {

    public void start(int sizeBoard, int difficultGame) {
        Board board = new Board(sizeBoard, difficultGame);
        board.getBomberman().start();
        for (int i = 0; i < board.getDemons().size(); i++) {
            board.getDemons().get(i).setDaemon(true);
            board.getDemons().get(i).start();
            board.getBlocks().get(i).start();
        }
    }
}
