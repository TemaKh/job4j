package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArraySumTest {
    @Test
    public void whenMethodTakesArrayThenReturnSum() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int result = ArraySum.sum(array);
        int expect = 55;
        assertThat(result, is(expect));
    }
}
