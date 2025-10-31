package com.expensetracker.servlets;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ExpenseServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/expensetracker";
    private static final String USER = "root"; // change if your MySQL username is different
    private static final String PASSWORD = ""; // enter your MySQL password here

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String category = request.getParameter("category");
        String amount = request.getParameter("amount");
        String date = request.getParameter("date");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "INSERT INTO expenses (category, amount, date) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, category);
            stmt.setDouble(2, Double.parseDouble(amount));
            stmt.setDate(3, Date.valueOf(date));
            stmt.executeUpdate();
            conn.close();

            out.println("<h3 style='color:green;'>Expense added successfully!</h3>");
            request.getRequestDispatcher("index.jsp").include(request, response);

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM expenses");

            out.println("<h2>All Expenses</h2>");
            out.println("<table border='1'><tr><th>ID</th><th>Category</th><th>Amount</th><th>Date</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>"
                        + rs.getString("category") + "</td><td>"
                        + rs.getDouble("amount") + "</td><td>"
                        + rs.getDate("date") + "</td></tr>");
            }
            out.println("</table>");
            conn.close();

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }
}
