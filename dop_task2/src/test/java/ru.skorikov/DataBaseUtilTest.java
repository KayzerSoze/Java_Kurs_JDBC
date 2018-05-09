package ru.skorikov;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;


/**
 * Created by AlexSkorikov on 09.05.18.
 */
public class DataBaseUtilTest {

    /**
     * Test Init.
     */
    @Test
    public void dataBaseInit() {
        String url = "jdbc:postgresql://localhost:5432/Vacancy";
        String user = "postgres";
        String pas = "postgres";
        DataBaseUtil util = new DataBaseUtil(url, user, pas);
        util.dataBaseInit();
    }

    /**
     * Test add new Vacancy.
     */
    @Test
    public void addVacansyToDataBase() {
        Calendar testdate = Calendar.getInstance();
        String url = "test_url";
        String title = "test_title";
        Vacansy vacansy = new Vacansy(url, title, testdate);
        String urlDB = "jdbc:postgresql://localhost:5432/Vacancy";
        String user = "postgres";
        String pas = "postgres";
        DataBaseUtil util = new DataBaseUtil(urlDB, user, pas);
        util.dataBaseInit();
        Assert.assertTrue(util.addVacansyToDataBase(vacansy));
        Assert.assertFalse(util.addVacansyToDataBase(vacansy));
    }

    /**
     * Test new DBUtil.
     */
    @Test(expected = NullPointerException.class)
    public void testDataBaseInit() {
        String url = "";
        String user = "";
        String pas = "";
        DataBaseUtil dataBaseUtil = new DataBaseUtil(url, user, pas);
        dataBaseUtil.dataBaseInit();
    }

    /**
     * After for testVacancy.
     */
    @After
    public void deleteTestVacancy() {
        String urlDB = "jdbc:postgresql://localhost:5432/Vacancy";
        String user = "postgres";
        String pas = "postgres";
        Connection connection;
        try {
            connection = DriverManager.getConnection(urlDB, user, pas);
            connection.setAutoCommit(false);
            String sql = "DELETE FROM Vacancys WHERE description = 'test_title';";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}