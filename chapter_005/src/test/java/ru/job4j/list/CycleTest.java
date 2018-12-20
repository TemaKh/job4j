package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CycleTest {
    private Node<Integer> first;
    private Node<Integer> two;
    private Node<Integer> third;
    private Node<Integer> four;

    @Before
    public void setUp() {
        first = new Node<>(1);
        two = new Node<>(2);
        third = new Node<>(3);
        four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
    }

    @Test
    public void whenHasCycleThenTrue() {
        assertThat(Cycle.hasCycle(first), is(true));
    }

    @Test
    public void whenHasNoCycleThenFalse() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;
        assertThat(Cycle.hasCycle(first), is(false));
    }
}