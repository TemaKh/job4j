package ru.job4j.lambda;

import java.util.Arrays;

public class ArraySum {
    public static int sum(int[] array) {
        return Arrays.stream(array).reduce((i1, i2) -> i1 + i2).orElse(0);
    }
}
