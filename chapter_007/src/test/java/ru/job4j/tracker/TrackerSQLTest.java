package ru.job4j.tracker;

import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {

    @Test
    public void whenAddNewItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(new Properties())) {
            Item item = new Item("test1", "testDescription", 1L);
            trackerSQL.add(item);
            trackerSQL.add(new Item("test2", "testDescription", 12L));
            trackerSQL.add(new Item("test3", "testDescription", 123L));
            trackerSQL.add(new Item("test4", "testDescription", 1234L));
            assertThat(trackerSQL.findById("1"), is(item));
        }
    }

    @Test
    public void whenReplaceItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(new Properties())) {
            Item item = new Item("test5", "testDescription", 12345L);
            trackerSQL.replace("2", item);
            assertThat(trackerSQL.findById("2"), is(item));
        }
    }

    @Test
    public void whenDeleteItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(new Properties())) {
            assertThat(trackerSQL.delete("4"), is(true));
        }
    }

    @Test
    public void whenFindAllItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(new Properties())) {
            assertThat(trackerSQL.findAll().get(2), is(trackerSQL.findById("2")));
        }
    }

    @Test
    public void whenFindByName() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(new Properties())) {
            trackerSQL.add(new Item("test5", "testDescription", 45L));
            assertThat(trackerSQL.findByName("test5").get(0).getName(), is("test5"));
        }
    }
}