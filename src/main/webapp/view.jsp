<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<html>
<head>
    <title>View Expenses</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f8f9fa;
            padding: 40px;
        }
        h2 {
            color: #333;
            text-align: center;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background: white;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            border-bottom: 1px solid #ccc;
            text-align: center;
        }
        th {
            background: #007BFF;
            color: white;
        }
        tr:hover {
            background: #f1f1f1;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        .btn {
            padding: 10px 20px;
            background: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>

<h2>All Expenses</h2>

<%
    List<String[]> expenses = (List<String[]>) request.getAttribute("expensesList");

    if (expenses == null || expenses.isEmpty()) {
%>
        <p style="text-align:center;">No expenses found!</p>
<%
    } else {
%>
        <table>
            <tr>
                <th>ID</th>
                <th>Category</th>
                <th>Amount</th>
                <th>Date</th>
            </tr>
<%
        for (String[] expense : expenses) {
%>
            <tr>
                <td><%= expense[0] %></td>
                <td><%= expense[1] %></td>
                <td><%= expense[2] %></td>
                <td><%= expense[3] %></td>
            </tr>
<%
        }
%>
        </table>
<%
    }
%>

<div class="button-container">
    <a href="index.jsp" class="btn">Add Another Expense</a>
</div>

</body>
</html>
