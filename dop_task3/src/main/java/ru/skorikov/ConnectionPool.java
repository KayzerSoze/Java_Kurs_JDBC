package ru.skorikov;

import java.sql.Connection;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by AlexSkorikov on 18.05.18.
 */
public class ConnectionPool {
    /**
     * Utils for Pool.
     */
    private DataBaseUtil util = new DataBaseUtil();

    /**
     * ConnectionsPool.
     */
    private ArrayBlockingQueue<Connection> connectionsPool = new ArrayBlockingQueue<>(4);

    /**
     * Constructor.
     */
    ConnectionPool() {
    }

    /**
     * Inner class Holder.
     */
    private static class PollHolder {
        /**
         * New instanse connectionPool.
         */
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    /**
     * Get single instanse ConnectionPool.
     *
     * @return instanse.
     */
    public static ConnectionPool getInstance() {
        return PollHolder.INSTANCE;
    }

    /**
     * Get connection from pool.
     *
     * @return connection.
     */
    public Connection getConnectionFromPoll() {
        Connection connection = connectionsPool.poll();
        if (connection == null) {
            connection = util.connectToDataBase();
        }
        return connection;
    }

    /**
     * Return connection to pool.
     *
     * @param connection connection.
     */
    public void returnConnectToPool(Connection connection) {
        if (!connectionsPool.offer(connection)) {
            util.closeQuietly(connection);
        }
    }
}
