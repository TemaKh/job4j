package ru.job4j.max;

/**
 * @author Artem Khizhniakov (mailto:artem_khizhniakov@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Max {

    /**
     * Находит максимум из двух чисел.
     * @param first первое число.
     * @param second второе число.
     * @return максимальное число.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Находит максимум из трех чисел.
     * @param first первое число.
     * @param second второе число.
     * @param third третье число.
     * @return максимальное число.
     */
    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }
}
