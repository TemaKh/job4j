package ru.job4j.array;

/**
 * Перевернуть массив.
 */
public class Turn {
    /**
     * Перевернуть массив.
     * @param array массив чисел который нужно перевернуть.
     * @return перевернутый массив array.
     */
    public int[] turn(int[] array) {
        for (int index = 0; index < array.length / 2; index++) {
            int temp = array[index];
            array[index] = array[array.length - index - 1];
            array[array.length - index - 1] = temp;
        }
        return array;
    }
}
