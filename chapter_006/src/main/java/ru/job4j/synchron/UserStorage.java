package ru.job4j.synchron;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class UserStorage {

    private int capacity = 10;

    @GuardedBy("this")
    private User[] container = new User[capacity];

    private int size;

    public synchronized boolean add(User user) {
        container[size++] = user;
        return true;
    }

    public synchronized boolean update(User user) {
        int index = searchIndexByUser(user);
        if (index == -1) {
            return false;
        }
        container[index] = user;
        return true;
    }

    public synchronized boolean delete(User user) {
        int index = searchIndexByUser(user);
        if (index == -1) {
            return false;
        }
        if (index == container.length - 1) {
            container[size--] = null;
            return true;
        }
        System.arraycopy(container, index + 1, container, index, size - 1 - index);
        container[--size] = null;
        return true;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        int indexFromId = searchIndexById(fromId);
        int indexToId = searchIndexById(toId);
        container[indexFromId].setAmount(container[indexFromId].getAmount() - amount);
        container[indexToId].setAmount(container[indexToId].getAmount() + amount);
        return true;
    }

    public synchronized User getUser(int id) {
        int index = searchIndexById(id);
        return container[index];
    }
    private int searchIndexByUser(User user) {
        for (int i = 0; i < size; i++) {
            if (container[i].getId() == user.getId()) {
                return i;
            }
        }
        return -1;
    }

    private int searchIndexById(int id) {
        for (int i = 0; i < size; i++) {
            if (container[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
