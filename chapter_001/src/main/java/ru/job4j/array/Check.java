package ru.job4j.array;

/**
 * Проверяет, что все элементы в массиве являются true или false.
 */
public class Check {
    /**
     * Метод проверяет, что все элементы в массиве являются true или false.
     * @param data массив содержащий true или false.
     * @return true или false.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
          for (int index = 0; index < data.length - 1; index++) {
              if (data[index] != data[index + 1]) {
                  result = false;
                  break;
              }
          }
        return result;
    }
}
