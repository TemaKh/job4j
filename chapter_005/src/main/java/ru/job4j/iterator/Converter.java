package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> iterator = it.next();
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (iterator.hasNext()) {
                    result = true;
                } else {
                    while (it.hasNext()) {
                        iterator = it.next();
                        result = iterator.hasNext();
                        if (result) {
                            break;
                        }
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                return iterator.next();
            }
        };
    }
}
