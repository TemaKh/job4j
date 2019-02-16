package ru.job4j.bomberman;

public class FieldBlocks extends Heroes {
    public FieldBlocks(Cell position, Board board) {
        super(position, board);
    }

    @Override
    public void moveHero() {
        getBoardForHero().getBoard()[getPosition().getX()][getPosition().getY()].lock();
    }
}
