package com.example.demo.repository.connect;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    private static ConnectionUtils instance = new ConnectionUtils();

    private static final String USERNAME = "sa";

    private static final String PASSWORD = "1";

    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost;databaseName=ManagerUser";

    public static final int DB_MIN_CONNECTIONS = 2;

    public static final int DB_MAX_CONNECTIONS = 4;

    private static BasicDataSource dataSource = new BasicDataSource();

    private ConnectionUtils() {
    }

    public static ConnectionUtils getInstance(){
        return instance;
    }

    public Connection getConnection() throws SQLException {
        dataSource.setUrl(CONNECTION_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMinIdle(DB_MIN_CONNECTIONS); // minimum number of idle connections in the pool
        dataSource.setInitialSize(DB_MIN_CONNECTIONS);
        dataSource.setMaxIdle(DB_MAX_CONNECTIONS); // maximum number of idle connections in the pool
        dataSource.setMaxOpenPreparedStatements(100);
        return dataSource.getConnection();
    }
}
