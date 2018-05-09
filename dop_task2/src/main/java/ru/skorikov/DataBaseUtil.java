package ru.skorikov;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
public class DataBaseUtil {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Work.class);
    /**
     * Url for connect DataBase.
     */
    private String urlDataBase;
    /**
     * User database.
     */
    private String user;
    /**
     * Password User.
     */
    private String password;
    /**
     * Statment.
     */
    private PreparedStatement statement;
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * new DB Util.
     *
     * @param urlDataBase DB URL.
     * @param user        user DB.
     * @param password    user password.
     */
    DataBaseUtil(String urlDataBase, String user, String password) {
        this.urlDataBase = urlDataBase;
        this.user = user;
        this.password = password;
        connectDataBase();
    }

    /**
     * Get connection to DataBase.
     */
    private void connectDataBase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(this.urlDataBase, this.user, this.password);
        } catch (SQLException e) {
            LOGGER.info("Don't connect to DataBase");
            LOGGER.error(e.getMessage(), e);
        }
        this.connection = connection;
    }

    /**
     * DataBase initialize.
     */
    public void dataBaseInit() {
        try {
            connection.setAutoCommit(false);
            String sql = "create table if not exists Vacancys(id serial, url text, description text, vacancy_date timestamp(6));";
            statement = connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.info("Don't initialize DataBase");
            LOGGER.error(e.getMessage(), e);
            try {
                rollbackQuietly(connection);
            } finally {
                closeQuietly(statement, connection);
            }
        }
    }

    /**
     * Add vacancy to DatdaBase.
     *
     * @param vacansy vacancy.
     * @return isAdded vacansy
     */
    public boolean addVacansyToDataBase(Vacansy vacansy) {
        boolean isAdded = false;
        String sql = "insert into Vacancys (url, description, vacancy_date) values (?, ?, ?);";
        if (uniqueVacancy(vacansy)) {
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(sql);
                statement.setString(1, vacansy.getUrl());
                statement.setString(2, vacansy.getTitle());
                statement.setTimestamp(3, new Timestamp(vacansy.getDateVacancy().getTimeInMillis()));
                statement.executeUpdate();
                connection.commit();
                isAdded = true;
            } catch (SQLException e) {
                LOGGER.info("Don't add Vacancy to DataBase");
                LOGGER.error(e.getMessage(), e);
                try {
                    rollbackQuietly(connection);
                } finally {
                    closeQuietly(statement, connection);
                }
            }
        }
        return isAdded;
    }

    /**
     * Is vacancy unique.
     * @param vacansy vacancy
     * @return unique or no
     */
    private boolean uniqueVacancy(Vacansy vacansy) {
        boolean unique = true;
        ResultSet resultSet = null;
        String sql = "select url from Vacancys;";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String url = resultSet.getString("url");
                if (url.equals(vacansy.getUrl())) {
                    unique = false;
                    break;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            closeQuietly(resultSet);
        }
        return unique;
    }

    /**
     * Soft close resourse.
     *
     * @param resourse resourse.
     */
    private void closeQuietly(AutoCloseable resourse) {
        if (resourse != null) {
            try {
                resourse.close();
            } catch (Exception e) {
                LOGGER.info("Everything is bad");
            }
        }
    }

    /**
     * Soft rollback.
     *
     * @param connection connection.
     */
    private void rollbackQuietly(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.info("Everything is bad");
            }
        }
    }

    /**
     * Soft close array resourses.
     *
     * @param resourses resourses.
     */
    private void closeQuietly(AutoCloseable... resourses) {
        for (AutoCloseable resourse : resourses) {
            closeQuietly(resourse);
        }
    }
}
