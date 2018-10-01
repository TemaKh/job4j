package ru.job4j.array;

/**
 * Сортировка массива.
 */
public class BubbleSort {

    /**
     * Соритровка массива целых чисел, используя алгоритм сортировки пузырьком.
     * @param array неосортированный массив.
     * @return отсортированный массив.
     */
    public int[] sort(int[] array) {
        for (int out = array.length - 1; out > 1; out--) {
            for (int in = 0; in < out; in++) {
                if (array[in] > array[in + 1]) {
                    int temp = array[in];
                    array[in] = array[in + 1];
                    array[in + 1] = temp;
                }
            }
        }
        return array;
    }
}
