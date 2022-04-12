package com.example.demo.repository.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    private static final String USERNAME ="sa";

    private static final String PASSWORD ="1";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://localhost:8081/ManagerUser";
        return  DriverManager.getConnection(connectionURL, USERNAME, PASSWORD);
    }
}
