package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    private SimpleArray<Integer> simpleArray;
    private Iterator<Integer> iterator;

    @Before
    public void setUp() {
        simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        iterator = simpleArray.iterator();
    }

    @Test
    public void whenAddElement() {
        simpleArray = new SimpleArray<>(2);
        simpleArray.add(1);
        assertThat(simpleArray.get(0), is(1));
    }

    @Test
    public void whenGetElement() {
        assertThat(simpleArray.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void invocationGetMethodShouldThrowIndexOutOfBoundsException() {
        simpleArray.get(4);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void invocationAddMethodShouldThrowArrayIndexOutOfBoundsException() {
        simpleArray.get(5);
    }

    @Test
    public void whenSetElement() {
        simpleArray.set(3, 5);
        assertThat(simpleArray.get(3), is(5));
    }

    @Test
    public void whenRemoveElement() {
        simpleArray.remove(2);
        assertThat(simpleArray.get(2), is(4));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(4));
        iterator.next();
    }
}