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

     public class AddNewItem implements UserAction {
         private int key;
         private String name;

         public AddNewItem(int key, String name) {
             this.key = key;
             this.name = name;
         }

         @Override
         public int key() {
             return this.key;
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

         @Override
         public String info() {
             return String.format("%d. %s", this.key, this.name);
         }
     }

    public class ShowAllItems implements UserAction {
        private int key;
        private String name;

        public ShowAllItems(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
        }

        @Override
        public void execute() {
            Item[] items = tracker.findAll();
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key, this.name);
        }
    }

    public class EditItem implements UserAction {
        private int key;
        private String name;

        public EditItem(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
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

        @Override
        public String info() {
            return String.format("%d. %s", this.key, this.name);
        }
    }

    public class DeleteItem implements UserAction {
        private int key;
        private String name;

        public DeleteItem(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
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

        @Override
        public String info() {
            return String.format("%d. %s", this.key, this.name);
        }
    }

    public class FindItemById implements UserAction {
        private int key;
        private String name;

        public FindItemById(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
        }

        @Override
        public void execute() {
            System.out.println(tracker.findById(input.ask("Введите ID заявки: ")).toString());
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key, this.name);
        }
    }

    public class FindItemsByName implements UserAction {
        private int key;
        private String name;

        public FindItemsByName(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
        }

        @Override
        public void execute() {
            Item[] items = tracker.findByName(input.ask("Введите Имя заявки: "));
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key, this.name);
        }
    }

    public class ExitProgram implements UserAction {
        private int key;
        private String name;

        public ExitProgram(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
        }

        @Override
        public void execute() {

        }

        @Override
        public String info() {
            return String.format("%d. %s", this.key, this.name);
        }
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddNewItem(0, "Add new Item"));
        this.actions.add(new ShowAllItems(1, "Show all items"));
        this.actions.add(new EditItem(2, "Edit item"));
        this.actions.add(new DeleteItem(3, "Delete item"));
        this.actions.add(new FindItemById(4, "Find item by Id"));
        this.actions.add(new FindItemsByName(5, "Find items by name"));
        this.actions.add(new ExitProgram(6, "Exit Program"));
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