package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {
    private SimpleSet<Integer> set;
    private Iterator<Integer> iterator;

    @Before
    public void setUp() {
        set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(2);
        set.add(1);
        iterator = set.iterator();
    }

    @Test
    public void whenNoDuplicates() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenAddDuplicateReturnFalse() {
        assertThat(set.add(4), is(true));
        assertThat(set.add(3), is(false));
    }
}