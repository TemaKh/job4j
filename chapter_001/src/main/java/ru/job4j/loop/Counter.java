package ru.job4j.loop;

/**
 * Подсчет суммы чётных чисел в заданном диапазоне.
 */
public class Counter {

    /**
     * Подсчитываем суму чётных чисел.
     * @param start начальное число.
     * @param finish конечное число.
     * @return сумму.
     */
    public int add(int start, int finish) {
        int temp = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                temp += i;
            }
        }
        return temp;
    }
}
