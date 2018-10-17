package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartUITest {

    private final PrintStream stdout = System.out;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private final String ls = System.lineSeparator();

    private String menu() {
        StringBuilder menu = new StringBuilder();
        menu.append("Menu.").append(this.ls);
        menu.append("0. Add new Item").append(this.ls);
        menu.append("1. Show all items").append(this.ls);
        menu.append("2. Edit item").append(this.ls);
        menu.append("3. Delete item").append(this.ls);
        menu.append("4. Find item by Id").append(this.ls);
        menu.append("5. Find items by name").append(this.ls);
        menu.append("6. Exit Program").append(this.ls);
        return menu.toString();
    }

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(new StringBuilder(this.menu())
                .append("------------ Добавление новой заявки --------------").append(this.ls)
                .append("------------ Новая заявка с Id : ")
                .append(tracker.findAll()[0].getId()) .append("-----------")
                .append(this.ls)
                .append(this.menu()).toString()
        ));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(new StringBuilder(this.menu())
                .append("------------ Изменение заявки --------------").append(this.ls)
                .append("--- Существующая заявка с Id : ").append(item.getId())
                .append(". Была заменена на новую заявку с Id : ").append(item.getId()).append(" ---").append(this.ls)
                .append(this.menu()).toString()
        ));
    }

    @Test
    public void whenDeleteThenTrackerHasDeleteValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(new StringBuilder(this.menu())
                .append("Заявка с Id: ").append(item.getId()).append(" ,была удалена").append(ls)
                .append(this.menu()).toString()
        ));
    }

    @Test
    public void whenShowAllItems() {
        Tracker tracker = new Tracker();
        for (int i = 0; i < 10; i++) {
            tracker.add(new Item("test name" + i, "desc" + i));
        }
        StringBuilder showall = new StringBuilder(this.menu());
        Item[] items = tracker.findAll();
        for (Item item : items) {
            showall.append(item.toString()).append(this.ls);
        }
        showall.append(this.menu());
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(showall.toString()));
    }

    @Test
    public void whenItemFindById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(new StringBuilder(this.menu())
                .append(item).append(this.ls)
                .append(this.menu()).toString()
        ));
    }

    @Test
    public void whenItemFindByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test name", "desc1"));
        StringBuilder findbyname = new StringBuilder(this.menu());
        Item[] items = tracker.findByName(item.getName());
        for (Item vararray : items) {
            findbyname.append(vararray.toString()).append(this.ls);
        }
        findbyname.append(this.menu());
        Input input = new StubInput(new String[]{"5", item.getName(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(findbyname.toString()));
    }
}