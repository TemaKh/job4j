package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements Iterable<E> {

    private int size;
    private Node<E> first;
    private int modCount;

    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        modCount++;
    }

    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    public E delete() {
        if (first == null) {
            return null;
        }
        Node<E> temp = this.first;
        this.first = this.first.next;
        this.size--;
        return temp.date;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private static class Node<E> {

        E date;
        Node<E> next;

        public Node(E date) {
            this.date = date;
        }
    }

    public int size() {
        return this.size;
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
                Node<E> result = first;
                for (int i = 0; i < cursor; i++) {
                    result = result.next;
                }
                cursor++;
                return result.date;
            }

            private void checkModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
