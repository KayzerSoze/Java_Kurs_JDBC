package ru.skorikov;

import java.sql.Connection;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by AlexSkorikov on 18.05.18.
 */
class ConnectionPool {

    /**
     * ConnectionsPool.
     */
    private ArrayBlockingQueue<Connection> connectionsPool = new ArrayBlockingQueue<>(4);

    /**
     * Constructor.
     */
    private ConnectionPool() {
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
    static ConnectionPool getInstance() {
        return PollHolder.INSTANCE;
    }

    /**
     * Get connection from pool.
     *
     * @return connection.
     */
    Connection getConnection() {
        Connection connection = connectionsPool.poll();
        if (connection == null) {
            connection = new ConnectionForPool().getConnection();
        }
        return connection;
    }

    /**
     * Get connection pool.
     *
     * @return pool.
     */
    ArrayBlockingQueue<Connection> getConnectionsPool() {
        return connectionsPool;
    }
}
