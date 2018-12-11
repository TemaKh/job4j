package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {

    private int[][] array;
    private int out = 0;
    private int in = 0;

    public MatrixIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
       return out != array.length && in != array[out].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int value = array[out][in];
        if (in != array[out].length) {
            in++;
        }
        if (in == array[out].length) {
            out++;
            in = 0;
        }
        return value;
    }
}
