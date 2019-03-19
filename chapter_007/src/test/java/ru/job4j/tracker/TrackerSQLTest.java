package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {

    private Item item;

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Before
    public void before() throws Exception {
        item = new Item("test1", "testDescription", 1L);
    }

    @Test
    public void whenAddNewItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            trackerSQL.add(item);
            assertThat(trackerSQL.findByName("test1").get(0), is(item));
        }
    }

    @Test
    public void whenReplaceItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            trackerSQL.add(item);
            Item item1 = new Item("test5", "testDescription", 12345L);
            trackerSQL.replace("1", item1);
            assertThat(trackerSQL.findById("1"), is(item1));
        }
    }

    @Test
    public void whenDeleteItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            assertThat(trackerSQL.delete("1"), is(true));
        }
    }

    @Test
    public void whenFindAllItem() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            assertThat(trackerSQL.findAll().get(1), is(trackerSQL.findById("2")));
        }
    }

    @Test
    public void whenFindByName() throws Exception {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            trackerSQL.add(new Item("test5", "testDescription", 45L));
            assertThat(trackerSQL.findByName("test5").get(0).getName(), is("test5"));
        }
    }
}