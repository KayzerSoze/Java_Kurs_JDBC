package ru.skorikov;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by AlexSkorikov on 17.05.18.
 */
public class DataBaseUtil {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(DataBaseUtil.class);

    /**
     * Connect to DataBase.
     * @return connection.
     */
    public Connection connectToDataBase() {
        Properties properties = new Properties();
        Connection connection = null;
        try {
            properties.load(new FileReader("src/main/resources/propertiesDB.txt"));
        } catch (IOException e) {
            LOGGER.error("Cannot read properties ", e);
        }
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOGGER.error("Cannot connect to DB ", e);
        }
        return connection;
    }

    /**
     * Soft close resourse.
     *
     * @param resourse resourse.
     */
    public void closeQuietly(AutoCloseable resourse) {
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
    public void rollbackQuietly(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.info("Everything is bad");
            }
        }
    }
}
