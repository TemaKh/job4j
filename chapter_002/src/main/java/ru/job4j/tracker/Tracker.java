package ru.job4j.tracker;

import java.util.Random;

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    private static final Random RN = new Random();

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
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
    public void replace(String id, Item item) {
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                this.items[index] = item;
                break;
            }
        }
    }

    /**
     * Метод  удаляет ячейку в массиве this.items по заданному id.
     * @param id удаляемого элемента.
     */
    public void delete(String id) {
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].equals(id)) {
                System.arraycopy(this.items, index + 1, this.items, index, this.items.length - 1 - index);
                this.items[this.items.length - 1] = null;
            }
        }
    }

    /**
     * Метод возвращает копию массива this.items без null элементов.
     * @return возвращает копию массива this.items без null элементов.
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Метод проверяет в цикле все элементы массива this.items, сравнивая name (используя метод getName класса Item)
     * с аргументом метода String key. Элементы, у которых совпадает name, копирует в результирующий массив
     * и возвращает его.
     * @param key имя которое нужно найти.
     * @return результирующий массив.
     */
    public Item[] findByName(String key) {
        int counter = 0;
        int resPosistion = 0;
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getName().equals(key)) {
                counter++;
            }
        }
        Item[] result = new Item[counter];
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getName().equals(key)) {
                result[resPosistion++] = this.items[index];
            }
        }
        return result;
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
