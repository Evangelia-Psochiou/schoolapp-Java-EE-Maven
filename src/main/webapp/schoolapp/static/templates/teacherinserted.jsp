<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Επιτυχής Εισαγωγή</title>
</head>
<body>
	<h1>Teacher inserted successfully</h1>
	<div>
		<p>${requestScope.insertedTeacher.lastname}</p>
		<p>${requestScope.insertedTeacher.firstname}</p>
		<p></p>
	</div>

	<div>
		<form method="post" action="${pageContext.request.contextPath}/schoolapp/static/templates/teachersmenu.jsp">
			<button class="btn"><i class="fa fa-close"></i>Close</button>
		</form>
	</div>
</body>
</html>