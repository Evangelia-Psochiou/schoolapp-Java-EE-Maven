<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
<body>
<p>User: ${requestScope.user.id} ${requestScope.user.username} ${requestScope.user.password}
  was deleted</p>
<div>
  <form method="post" action="${pageContext.request.contextPath}/schoolapp/static/templates/usersmenu.jsp">
    <button class="btn"><i class="fa fa-close"></i>Close</button>
  </form>
</div>
</body>
</html>
