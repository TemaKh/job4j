package ru.job4j.pool;

import ru.job4j.consumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(() -> {
               while (!Thread.currentThread().isInterrupted()) {
                   if (!tasks.isEmpty()) {
                       try {
                           tasks.poll().run();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                           Thread.currentThread().interrupt();
                       }
                   }
               }
            });
            threads.add(thread);
            thread.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
       for (Thread thread : threads) {
           thread.interrupt();
       }
    }
}
