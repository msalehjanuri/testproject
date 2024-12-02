
package com.testprojectfinal.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/finalprojecttes?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root"; // Gantilah dengan username MySQL Anda
    private static final String PASSWORD = ""; // Gantilah dengan password MySQL Anda (biasanya kosong pada XAMPP)
    
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC driver
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metode untuk inisialisasi koneksi ke database
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        // URL database
        String dbURL = "jdbc:mysql://localhost:3306/finalprojecttes?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String dbUser = "root"; // Ganti dengan username MySQL Anda
        String dbPassword = ""; // Ganti dengan password MySQL Anda (kosong jika default XAMPP)

        // Memuat driver JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Membuat koneksi
        return DriverManager.getConnection(dbURL, dbUser, dbPassword);
    }
}
