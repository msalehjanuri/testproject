package com.testprojectfinal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mengambil parameter email dan password dari form login
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Menghubungkan ke database dan memeriksa kecocokan data
        try (Connection connection = DBConnection.initializeDatabase()) {
            // SQL query untuk memeriksa kecocokan email dan password
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Menjalankan query dan memeriksa apakah ada data yang cocok
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Jika data cocok (pengguna ditemukan), login berhasil
//                response.getWriter().println("Login successful!");
                response.sendRedirect("./products");
                // Anda bisa menyimpan informasi pengguna ke session jika diperlukan
//                 request.getSession().setAttribute("user", resultSet.getString("username"));
            } else {
                // Jika data tidak ditemukan, login gagal
                response.getWriter().println("Invalid username or password!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database error: " + e.getMessage());
        }
    }
}
