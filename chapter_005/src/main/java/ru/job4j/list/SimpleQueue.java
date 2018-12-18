package ru.job4j.list;

public class SimpleQueue<T> {
    private SimpleStack<T> firstStack;
    private SimpleStack<T> secondStack;
    private int count;
    private int callPoll;

    public SimpleQueue() {
        firstStack = new SimpleStack<>();
        secondStack = new SimpleStack<>();
    }

    public void push(T value) {
        firstStack.push(value);
        count++;
    }

    public T poll() {
        callPoll++;
        if (callPoll == 1) {
            for (int i = 0; i < count; i++) {
                secondStack.push(firstStack.poll());
            }
        }
        return secondStack.poll();
    }
}
