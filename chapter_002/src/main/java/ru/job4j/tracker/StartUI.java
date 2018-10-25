package ru.job4j.tracker;

public class StartUI {

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    private boolean working = true;

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

    public void stop() {
        this.working = false;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        int[] range = new int[menu.getActionsLength()];
        for (int index = 0; index < range.length; index++) {
            range[index] = menu.getActionsKey(index);
        }
        while (this.working) {
            menu.show();
            int answer = this.input.ask("Select item menu: ", range);
            menu.select(answer);
        }
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
