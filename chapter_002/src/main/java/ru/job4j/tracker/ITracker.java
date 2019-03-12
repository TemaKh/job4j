package ru.job4j.tracker;

import java.util.List;

/**
 * Interface describing the behavior of the Tracker class.
 */
public interface ITracker {
    Item add(Item item);
    boolean replace(String id, Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);
}
