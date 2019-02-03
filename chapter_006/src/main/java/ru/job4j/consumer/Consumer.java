package ru.job4j.consumer;

import java.util.concurrent.CopyOnWriteArrayList;

public class Consumer extends Thread {
    private SimpleBlockingQueue<Integer> queue;
    private CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();

    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
            buffer.add(queue.poll());
        }
    }

    public CopyOnWriteArrayList<Integer> getBuffer() {
        return buffer;
    }
}
