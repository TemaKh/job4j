package ru.job4j.сoffeemachine;

import java.util.Arrays;

public class CoffeeMachine {
    public int[] changes(int value, int price) {
        int[] coins = {1, 2, 5, 10};
        int change = value - price;
        int[] result = new int[change];
        int newLength = 0;
        int index = coins.length - 1;
        while (change != 0) {
            while (change - coins[index] < 0) {
                index--;
            }
            change -= coins[index];
            result[newLength++] = coins[index];
        }
        return Arrays.copyOf(result, newLength);
    }
}
