package ru.job4j.array;

/**
 * Двухмерный массив. Таблица умножения.
 */
public class Matrix {
    /**
     * Двухмерный массив. Таблица умножения.
     * @param size указывает на размер таблицы.
     * @return таблицу умножения.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
