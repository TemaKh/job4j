package ru.job4j.consumer;

import java.util.concurrent.CopyOnWriteArrayList;

//public class Consumer extends Thread {
//    private final SimpleBlockingQueue<Integer> queue;
//    private final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
//
//    public Consumer(final SimpleBlockingQueue<Integer> queue) {
//        this.queue = queue;
//    }
//
//    @Override
//    public void run() {
//        while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
//            try {
//                buffer.add(queue.poll());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//
//    public CopyOnWriteArrayList<Integer> getBuffer() {
//        return buffer;
//    }
//}
