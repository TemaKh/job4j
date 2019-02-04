package ru.job4j.consumer;

import java.util.stream.IntStream;

public class Producer extends Thread {
    private final SimpleBlockingQueue<Integer> queue;

    public Producer(final SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        IntStream.range(0, 5).forEach(queue::offer);
    }
}
