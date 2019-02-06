package ru.job4j.nonblocking;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NonBlockingCasheTest {
    private NonBlockingCashe cashe;
    private Base base1;
    private Base base2;

    @Before
    public void setUp() {
        cashe = new NonBlockingCashe();
        base1 = new Base(1, 0);
        base2 = new Base(2, 0);
        cashe.add(base1);
        cashe.add(base2);
    }

    @Test
    public void whenUpdateDate() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            try {
                cashe.update(base1);
            } catch (OptimisticException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                cashe.update(base1);
            } catch (OptimisticException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        assertThat(base1.getVersion(), is(2));
    }
}