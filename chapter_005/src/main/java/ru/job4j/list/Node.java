package ru.job4j.list;

public class Node<T> {
    private T date;
    Node<T> next;

    public Node(T date) {
        this.date = date;
    }
}
