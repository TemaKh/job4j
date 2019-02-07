package ru.job4j.pool;

import org.junit.Before;
import org.junit.Test;

public class ThreadPoolTest {
    private ThreadPool pool;



    @Before
    public void setUp() {
        pool = new ThreadPool();
    }

    @Test
    public void whenWork() {
        for (int i = 0; i < 10; i++) {
            pool.work(() ->
                System.out.println(Thread.currentThread().getName())
            );
        }
        pool.shutdown();
    }
}