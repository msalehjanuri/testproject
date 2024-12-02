
package com.testprojectfinal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "AddProductServlet", urlPatterns = {"/addproduct"})
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Ambil data dari form
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try (Connection connection = DBConnection.initializeDatabase()) {
            // Query untuk menambahkan data ke tabel produk
            String query = "INSERT INTO products (name, description, price) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, name);
            statement.setString(2, description);
            statement.setString(3, price);

            // Eksekusi query
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                response.getWriter().write("<h1>Product added successfully!</h1>");
                response.getWriter().write("<a href='products'>Back to Product List</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("<h1>Error adding product!</h1>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().write("<h1>Add Product</h1>");
        response.getWriter().write("<form action='addproduct' method='POST'>"
                + "<label for='name'>Product Name:</label><br>"
                + "<input type='text' id='name' name='name' required><br><br>"
                + "<label for='description'>Product Description:</label><br>"
                + "<input type='text' id='description' name='description' required><br><br>"
                + "<label for='price'>Product Price:</label><br>"
                + "<input type='number' id='price' name='price' required><br><br>"
                + "<button type='submit'>Add Product</button>"
                + "</form>");
    }
}
