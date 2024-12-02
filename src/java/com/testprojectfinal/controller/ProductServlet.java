
package com.testprojectfinal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Kelas untuk merepresentasikan produk
class Product {
    int id;
    String name;
    String description;
    double price;

    public Product(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Membuat koneksi ke database
            Connection conn = DBConnection.initializeDatabase();

            // Query untuk mendapatkan data produk
            String query = "SELECT * FROM products";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Menyimpan hasil query ke dalam ArrayList
            ArrayList<Product> products = new ArrayList<>();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price")
                ));
            }

            // Menampilkan data dalam tabel HTML
            out.println("<h1>Product List</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Description</th><th>Price</th></tr>");
            for (Product product : products) {
                out.println("<tr>");
                out.println("<td>" + product.id + "</td>");
                out.println("<td>" + product.name + "</td>");
                out.println("<td>" + product.description + "</td>");
                out.println("<td>" + product.price + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<br>");
            out.println("<a href='./addproduct'>Tambah Produk</a><br>");
            out.println("<a href='./hello'>Balik yuk</a>");

            conn.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
