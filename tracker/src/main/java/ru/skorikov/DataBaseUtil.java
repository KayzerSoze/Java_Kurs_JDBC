package ru.skorikov;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
public class DataBaseUtil {
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
     * Tracker.
     */
    private Tracker tracker;

    /**
     * Constructor Utills.
     *
     * @param tracker tracer
     */
    DataBaseUtil(Tracker tracker) {
        this.tracker = tracker;
    }

    /**
     * Get connection to DataBase.
     *
     * @return connection.
     */
    public Connection connectDataBase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(this.urlDataBase, this.user, this.password);
        } catch (SQLException e) {
            System.out.println("Don't connect to DataBase");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Get parameters for connect to database.
     *
     * @param tracker tracker.
     */
    public void getParametresForConnect(Tracker tracker) {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        MyHandler handler = new MyHandler();
        try {
            SAXParser parser = parserFactory.newSAXParser();
            try {
                parser.parse(tracker.getConfig(), handler);
                urlDataBase = handler.getUrlDataBase();
                user = handler.getUser();
                password = handler.getPassword();
            } catch (IOException e) {
                System.out.println("Problem with the file config.");
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            System.out.println("Problem with Parser.");
            e.printStackTrace();
        } catch (SAXException e) {
            System.out.println("Problem with SAX.");
            e.printStackTrace();
        }
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
                System.out.println("Everything is bad");
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
                System.out.println("Everything is bad");
            }
        }
    }

    /**
     * Soft close array resourses.
     *
     * @param resourses resourses.
     */
    public void closeQuietly(AutoCloseable... resourses) {
        for (AutoCloseable resourse : resourses) {
            closeQuietly(resourse);
        }
    }
}
