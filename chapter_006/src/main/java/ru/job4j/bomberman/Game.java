package ru.job4j.bomberman;

public class Game {

    public void start(int sizeBoard, int difficultGame) {
        Board board = new Board(sizeBoard, difficultGame);
        board.getBomberman().start();
        for (Heroes hero : board.getBadHeroes()) {
            if (hero instanceof Demon) {
                hero.setDaemon(true);
            }
            hero.start();
        }
    }
}
