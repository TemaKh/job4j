package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * This class connects to the database, creates records in it,
 * edits, reads and deletes.
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());

    public TrackerSQL(Properties config) {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement st = connection.prepareStatement(
                "INSERT INTO items(name, description, create_item) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setLong(3, item.getCreate());
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(String.valueOf(generatedKeys.getInt(1)));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        if (findById(id) == null) {
            return false;
        }
        try (PreparedStatement st = connection.prepareStatement(
                "UPDATE items SET name = ?, description = ?, create_item = ? WHERE id = ?",
                Statement.RETURN_GENERATED_KEYS)) {
                st.setString(1, item.getName());
                st.setString(2, item.getDescription());
                st.setLong(3, item.getCreate());
                st.setInt(4, Integer.parseInt(id));
                st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(String.valueOf(generatedKeys.getInt(1)));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id) == null) {
            return false;
        }
        try (PreparedStatement st = connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
                st.setInt(1, Integer.parseInt(id));
                st.executeUpdate();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM items")) {
            while (rs.next()) {
                Item item = new Item(rs.getString("name"),
                        rs.getString("description"),
                        rs.getLong("create_item"));
                item.setId(String.valueOf(rs.getInt("id")));
                items.add(item);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM items")) {
            while (rs.next()) {
                if (rs.getString("name").equals(key)) {
                    Item item = new Item(rs.getString("name"),
                            rs.getString("description"),
                            rs.getLong("create_item"));
                    item.setId(String.valueOf(rs.getInt("id")));
                    items.add(item);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                item = new Item(rs.getString("name"),
                        rs.getString("description"),
                        rs.getLong("create_item"));
                item.setId(String.valueOf(rs.getInt("id")));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }
}
