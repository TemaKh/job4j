package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {
    private SimpleStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void whenCallMethodPollThenReturnsValueAndRemovesItFromTheStack() {
        assertThat(stack.poll(), is(3));
    }

    @Test
    public void whenCallPushMethodThenPushElementOnTopStack() {
        stack.push(4);
        assertThat(stack.poll(), is(4));

    }
}