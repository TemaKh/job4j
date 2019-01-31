package ru.job4j.synchron;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleLinkedList;

import java.util.Collections;
import java.util.Iterator;

@ThreadSafe
public class MultithreadedLinkedList<E> implements Iterable<E> {
    @GuardedBy("this")
    private SimpleLinkedList<E> simpleLinkedList = new SimpleLinkedList<>();

    public synchronized void add(E date) {
        simpleLinkedList.add(date);
    }

    public synchronized E get(int index) {
        return simpleLinkedList.get(index);
    }

    public synchronized E delete() {
        return simpleLinkedList.delete();
    }

    public synchronized boolean isEmpty() {
        return simpleLinkedList.isEmpty();
    }

    public synchronized int size() {
        return simpleLinkedList.size();
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy(simpleLinkedList).iterator();
    }

    private SimpleLinkedList<E> copy(SimpleLinkedList<E> list) {
        SimpleLinkedList<E> copyList = new SimpleLinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            copyList.add(list.get(i));
        }
        return copyList;
    }
}
