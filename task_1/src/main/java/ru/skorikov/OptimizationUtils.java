package ru.skorikov;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Source;


/**
 * Created with IntelliJ IDEA.
 *
 * @ author: Alex_Skorikov.
 * @ date: 21.04.18
 * @ version: Java_Kurs_JDBC
 */
public class OptimizationUtils {
    /**
     * Create DataBase Table and insert Data.
     *
     * @param optimization input parametr.
     * @throws SQLException exception
     */
    public void createTable(Optimization optimization) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement st = null;
        try {
            connection = DriverManager.getConnection(optimization.getUrl(),
                    optimization.getUsername(), optimization.getPassword());
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS Test;");
            statement.execute("CREATE TABLE Test(id BIGINT);");

            String sql = "insert into Test (id) values (?)";
            st = connection.prepareStatement(sql);

            for (long index = 1; index <= optimization.getN(); index++) {
                st.setLong(1, index);
                st.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println("DataBase problem " + e);
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (st != null) {
                st.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Inner method.
     * Select Data from DataBase and create DataList.
     *
     * @param optimization input parametr
     * @return DataList
     * @throws SQLException exception
     */
    public DataList selectFromDBforXML(Optimization optimization) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DataList dataList = new DataList();
        try {
            connection = DriverManager.getConnection(optimization.getUrl(),
                    optimization.getUsername(), optimization.getPassword());
            connection.setAutoCommit(false);

            String sql = "select * from Test";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Data data = new Data();
                data.setData(resultSet.getLong("id"));
                dataList.getDataList().add(data);
            }
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return dataList;
    }

    /**
     * Create XML1 from DataList.
     *
     * @param optimization input param
     * @throws SQLException exception
     */
    public void createXML1(Optimization optimization) throws SQLException {
        JAXBContext context;
        DataList dataList = selectFromDBforXML(optimization);
        try {
            context = JAXBContext.newInstance(DataList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(dataList, new File(String.valueOf(optimization.getFile1())));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert XML2 from XML1.
     *
     * @param optimization input param
     * @throws TransformerException exception
     */
    public void convertXML1ToXML2(Optimization optimization) throws TransformerException {

        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(String.valueOf(optimization.getSoursexslt())));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File(String.valueOf(optimization.getFile1())));
        transformer.transform(text, new StreamResult(new File(String.valueOf(optimization.getFile2()))));
    }

    /**
     * Parse XML_2 and out rezalt.
     *
     * @param optimization input param
     */
    public void parseXMLAndReturnResult(Optimization optimization) {
        Long rezult = null;
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        Handler handler = new Handler();
        try {
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(new File(String.valueOf(optimization.getFile2())), handler);
            rezult = handler.getRezalt();

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        System.out.println(rezult);

    }

}
