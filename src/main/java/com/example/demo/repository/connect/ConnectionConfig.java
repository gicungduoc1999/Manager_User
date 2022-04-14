package com.example.demo.repository.connect;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


import java.io.File;


public class ConnectionConfig {
    private static Config config = ConfigFactory.parseFile(new File("DBConfig.properties"));
    public static final String USERNAME = config.getString("username");
    public static final String PASSWORD = config.getString("password");
    public static final String CONNECTION_URL = config.getString("connection_url");
    public static final int DB_MIN_CONNECTIONS = config.getInt("db_min_connections");
    public static final int DB_MAX_CONNECTIONS = config.getInt("db_max_connections");
}
