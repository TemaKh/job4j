package ru.job4j.consumer;

import java.util.stream.IntStream;

public class Producer extends Thread {
    private SimpleBlockingQueue<Integer> queue;

    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        IntStream.range(0, 5).forEach(queue::offer);
    }
}
