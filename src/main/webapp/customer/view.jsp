<%--
  Created by IntelliJ IDEA.
  User: tungld
  Date: 02/04/2023
  Time: 5:42 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View customer</title>
</head>
<body>
<h1>
    View customer
</h1>
<p>
    <a href="/customers">Back to customer list</a>
</p>
<form method="get">
    <table border="1px">
    <tr>
        <td>Name</td>
        <td>${requestScope["customer"].getName()}</td>
    </tr>
    <tr>
        <td>Email</td>
        <td>${requestScope["customer"].getEmail()}</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>${requestScope["customer"].getAddress()}</td>
    </tr>
    </table>
</form>
</body>
</html>
