package ru.job4j.parser;

import java.io.InputStream;
import java.sql.*;

/**
 * Class to work with the database.
 */
public class DBConnect implements AutoCloseable {
    private Connection connection;
    private final Config config;

    public DBConnect() {
        config = new Config();
        init();
        createTable();
    }

    /**
     * Method performs connection to the database.
     */
    private void init() {
        try (InputStream in = DBConnect.class.getClassLoader().getResourceAsStream("parser_app.properties")) {
            Class.forName(config.get("jdbc.driver"));
            this.connection = DriverManager.getConnection(
                    config.get("jdbc.url"),
                    config.get("jdbc.username"),
                    config.get("jdbc.password")
            );

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Method create table parser if it does not exist.
     */
    private void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS parser("
                    + "id serial primary key,"
                    + "name text,"
                    + "description text,"
                    + "link text,"
                    + "date timestamp"
                    + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method adds an entry to the database.
     * @param name - job name.
     * @param description - job text.
     * @param link - reference to the vacancy.
     * @param data - Timestamp job posting dates.
     */
    public void insert(String name, String description, String link, long data) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO parser(name, description, link) "
                + "VALUES (?, ?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setString(3, link);
            statement.setTimestamp(4, new Timestamp(data));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returns the date of the last entry in the database.
     * @return Timestamp.
     */
    public Timestamp getLastDate() {
        Timestamp last = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT MAX(date) FROM parser")) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                last = set.getTimestamp(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return last;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}