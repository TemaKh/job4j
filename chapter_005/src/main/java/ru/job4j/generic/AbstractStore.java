package ru.job4j.generic;

import java.util.Random;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> simpleArray;

    private static final Random RN = new Random();

    public AbstractStore(int size) {
        simpleArray = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        simpleArray.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = this.findIndex(id);
        if (index >= 0) {
            simpleArray.set(index, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int index = this.findIndex(id);
        if (index >= 0) {
            simpleArray.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        int index = this.findIndex(id);
        return simpleArray.get(index);
    }

    private int findIndex(String id) {
        for (int index = 0; index < simpleArray.size(); index++) {
            if (simpleArray.get(index).getId().equals(id)) {
                return index;
            }
        }
        return -1;
    }
}
