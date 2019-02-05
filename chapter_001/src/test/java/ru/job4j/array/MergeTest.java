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

    @Test
    public void whenOneSortArrayMoreMergeTwoSortArrayThenThreeSortArray() {
        Merge merge = new Merge();
        int[] oneArray = {1, 3, 5, 7, 9, 11, 12, 13, 14};
        int[] twoArray = {2, 4, 6, 8, 10};
        int[] result = merge.mergeArray(oneArray, twoArray);
        int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        assertThat(result, is(expect));
    }

    @Test
    public void whenOneSortArrayMergeTwoSortArrayMoreThenThreeSortArray() {
        Merge merge = new Merge();
        int[] oneArray = {1, 3, 5, 7, 9};
        int[] twoArray = {2, 4, 6, 8, 10, 11, 12, 13, 14};
        int[] result = merge.mergeArray(oneArray, twoArray);
        int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        assertThat(result, is(expect));
    }

    @Test
    public void whenOneArrayEmptyMergeTwoSortArrayThenThreeSortArray() {
        Merge merge = new Merge();
        int[] oneArray = new int[0];
        int[] twoArray = {2, 4, 6, 8, 10};
        int[] result = merge.mergeArray(oneArray, twoArray);
        int[] expect = {2, 4, 6, 8, 10};
        assertThat(result, is(expect));
    }

    @Test
    public void whenOneSortArrayMergeTwoArrayEmptyThenThreeSortArray() {
        Merge merge = new Merge();
        int[] oneArray = {1, 3, 5, 7, 9};
        int[] twoArray = new int[0];
        int[] result = merge.mergeArray(oneArray, twoArray);
        int[] expect = {1, 3, 5, 7, 9};
        assertThat(result, is(expect));
    }

    @Test
    public void whenOneArrayEmptyMergeTwoArrayEmptyThenThreeSortArray() {
        Merge merge = new Merge();
        int[] oneArray = new int[0];
        int[] twoArray = new int[0];
        int[] result = merge.mergeArray(oneArray, twoArray);
        int[] expect = {};
        assertThat(result, is(expect));
    }
}
