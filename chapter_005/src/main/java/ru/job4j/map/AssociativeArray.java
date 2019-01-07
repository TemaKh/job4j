package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AssociativeArray<K, V> implements Iterable<V> {

    private Node<K, V>[] table;

    private int capacity;

    private float loadFactor = 0.75f;

    private int threshold;

    private int size;

    public AssociativeArray() {
        capacity = 5;
        threshold = (int) (capacity * loadFactor);
        table = new Node[capacity];
    }

    public boolean insert(K key, V value) {
        int index = hash(key);
        if (table[index] != null) {
            if (!table[index].getKey().equals(key)) {
                return false;
            }
            if (table[index].getKey().equals(key)) {
                table[index].setValue(value);
                return true;
            }
        }
        if (size == threshold) {
            resize();
        }
        table[index] = new Node<>(index, key, value, null);
        size++;
        return true;
    }

    public V get(K key) {
        int index = hash(key);
        return table[index].getValue();
    }

    public boolean delete(K key) {
        int index = hash(key);
        if (table[index] == null) {
            return false;
        }
        table[index] = null;
        return true;
    }

    private int hash(Object key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private void resize() {
        capacity = capacity * 2;
        Node<K, V>[] newTable = new Node[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            int index = hash(table[i].getKey());
            newTable[index] = table[i];
        }
        table = newTable;
        threshold = (int) (capacity * loadFactor);
    }

    private static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K kay, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = kay;
            this.value = value;
            this.next = next;
        }

        public void setValue(V newValue) {
            value = newValue;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {

            int cursor = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                while (cursor != table.length) {
                    if (table[cursor] != null) {
                        result = true;
                        break;
                    } else {
                        cursor++;
                    }
                }
                return result;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].getValue();
            }
        };
    }
}
