package ru.job4j.list;

public class SimpleQueue<T> {
    private SimpleStack<T> firstStack;
    private SimpleStack<T> secondStack;

    public SimpleQueue() {
        firstStack = new SimpleStack<>();
        secondStack = new SimpleStack<>();
    }

    public void push(T value) {
        firstStack.push(value);
    }

    public T poll() {
        if (secondStack.isEmpty()) {
            while (!firstStack.isEmpty()) {
                secondStack.push(firstStack.poll());
            }
        }
        return secondStack.poll();
    }
}
