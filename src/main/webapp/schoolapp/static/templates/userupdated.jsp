<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="centering">
    <h1>Νέα Στοιχεία User</h1>
    <p>user ${requestScope.user.username}</p>
    <p>user ${requestScope.user.password}</p>
    <div>
        <form method="post" action="${pageContext.request.contextPath}/schoolapp/static/templates/usersmenu.jsp">
            <button class="btn"><i class="fa fa-close"></i>Close</button>
        </form>
    </div>
</div>
</body>
</html>
