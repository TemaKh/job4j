package ru.job4j.set;

import ru.job4j.list.DynamicContainer;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private DynamicContainer<E> set = new DynamicContainer<>();

    public boolean add(E value) {
        if (checkForUniqueness(value)) {
            return false;
        }
        set.add(value);
        return true;
    }

    private boolean checkForUniqueness(E value) {
        for (E element : set) {
            if (value.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }
}
