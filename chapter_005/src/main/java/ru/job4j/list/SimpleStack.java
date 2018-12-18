package ru.job4j.list;

public class SimpleStack<T> {
    private SimpleLinkedList<T> stack;

    public SimpleStack() {
        stack = new SimpleLinkedList<T>();
    }

    /**
     * Метод размещает элемент на вершине стека.
     */
    public void push(T value) {
        stack.add(value);
    }

    /**
     * Метод возвращает значение и удаляет его из коллекции.
     */
    public T poll() {
        return stack.delete();
    }
}
