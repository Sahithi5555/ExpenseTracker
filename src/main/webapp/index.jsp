<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Expense</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
            width: 350px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        input[type="text"], input[type="date"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .view-link {
            display: block;
            text-align: center;
            margin-top: 15px;
        }
        .view-link a {
            color: #007BFF;
            text-decoration: none;
            font-weight: bold;
        }
        .view-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Add New Expense</h2>

    <form action="addExpense" method="post">
        <label>Category:</label>
        <input type="text" name="category" required>

        <label>Amount:</label>
        <input type="text" name="amount" required>

        <label>Date:</label>
        <input type="date" name="date" required>

        <input type="submit" value="Add Expense">
    </form>

    <div class="view-link">
        <a href="viewExpenses">View All Expenses</a>
    </div>
</div>

</body>
</html>
