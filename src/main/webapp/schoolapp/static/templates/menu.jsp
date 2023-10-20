<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/schoolapp/static/css/menu.css">
</head>
<body>
<div class="container-fluid">
    <div class="header">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/schoolapp/static/img/aueb100b.png" alt="AUEB 100 years">
        </div>
        <div class="menu">
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="#">We Teach</a></li>
                <li><a href="#">We Innovate</a></li>
                <li><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
            </ul>
        </div>
    </div>
    <div class="button-container">
        <form method="post" action="${pageContext.request.contextPath}/schoolapp/static/templates/teachersmenu.jsp">
            <button class="button" name="button" value="teachers">Teachers</button>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/schoolapp/static/templates/usersmenu.jsp">
            <button class="button" name="button" value="users">Users</button>
        </form>
    </div>

    <div class="footer">
        <div class="copyright">
            <p>&copy; Coding Factory, AUEB,
                All rights reserved</p>
        </div>
        <div class="social">
            <p class="follow-us">Follow us:</p>
            <i class="fab fa-facebook-square"></i>
            <i class="fab fa-twitter-square"></i>
        </div>
    </div>
</div>
</body>
</html>