package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicContainer<E> implements Iterable<E> {

    private int capacity = 4;

    private int newCapacity;

    private Object[] container = new Object[capacity];

    private int size;

    private int modCount;

    public void add(E value) {
        ensureCapacity(size + 1);
        container[size++] = value;
        modCount++;
    }

    public E get(int index) {
        return (E) container[index];
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity == container.length) {
            newCapacity = (capacity * 3) / 2 + 1;
            Object[] oldContainer = container;
            container = new Object[newCapacity];
            System.arraycopy(oldContainer, 0, container, 0, capacity);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int cursor = 0;

            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public E next() {
                checkModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[cursor++];
            }

            private void checkModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
