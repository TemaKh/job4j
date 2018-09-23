package ru.job4j.array;

/**
 * Классический поиск перебором.
 */
public class FindLoop {
    /**
     * Поиск числа перебором.
     * @param data масси чисел.
     * @param el элемент который нужно найти в массиве.
     * @return если el удовлетворяет поиску то возвращаем индекс элемента в массиве, иначе -1.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
