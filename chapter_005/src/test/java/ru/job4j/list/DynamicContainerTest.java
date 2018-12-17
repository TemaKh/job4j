package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DynamicContainerTest {

    private DynamicContainer<Integer> dynamicContainer;
    private Iterator<Integer> iterator;

    @Before
    public void setUp() {
        dynamicContainer = new DynamicContainer<>();
        dynamicContainer.add(1);
        dynamicContainer.add(2);
        dynamicContainer.add(3);
        dynamicContainer.add(4);
        iterator = dynamicContainer.iterator();
        dynamicContainer.add(5);

    }

    @Test
    public void whenGetByIndexReturnElement() {
        assertThat(dynamicContainer.get(0), is(1));
    }

    @Test
    public void whenAddElementInFullContainerThenContainerMustIncrease() {
        dynamicContainer.add(6);
        dynamicContainer.add(7);
        assertThat(dynamicContainer.get(6), is(7));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCreatingIteratorInContainerHasChangedThenThrowException() {
            iterator.next();
    }
}