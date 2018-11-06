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
        if (tasks.size() == 0) {
            tasks.add(task);
        }
        for (Task taskit : tasks) {
            if (task.getPriority() < taskit.getPriority()) {
                tasks.add(tasks.indexOf(taskit), task);
                break;
            }
        }
        if (tasks.getLast().getPriority() < task.getPriority()) {
            tasks.addLast(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }

    public Task takeLast() {
        return this.tasks.getLast();
    }
}