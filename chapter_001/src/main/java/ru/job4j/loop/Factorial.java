package ru.job4j.loop;

/**
 * Вычисления факториала.
 */
public class Factorial {
    /**
     * Метод вычисляет факториал числа.
     * @param n число для вычисления факториала.
     * @return фаториал.
     */
    public int calc(int n) {
        int factorial = 1;
        if (n == 0) {
            return factorial;
        }
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
