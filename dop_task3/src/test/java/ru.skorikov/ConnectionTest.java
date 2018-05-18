package ru.skorikov;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by AlexSkorikov on 17.05.18.
 */
public class ConnectionTest {
    /**
     * Test Pool.
     * @throws SQLException exception.
     */
    @Test
    public void testPool() throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection0 = connectionPool.getConnectionFromPoll();
        Connection connection1 = connectionPool.getConnectionFromPoll();
        Connection connection2 = connectionPool.getConnectionFromPoll();
        Connection connection3 = connectionPool.getConnectionFromPoll();
        Connection connection4 = connectionPool.getConnectionFromPoll();

        connectionPool.returnConnectToPool(connection0);
        connectionPool.returnConnectToPool(connection1);
        connectionPool.returnConnectToPool(connection2);
        connectionPool.returnConnectToPool(connection3);
        connectionPool.returnConnectToPool(connection4);

        Assert.assertFalse(connection0.isClosed());
        Assert.assertFalse(connection1.isClosed());
        Assert.assertFalse(connection2.isClosed());
        Assert.assertFalse(connection3.isClosed());
        Assert.assertTrue(connection4.isClosed());
    }
}
