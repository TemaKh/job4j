package ru.job4j.magnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Generating data in SQLLite. Described by the class StoreSQL.
 */
public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
        init();
        createTable();
    }

    private boolean init() {
        try {
            Class.forName(config.get("driver"));
            this.connect = DriverManager.getConnection(config.get("url"));
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.connect != null;
    }

    private void createTable() {
        try (Statement statement = connect.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS entry(field INTEGER)");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generated(int size) throws SQLException {
        clearTable();
        connect.setAutoCommit(false);
        try (PreparedStatement statement = connect.prepareStatement("INSERT INTO entry(field) VALUES (?)")) {
            for (int i = 1; i <= size; i++) {
                statement.setInt(1, i);
                statement.addBatch();
            }
            statement.executeBatch();
            connect.commit();
        } catch (SQLException e) {
            connect.rollback();
            e.printStackTrace();
        }
        connect.setAutoCommit(true);
    }

    public List<Entry> load() {
        List<Entry> list = new ArrayList<>();
        try (Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM entry")) {
            while (resultSet.next()) {
                int value = resultSet.getInt("field");
                list.add(new Entry(value));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void clearTable() throws SQLException {
        connect.setAutoCommit(false);
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate("DELETE FROM entry");
            connect.commit();
        } catch (SQLException e) {
            connect.rollback();
            e.printStackTrace();
        }
        connect.setAutoCommit(true);
    }

    @Override
    public void close() {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
