package ru.job4j.pool;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadPoolTest {
    private ThreadPool pool;
    private CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
    private final int value = 1;

    @Before
    public void setUp() {
        pool = new ThreadPool();
    }

    @Test
    public void whenWork() {
        for (int i = 0; i < 4; i++) {
            pool.work(() ->
                list.add(value)
            );
        }
        pool.shutdown();
        assertThat(list.size(), is(4));
    }
}