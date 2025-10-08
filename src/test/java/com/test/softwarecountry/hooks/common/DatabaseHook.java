package com.test.softwarecountry.hooks.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DatabaseHook {

    private static final ThreadLocal<Connection> threadConn = new ThreadLocal<>();

    public static Connection getConnection() {
        return threadConn.get();
    }

    @Before("@database")
    public void initInMemoryDatabase() throws SQLException {
        String dbName = "testdb_" + Thread.currentThread().getId();
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:" + dbName + ";DB_CLOSE_DELAY=-1", "sa", "");
        threadConn.set(conn);

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("""
                                     CREATE TABLE users (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL
                                     );
                                 
                                     CREATE TABLE hotels (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL
                                     );
                                 
                                     CREATE TABLE reservations (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       user_id INT,
                                       hotel_id INT,
                                       checkin_date DATE,
                                       checkout_date DATE,
                                       FOREIGN KEY (user_id) REFERENCES users(id),
                                       FOREIGN KEY (hotel_id) REFERENCES hotels(id)
                                     );
                                 """);

            stmt.execute("INSERT INTO users (name) VALUES ('Alisson'), ('Gabi')");
            stmt.execute("INSERT INTO hotels (name) VALUES ('Hotel Software Country'), ('Yellow Ocean Hotel')");
            stmt.execute("INSERT INTO reservations (user_id, hotel_id, checkin_date, checkout_date) VALUES " +
                                 "(1, 1, '2025-10-01', '2025-10-05'), (2, 2, '2025-10-03', '2025-10-06')");
        }
    }

    @After("@database")
    public void dropAllTables() throws SQLException {
        Connection conn = threadConn.get();
        if (conn != null && !conn.isClosed()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP ALL OBJECTS");
            }
            conn.close();
        }
        threadConn.remove();
    }
}
