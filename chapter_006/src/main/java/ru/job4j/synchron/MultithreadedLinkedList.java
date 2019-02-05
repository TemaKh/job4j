package ru.job4j.synchron;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleLinkedList;

import java.util.Iterator;
import java.util.LinkedList;

@ThreadSafe
public class MultithreadedLinkedList<E> implements Iterable<E> {
    @GuardedBy("this")
    private LinkedList<E> linkedList = new LinkedList<>();

    public synchronized void add(E date) {
        linkedList.add(date);
    }

    public synchronized E get(int index) {
        return linkedList.get(index);
    }

    public synchronized boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public synchronized int size() {
        return linkedList.size();
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy(linkedList).iterator();
    }

    private SimpleLinkedList<E> copy(LinkedList<E> list) {
        SimpleLinkedList<E> copyList = new SimpleLinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            copyList.add(list.get(i));
        }
        return copyList;
    }
}
