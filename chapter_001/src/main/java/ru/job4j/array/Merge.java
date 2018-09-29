package ru.job4j.array;

public class Merge {
    /**
     * Метод проверяет является ли массив отсортированным.
     * @param array проверяемый массив.
     * @return true если массив отсортирован.
     */
    public boolean isArraySorted(int[] array) {
        boolean result = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Метод соединяет два отсортированных массива, возвращая результирующий отсортированный массив.
     * @param oneArray первый отсортированный массив.
     * @param twoArray второй отсортированный массив.
     * @return результирующий отсортированный массив.
     */
    public int[] mergeArray(int[] oneArray, int[] twoArray) {
        int[] resultArray = new int[oneArray.length + twoArray.length];
        int countOne = 0;
        int countTwo = 0;
        int countResult = 0;
        while (countOne < oneArray.length && countTwo < twoArray.length) {
            if (oneArray[countOne] < twoArray[countTwo]) {
                resultArray[countResult++] = oneArray[countOne++];
            } else {
                resultArray[countResult++] = twoArray[countTwo++];
            }
        }
        while (countOne < oneArray.length) {
            resultArray[countResult++] = oneArray[countOne++];
        }

        while (countTwo < twoArray.length) {
            resultArray[countResult++] = twoArray[countTwo++];
        }
        return resultArray;
    }
}
