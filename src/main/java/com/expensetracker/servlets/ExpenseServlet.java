package com.expensetracker.servlets;

import java.io.*;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.expensetracker.util.DBConnection; // ✅ using your working connection class

public class ExpenseServlet extends HttpServlet {

    // ✅ Handles adding a new expense
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String category = request.getParameter("category");
        String amount = request.getParameter("amount");
        String date = request.getParameter("date");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO expenses (category, amount, date) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, category);
            stmt.setDouble(2, Double.parseDouble(amount));
            stmt.setDate(3, Date.valueOf(date));
            stmt.executeUpdate();

            // ✅ Redirect to view.jsp after successful insertion
            response.sendRedirect("ExpenseServlet"); // triggers doGet()

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }

    // ✅ Handles viewing all expenses
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM expenses");

            // Store results in a List for JSP
            List<String[]> expenses = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("category"),
                    String.valueOf(rs.getDouble("amount")),
                    String.valueOf(rs.getDate("date"))
                };
                expenses.add(row);
            }

            // Send data to view.jsp
            request.setAttribute("expensesList", expenses);
            System.out.println("✅ Found " + expenses.size() + " expenses in database.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            e.printStackTrace(out);
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }
}
