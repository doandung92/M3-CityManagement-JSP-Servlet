package com.codegym.DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/m3exam?useSSL=false";
        String username = "root";
        String password = "123456";
        return DriverManager.getConnection(url, username, password);
    }
}
