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
    <title>Delete customer</title>
</head>
<body>
<h1>Delete customer</h1>
<p>
    <a href="/customers">Back to customer list</a>
</p>

<form method="post">
    <h2>Are you sure ???</h2>
    <fieldset>
        <legend>
            <table>
                <tr>
                    <td></td>
                    <td>Name: ${requestScope["customer"].getName()}</td>
                </tr>
                <tr>
                    <td></td>
                    <td>Email: ${requestScope["customer"].getEmail()}</td>
                </tr>
                <tr>
                    <td></td>
                    <td>Address: ${requestScope["customer"].getAddress()}</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Delete customer">
                    </td>
                </tr>
            </table>
        </legend>
    </fieldset>
</form>
</body>
</html>
