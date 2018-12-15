package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int size = 0;

    public SimpleArray(int capacity) {
        this.objects = new Object[capacity];
    }

    public void add(T model) {
        if (size == objects.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        objects[size++] = model;
    }

    public void set(int index, T model) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        objects[index] = model;
    }

    public void remove(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == objects.length - 1) {
            objects[size--] = null;
        } else {
            System.arraycopy(objects, index + 1, objects, index, size - 1 - index);
            objects[--size] = null;
        }
    }

    public T get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) objects[index];
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) objects[cursor++];
            }
        };
    }
}
