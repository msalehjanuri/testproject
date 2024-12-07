package com.testprojectfinal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DBConnection.initializeDatabase()) {
            // SQL untuk menyimpan data registrasi
            String sql = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, request.getParameter("email"));
            statement.setString(2, request.getParameter("username"));
            statement.setString(3, request.getParameter("password"));
            statement.executeUpdate();
            
            // Mengirimkan response sukses untuk memberi tahu pengguna
//            response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("alert('Registration successful!');");
            response.sendRedirect("login-register.html?status=registered");
//            response.getWriter().println("document.getElementById('register-form').style.display = 'none';");
//            response.getWriter().println("document.getElementById('login-form').style.display = 'block';");
//            response.getWriter().println("</script>");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database error: " + e.getMessage());
        }
    }
}
