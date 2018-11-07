package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        int i = 0;
        for (Task task1 : tasks) {
            if (task.getPriority() <= task1.getPriority()) {
                break;
            }
            i++;
        }
        tasks.add(i, task);
    }

    public Task take() {
        return this.tasks.poll();
    }

    public Task takeLast() {
        return this.tasks.getLast();
    }
}