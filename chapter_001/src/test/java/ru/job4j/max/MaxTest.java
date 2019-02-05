package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Khizhniakov (mailto:artem_khizhniakov@mail.ru).
 * @version $Id$.
 * @since 19.09.2018.
 */
public class MaxTest {
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenSecondLessFirst() {
        Max maxim = new Max();
        int result = maxim.max(2, 1);
        assertThat(result, is(2));
    }
    @Test
    public void whenFirstLessSecondAndLessThird() {
        Max maxim = new Max();
        int result = maxim.max(1, 5, 6);
        assertThat(result, is(6));
    }
    @Test
    public void whenSecondLessFirstAndLessThird() {
        Max maxim = new Max();
        int result = maxim.max(5, 1, 6);
        assertThat(result, is(6));
    }
    @Test
    public void whenThirdLessSecondAndLessFirst() {
        Max maxim = new Max();
        int result = maxim.max(5, 6, 1);
        assertThat(result, is(6));
    }
}
