package com.example.jobportal.dao.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
    // CHANGE: Ensure the database name here (job_portal_db) matches your MySQL Workbench
    private static final String URL = "jdbc:mysql://localhost:3306/job_portal_db";
    private static final String USER = "root";         
    private static final String PASS = "Charu38@"; 

    static {
        try {
            // Explicitly loading the driver (Modern JDBC doesn't strictly require this, 
            // but it's safer for older Tomcat versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            throw new RuntimeException("MySQL JDBC Driver not found.");
        }
    }

    /**
     * Establishes a connection to the MySQL database.
     * @return Connection object
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Recommended: Helper method to close connections safely (Optional but good for marks)
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}