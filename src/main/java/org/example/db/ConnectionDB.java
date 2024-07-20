package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    Connection connection;

    public ConnectionDB() {}

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String urlConnection = "jdbc:postgresql://localhost:5432/my-db";
        Class.forName("org.postgresql.Driver");

        connection = DriverManager.getConnection(urlConnection, "postgres", "mysecretpassword");
        return connection;


    }

}
