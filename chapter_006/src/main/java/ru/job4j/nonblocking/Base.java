package ru.job4j.nonblocking;

public class Base {
    private int id;
    private int version;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return this.id;
    }

    public int getVersion() {
        return this.version;
    }

    public void updateVersion() {
        this.version++;
    }
}
