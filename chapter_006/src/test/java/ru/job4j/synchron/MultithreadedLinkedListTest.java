package ru.job4j.synchron;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MultithreadedLinkedListTest {
    private class ThreadAdd extends Thread {
        private final MultithreadedLinkedList<Integer> list;

        private ThreadAdd(MultithreadedLinkedList<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            list.add(1);
            list.add(2);
        }
    }

    @Test
    public void whenAddAndIterator() throws InterruptedException {
        final MultithreadedLinkedList<Integer> list = new MultithreadedLinkedList<>();
        Thread thread = new ThreadAdd(list);
        thread.start();
        list.add(3);
        list.add(4);
        thread.join();
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
    }
}