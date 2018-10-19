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
         @Override
         public int key() {
             return 0;
         }

         @Override
         public void execute(Input input, Tracker tracker) {
             System.out.println("------------ Добавление новой заявки --------------");
             String name = input.ask("Введите имя заявки: ");
             String desc = input.ask("Введите описание заявки: ");
             Item item = new Item(name, desc);
             tracker.add(item);
             System.out.println("------------ Новая заявка с Id : " + item.getId() + "-----------");
         }

         @Override
         public String info() {
             return "0. Add new Item";
         }
     }

    public class ShowAllItems implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] items = tracker.findAll();
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }

        @Override
        public String info() {
            return "1. Show all items";
        }
    }

    public class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
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
            return "2. Edit item";
        }
    }

    public class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите ID заявки: ");
            if (tracker.delete(id)) {
                System.out.println("Заявка с Id: " + id + " ,была удалена");
            } else {
                System.out.println("Данной заявки нет в хранилище, удаление не произведено.");
            }
        }

        @Override
        public String info() {
            return "3. Delete item";
        }
    }

    public class FindItemById implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println(tracker.findById(input.ask("Введите ID заявки: ")).toString());
        }

        @Override
        public String info() {
            return "4. Find item by Id";
        }
    }

    public class FindItemsByName implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] items = tracker.findByName(input.ask("Введите Имя заявки: "));
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }

        @Override
        public String info() {
            return "5. Find items by name";
        }
    }

    public class ExitProgram implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

        }

        @Override
        public String info() {
            return "6. Exit Program";
        }
    }
    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddNewItem());
        this.actions.add(new ShowAllItems());
        this.actions.add(new EditItem());
        this.actions.add(new DeleteItem());
        this.actions.add(new FindItemById());
        this.actions.add(new FindItemsByName());
        this.actions.add(new ExitProgram());
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
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
