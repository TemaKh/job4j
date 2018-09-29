package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MergeTest {
    @Test
    public void whenArraySortedByTrueThenTrue() {
        Merge merge = new Merge();
        int[] input = {1, 3, 5, 7, 8, 11, 34};
        boolean result = merge.isArraySorted(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenOneSortArrayMergeTwoSortArrayThenThreeSortArray() {
        Merge merge = new Merge();
        int[] oneArray = {1, 3, 5, 7, 9};
        int[] twoArray = {2, 4, 6, 8, 10};
        int[] result = merge.mergeArray(oneArray, twoArray);
        int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(result, is(expect));
    }
}
