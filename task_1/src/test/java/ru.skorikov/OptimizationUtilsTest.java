package ru.skorikov;

import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.sql.SQLException;


/**
 * Created with IntelliJ IDEA.
 *
 * @ author: Alex_Skorikov.
 * @ date: 23.04.18
 * @ version: Java_Kurs_JDBC
 */
public class OptimizationUtilsTest {
    /**
     * Optimization for test.
     */
    private Optimization optimization = new Optimization();

    /**
     * Init input param.
     */
    @Before
    public void init() {
        optimization.setUrl("jdbc:sqlite:src/main/resources/java_test");
        optimization.setFile1(new File("src/main/resources/1.xml"));
        optimization.setFile2(new File("src/main/resources/2.xml"));
        optimization.setSourseXslt(new File("src/main/resources/transform.xslt"));
        optimization.setUsername("");
        optimization.setPassword("");
        optimization.setN(3_0);
    }

    /**
     * Try create Table at DataBase.
     */
    @Test
    public void tryCreateTable() {
        try {
            OptimizationUtils.createTable(this.optimization);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Try select from DataBase.
     *
     * @throws SQLException exception
     */
    @Test
    public void trySelectFromDBforXML() throws SQLException {
        OptimizationUtils.selectFromDBforXML(this.optimization);
    }

    /**
     * Try create XML1.
     *
     * @throws SQLException exception
     */
    @Test
    public void tryCreateXML1() throws SQLException {
        OptimizationUtils.createXML1(this.optimization);
    }

    /**
     * Try Transformation XML.
     *
     * @throws TransformerException exception
     */
    @Test
    public void tryConvertXML1ToXML2() throws TransformerException {
        OptimizationUtils.convertXML1ToXML2(this.optimization);
    }

    /**
     * Try parse XML and return result.
     */
    @Test
    public void tryParseXMLAndReturnResult() {
        OptimizationUtils.parseXMLAndReturnResult(this.optimization);
    }

    /**
     * Try run all method from UtilsOptimization.
     * @throws TransformerException exception
     * @throws SQLException exception
     */
    @Test
    public void startAllMethod() throws TransformerException, SQLException {
        long startTime = System.currentTimeMillis() / 1000;

        this.optimization.work();

        long endTime = System.currentTimeMillis() / 1000;
        System.out.println("End Time = " + (endTime - startTime) + " sek");
    }
}