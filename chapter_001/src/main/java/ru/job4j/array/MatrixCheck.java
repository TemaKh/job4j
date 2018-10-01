package ru.job4j.array;

/**
 * Проверяет, что все элементы по диагоналям равны true или false.
 */
public class MatrixCheck {

    /**
     * Метод должен проверить, что все элементы по диагоналям равны true или false.
     * @param data массив заполненный значениями true или false.
     * @return true ли false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length - 1; i++) {
            if ((data[i][i] != data[i + 1][i + 1]) || (data[data.length - 1 - i][i] != data[data.length - 2 - i][i + 1])) {
                result = false;
                break;
            }
        }
        return result;
    }
}
