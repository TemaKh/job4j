package ru.job4j.list;

public class SimpleQueue<T> {
    private SimpleStack<T> firstStack;
    private SimpleStack<T> secondStack;
    private int count;

    public SimpleQueue() {
        firstStack = new SimpleStack<>();
        secondStack = new SimpleStack<>();
    }

    public void push(T value) {
        firstStack.push(value);
        count++;
    }

    public T poll() {
        for (int i = count; i > 0; i--) {
            secondStack.push(firstStack.poll());
        }
        T result = secondStack.poll();
        count--;
        for (int i = 0; i < count; i++) {
            firstStack.push(secondStack.poll());
        }
        return result;
    }
}
