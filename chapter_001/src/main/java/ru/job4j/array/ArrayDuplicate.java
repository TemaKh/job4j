package ru.job4j.array;

import java.util.Arrays;

/**
 * Удаление дубликатов в массиве.
 */
public class ArrayDuplicate {
    /**
     * Метод удаляет дубликаты в массиве.
     * @param array масив в котором нужно удалить дубликаты.
     * @return массив не содержащий дубликатов.
     */
    public String[] remove(String[] array) {
        int unique = array.length;
        for (int out = 0; out < unique; out++) {
            for (int in = out + 1; in < unique; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[unique - 1];
                    unique--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, unique);
    }
}
