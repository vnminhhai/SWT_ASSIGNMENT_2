<%@ page import="test.integration.swt_assignment_2.model.CustomerType" %>
<%@ page import="test.integration.swt_assignment_2.model.EmployeeType" %>
<%@ page import="test.integration.swt_assignment_2.model.ItemType" %><%--a jsp page with input fields for customer type, employee type, item type and item price--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        form {
            margin: 20px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        label, input {
            display: block;
            margin-bottom: 10px;
            margin-top: 10px;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        select {
            width: 100%;
            padding: 8px 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #f8f8f8;
            box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
            appearance: none; /* Removes default styling of select in WebKit browsers */
            -moz-appearance: none; /* Removes default styling of select in Firefox */
            -webkit-appearance: none; /* Removes default styling of select in Safari and Chrome */
        }
    </style>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
    <form action="calculate" method="post">
        <label for="customerType">Customer Type:</label>
        <select id="customerType" name="customerType" required>
            <% for (CustomerType type : (CustomerType[]) request.getAttribute("customerTypes")) { %>
            <option value="<%= type.name() %>"><%= type.name() %></option>
            <% } %>
        </select>
        <br>

        <label for="employeeType">Employee Type:</label>
        <select id="employeeType" name="employeeType" required>
            <% for (EmployeeType type : (EmployeeType[]) request.getAttribute("employeeTypes")) { %>
            <option value="<%= type.name() %>"><%= type.name() %></option>
            <% } %>
        </select>
        <br>

        <label for="itemType">Item Type:</label>
        <select id="itemType" name="itemType" required>
            <% for (ItemType type : (ItemType[]) request.getAttribute("itemTypes")) { %>
            <option value="<%= type.name() %>"><%= type.name() %></option>
            <% } %>
        </select>
        <br>
        <label for="itemPrice">Item Price:</label>
        <input type="number" id="itemPrice" name="itemPrice" required value="<%= request.getAttribute("itemPrice") != null ? request.getAttribute("itemPrice").toString() : "" %>">
        <br>
        <input type="submit" value="Submit" id="submit">
    </form>
    <% if (request.getAttribute("commission") != null) { %>
        <label for="result">Commission:</label>
        <input id="result" type="text" name="commission" value="<%= request.getAttribute("commission").toString() %>" readonly>
        <br>
    <% } %>
</body>
</html>
