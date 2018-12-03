package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    private static final Random RN = new Random();

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод заменяет ячейку в массиве this.items. Для этого необходимо найти ячейку в массиве по id.
     * @param id .
     * @param item .
     */
    public boolean replace(String id, Item item) {
        int i = 0;
        for (Item itemreplace : this.items) {
            if (itemreplace.getId().equals(id)) {
                item.setId(id);
                this.items.set(i, item);
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Метод  удаляет ячейку в массиве this.items по заданному id.
     * @param id удаляемого элемента.
     */
    public boolean delete(String id) {
        int i = 0;
        for (Item itemdelete : this.items) {
            if (itemdelete.getId().equals(id)) {
                this.items.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Метод возвращает копию массива this.items без null элементов.
     * @return возвращает копию массива this.items без null элементов.
     */
    public List<Item> findAll() {
        return this.items.stream().collect(Collectors.toList());
    }

    /**
     * Метод проверяет в цикле все элементы массива this.items, сравнивая name (используя метод getName класса Item)
     * с аргументом метода String key. Элементы, у которых совпадает name, копирует в результирующий массив
     * и возвращает его.
     * @param key имя которое нужно найти.
     * @return результирующий массив.
     */
    public List<Item> findByName(String key) {
        return items.stream().filter(itemName -> itemName.getName().equals(key)).collect(Collectors.toList());
    }

    /**
     * Метод проверяет в цикле все элементы массива this.items, сравнивая id с аргументом String id и возвращает
     * найденный Item. Если Item не найден - возвращает null.
     * @param id .
     * @return найденный Item.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
