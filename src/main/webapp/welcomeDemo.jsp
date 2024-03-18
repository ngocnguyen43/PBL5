<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 13/03/2024
  Time: 8:55 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome Page</title>
</head>
<body>
<h2>Welcome, <%= session.getAttribute("username") %>!</h2>
<p>You have successfully logged in.</p>
<a href="logoutDemo.jsp">Logout</a>
</body>
</html>

