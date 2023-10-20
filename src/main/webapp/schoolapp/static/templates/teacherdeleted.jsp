<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Teacher: ${requestScope.teacher.id} ${requestScope.teacher.firstname} ${requestScope.teacher.lastname}
		was deleted</p>
	<div>
		<form method="post" action="${pageContext.request.contextPath}/schoolapp/static/templates/teachersmenu.jsp">
			<button class="btn"><i class="fa fa-close"></i>Close</button>
		</form>
	</div>
</body>
</html>
