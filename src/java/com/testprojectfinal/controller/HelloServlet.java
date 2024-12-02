package com.testprojectfinal.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")  // URL mapping
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Selamat datang di SADYOGAS Mart!</h1>");
        out.println("</body></html>");
        out.println("<a href='./products'>Lihat Produk</a>");
        out.println("<a href='./'>Keluar lewat sini</a>");
    }
}
