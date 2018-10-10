package ru.job4j.tracker;

public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static  final String ADD = "0";

    /**
     * Константа меню для демонстрации всех заявок.
     */
    private static  final String SHOW_ALL = "1";

    /**
     * Константа меню для редактирования заявки.
     */
    private static  final String EDIT = "2";

    /**
     * Константа для удаления заявки.
     */
    private static  final String DELETE = "3";

    /**
     * Константа для поиска заявки по ID.
     */
    private static  final String FIND_BY_ID = "4";

    /**
     * Константа для поиска заявки по имени.
     */
    private static  final String FIND_BY_NAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Select item menu: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                Item[] items = tracker.findAll();
                for (Item item : items) {
                    System.out.println(item.toString());
                }
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                tracker.delete(this.input.ask("Введите ID заявки: "));
            } else if (FIND_BY_ID.equals(answer)) {
                System.out.println(tracker.findById(this.input.ask("Введите ID заявки: ")).toString());
            } else if (FIND_BY_NAME.equals(answer)) {
                Item[] items = tracker.findByName(this.input.ask("Введите Имя заявки: "));
                for (Item item : items) {
                    System.out.println(item.toString());
                }
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с Id : " + item.getId() + "-----------");
    }

    /**
     * Метод заменяет существующую заявку на новую в хранилище.
     */
    private void editItem() {
        System.out.println("------------ Изменение заявки --------------");
        String id = this.input.ask("Введите ID существующей заявки: ");
        String name = this.input.ask("Введите имя новой заявки: ");
        String desc = this.input.ask("Введите описание новой заявки: ");
        Item item = new Item(name, desc);
        this.tracker.replace(id, item);
        System.out.println("--- Существующая заявка с Id : " + id
                + ". Была заменена на новую заявку с Id : " + item.getId() +  " ---");
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
