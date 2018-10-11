package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenInMethodEnterNameTheyReturnArrayItemsMatchedNames() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findByName("test1")[0].getName(), is(item.getName()));
    }

    @Test
    public void whenInMethodEnterIdTheyReturnItemMatchedId() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDescription", 1234L);
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.findById(item1.getId()), is(item1));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItemThenTrackerMustDeleteCellInTheArray() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDescription", 1234L);
        String iditem2 = item2.getId();
        Item item3 = new Item("test3", "testDescription", 12345L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.delete(item2.getId());
        assertNull(tracker.findById(iditem2));
    }
}
