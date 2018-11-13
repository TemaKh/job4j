package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class MenuTracker {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Хранилище меню.
     */
    private final List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения длины массива меню.
     *
     * @return длину массива
     */
     public int getActionsLength() {
         return this.actions.size();
     }

    public int getActionsKey(int index) {
        return actions.get(index).key();
    }

    public class AddNewItem extends BaseAction {

         public AddNewItem(int key, String name) {
            super(key, name);
         }

         @Override
         public void execute() {
             System.out.println("------------ Добавление новой заявки --------------");
             String name = input.ask("Введите имя заявки: ");
             String desc = input.ask("Введите описание заявки: ");
             Item item = new Item(name, desc);
             tracker.add(item);
             System.out.println("------------ Новая заявка с Id : " + item.getId() + "-----------");
         }
     }

    public class ShowAllItems extends BaseAction {

        public ShowAllItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute() {
            List<Item> items = tracker.findAll();
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }
    }

    public class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute() {
            System.out.println("------------ Изменение заявки --------------");
            String id = input.ask("Введите ID существующей заявки: ");
            String name = input.ask("Введите имя новой заявки: ");
            String desc = input.ask("Введите описание новой заявки: ");
            Item item = new Item(name, desc);
            if (tracker.replace(id, item)) {
                System.out.println("--- Существующая заявка с Id : " + id
                        + ". Была заменена на новую заявку с Id : " + item.getId() + " ---");
            } else {
                System.out.println("Данной заявки нет в хранилище, замена не произведена.");
            }
        }
    }

    public class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute() {
            String id = input.ask("Введите ID заявки: ");
            if (tracker.delete(id)) {
                System.out.println("Заявка с Id: " + id + " ,была удалена");
            } else {
                System.out.println("Данной заявки нет в хранилище, удаление не произведено.");
            }
        }
    }

    public class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute() {
            String id = input.ask("Введите ID заявки: ");
            Item result = tracker.findById(id);
            if (result != null) {
                System.out.println(result.toString());
            } else {
                System.out.printf("Заявки с id: %s не найдено%n", id);
            }
        }
    }

    public class FindItemsByName extends BaseAction {

        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute() {
            List<Item> items = tracker.findByName(input.ask("Введите Имя заявки: "));
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }

    }

    public class ExitProgram extends BaseAction {

        private final StartUI ui;

        public ExitProgram(int key, String name, StartUI ui) {
            super(key, name);
            this.ui = ui;
        }

        @Override
        public void execute() {
            this.ui.stop();
        }
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions(StartUI ui) {
        this.actions.add(new AddNewItem(0, "Add new Item"));
        this.actions.add(new ShowAllItems(1, "Show all items"));
        this.actions.add(new EditItem(2, "Edit item"));
        this.actions.add(new DeleteItem(3, "Delete item"));
        this.actions.add(new FindItemById(4, "Find item by Id"));
        this.actions.add(new FindItemsByName(5, "Find items by name"));
        this.actions.add(new ExitProgram(6, "Exit Program", ui));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute();
    }

    /**
     * Метод выводит на экран меню.
     */

    public void show() {
        System.out.println("Menu.");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
}
