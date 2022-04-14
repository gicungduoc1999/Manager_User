package com.example.demo.repository.connect;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    private static ConnectionUtils instance = new ConnectionUtils();

    private static BasicDataSource dataSource;

    private ConnectionUtils() {
        dataSource = new BasicDataSource();
        dataSource.setUrl(ConnectionConfig.CONNECTION_URL);
        dataSource.setUsername(ConnectionConfig.USERNAME);
        dataSource.setPassword(ConnectionConfig.PASSWORD);
        dataSource.setMinIdle(ConnectionConfig.DB_MIN_CONNECTIONS); // minimum number of idle connections in the pool
        dataSource.setInitialSize(ConnectionConfig.DB_MIN_CONNECTIONS);
        dataSource.setMaxIdle(ConnectionConfig.DB_MAX_CONNECTIONS); // maximum number of idle connections in the pool
        dataSource.setMaxOpenPreparedStatements(100);
    }

    public static ConnectionUtils getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
