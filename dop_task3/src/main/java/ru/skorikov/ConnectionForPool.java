package ru.skorikov;

import java.sql.Connection;

/**
 * Created by AlexSkorikov on 23.05.18.
 */
class ConnectionForPool {

    /**
     * Connection pool.
     */
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * SQL connection.
     */
    private Connection connection;

    /**
     * Create new connection.
     */
    ConnectionForPool() {
        this.connection = connect();
    }

    /**
     * Connect to DataBase.
     * @return connect.
     */
    private Connection connect() {
        DataBaseUtil util = new DataBaseUtil();

        return new Connect(util.connectToDataBase(), this.connectionPool);
    }

    /**
     * Get connection for pool.
     * @return connection.
     */
    Connection getConnection() {
        return connection;
    }
}
