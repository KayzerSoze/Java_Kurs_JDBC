package ru.skorikov;

import java.io.File;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
class Tracker implements AutoCloseable {
    /**
     * DataBase URL.
     */
    private DataBaseUtil util;
    /**
     * Connection to DataBase.
     */
    private Connection connection;
    /**
     * Statment.
     */
    private PreparedStatement statement;
    /**
     * Resultset.
     */
    private ResultSet resultSet;
    /**
     * Configuration file.
     */
    private final File config;

    /**
     * Get configuration file.
     *
     * @return file.
     */
    public File getConfig() {
        return config;
    }

    /**
     * Constructor Tracker.
     *
     * @param config configuration file.
     */
    Tracker(File config) {
        this.config = config;
        dataBaseInit();
    }

    /**
     * DataBase initialize.
     */
    private void dataBaseInit() {
        util = new DataBaseUtil(this);
        util.getParametresForConnect(this);
        connection = util.connectDataBase();
        try {
            connection.setAutoCommit(false);
            String sql = "create table if not exists Items(id serial, item_name varchar(64), description text);";
            statement = connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Dont create Table. " + e);
            try {
                util.rollbackQuietly(connection);
            } finally {
                util.closeQuietly(statement, connection);
            }
        }
    }

    /**
     * Adding item.
     *
     * @param item item.
     */
    public void add(Item item) {
        String sql = "INSERT INTO Items (item_name, description) VALUES (?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Cannot add Item. " + e);
            try {
                util.rollbackQuietly(connection);
            } finally {
                util.closeQuietly(statement, connection);
            }
        }
    }

    /**
     * Editing Item.
     *
     * @param item item.
     */
    public void update(Item item) {
        String sql = "UPDATE Items SET item_name = ?, description = ? WHERE id = ?;";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setInt(3, item.getId());
            int index = statement.executeUpdate();
            connection.commit();
            if (index == 0) {
                System.out.println("Item not found.");
            } else {
                System.out.println("Item was Update.");
            }
        } catch (SQLException e) {
            System.out.println("Cannot update Item. " + e);
            try {
                util.rollbackQuietly(connection);
            } finally {
                util.closeQuietly(statement, connection);
            }
        }
    }

    /**
     * Delete Item.
     *
     * @param item item.
     */
    public void delete(Item item) {
        String sql = "DELETE FROM Items WHERE id = ?;";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, item.getId());
            int index = statement.executeUpdate();
            connection.commit();
            if (index == 0) {
                System.out.println("Item not found.");
            } else {
                System.out.println("Item was Delete.");
            }
        } catch (SQLException e) {
            System.out.println("Cannot delete Item. " + e);
            try {
                util.rollbackQuietly(connection);
            } finally {
                util.closeQuietly(statement, connection);
            }
        }
    }

    /**
     * Search all Items.
     */
    public void findAll() {
        String sql = "SELECT id, item_name, description FROM Items;";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("item_name");
                String desc = resultSet.getString("description");
                System.out.println(String.format("%s\t %s\t %s\t", id, name, desc));
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                util.rollbackQuietly(connection);
            } finally {
                util.closeQuietly(resultSet, statement, connection);
            }
        }
    }

    /**
     * Search Item by name.
     *
     * @param name Item name.
     */
    public void findeByName(String name) {
        String sql = "SELECT id, item_name, description  FROM Items WHERE item_name = ?;";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name1 = resultSet.getString("item_name");
                String desc = resultSet.getString("description");
                System.out.println(String.format("%s\t %s\t %s\t", id, name1, desc));
            }
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                util.rollbackQuietly(connection);
            } finally {
                util.closeQuietly(resultSet, statement, connection);
            }
        }
    }

    /**
     * Find Item by ID.
     *
     * @param itemid ID.
     */
    public void findeById(Integer itemid) {
        String sql = "SELECT id, item_name, description  FROM Items WHERE id = ?;";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, itemid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int outid = resultSet.getInt("id");
                String name = resultSet.getString("item_name");
                String desc = resultSet.getString("description");
                System.out.println(String.format("%s\t %s\t %s\t", outid, name, desc));
            }
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                util.rollbackQuietly(connection);
            } finally {
                util.closeQuietly(resultSet, statement, connection);
            }
        }
    }

    @Override
    public void close() {

    }
}
