package ru.job4j.tracker;

public interface UserAction {
    /**
     * Метод возвращает ключ опции.
     * @return ключ
     */
    int key();

    /**
     * Основной метод.
     */
     void execute();
    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return Строка меню
     */
     String info();
}
