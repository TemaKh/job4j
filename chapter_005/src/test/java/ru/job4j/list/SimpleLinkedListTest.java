package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleLinkedListTest {
    private SimpleLinkedList<Integer> simpleLinkedList;
    private Iterator<Integer> iterator;

    @Before
    public void setUp() {
        simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add(1);
        simpleLinkedList.add(2);
        simpleLinkedList.add(3);
        iterator = simpleLinkedList.iterator();
        simpleLinkedList.add(4);
    }

    @Test
    public void whenGetByIndexReturnElement() {
        assertThat(simpleLinkedList.get(2), is(2));
    }

    @Test
    public void whenAddElement() {
        simpleLinkedList.add(5);
        assertThat(simpleLinkedList.get(0), is(5));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCreatingIteratorInListHasChangedThenThrowException() {
        iterator.next();
    }
}