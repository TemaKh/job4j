package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;

/**
 * Class to work with the database.
 */
public class DBConnect implements AutoCloseable {
    private Connection connection;
    private final Config config;
    private static final Logger LOG = LogManager.getLogger(DBConnect.class);

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
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method adds an entry to the database.
     * @param entry .
     */
    public void insert(Entry entry) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO parser(name, description, link, date) "
                + "VALUES (?, ?, ?, ?)")) {
            statement.setString(1, entry.getName());
            statement.setString(2, entry.getDescription());
            statement.setString(3, entry.getLink());
            statement.setTimestamp(4, new Timestamp(entry.getData()));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
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
            LOG.error(e.getMessage(), e);
        }
        return last;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}