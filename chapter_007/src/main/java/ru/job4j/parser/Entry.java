package ru.job4j.parser;

public class Entry {
    private String name;
    private String description;
    private String link;
    private long data;

    public Entry(String name, String description, String link, long data) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public long getData() {
        return data;
    }
}
