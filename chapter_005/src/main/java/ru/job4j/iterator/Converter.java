package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> iterator = it.next();
            int value = 0;
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                if (iterator.hasNext()) {
                    value = iterator.next();
                } else {
                    while (it.hasNext()) {
                        iterator = it.next();
                        if (iterator.hasNext()) {
                            value = iterator.next();
                            break;
                        }
                    }
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                }
                return value;
            }
        };
    }
}
