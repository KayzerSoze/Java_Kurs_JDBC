package ru.skorikov;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by AlexSkorikov on 17.05.18.
 */
public class ConnectionForPoolTest {
    /**
     * Test Pool.
     *
     * @throws SQLException exception.
     */
    @Test
    public void testPool() throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        java.sql.Connection connection0 = connectionPool.getConnection();
        java.sql.Connection connection1 = connectionPool.getConnection();
        java.sql.Connection connection2 = connectionPool.getConnection();
        java.sql.Connection connection3 = connectionPool.getConnection();
        java.sql.Connection connection4 = connectionPool.getConnection();

        connection0.close();
        connection1.close();
        connection2.close();
        connection3.close();
        connection4.close();

        Assert.assertFalse(connection0.isClosed());
        Assert.assertFalse(connection1.isClosed());
        Assert.assertFalse(connection2.isClosed());
        Assert.assertFalse(connection3.isClosed());
        Assert.assertTrue(connection4.isClosed());
    }
}
