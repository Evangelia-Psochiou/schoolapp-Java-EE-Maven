<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Teachers Search</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/schoolapp/static/css/teachersmenu.css">
</head>
<body>
  <div class="center">
    <p>Hello ${sessionScope.username}</p>
  </div>

  <div class="center">
    <div class="search-wrapper">
      <div class="bot-gap">
        <span class="title">Teachers Search</span>
      </div>
      <div class="bot-gap">
        <form method="POST" action="${pageContext.request.contextPath}/schoolapp/search">
          <input name="lastname" type="text" class="search rounded" placeholder="Insert teacher's lastname"
                 autofocus/>
          <br><br>
          <button class="search-btn rounded color-btn" type="submit">Search</button>
        </form>
      </div>
    </div>

    <div class="insert-wrapper">
      <div class="bot-gap">
        <span class="title">Teachers Insert</span>
      </div>
      <div class="bot-gap">
        <form method="POST" action="${pageContext.request.contextPath}/schoolapp/insert">
          <input name="lastname" type="text" value="${requestScope.insertedTeacher.lastname}"
                 class="insert rounded" placeholder="Last name" autofocus/><br>
          <input name="firstname" type="text" value="${requestScope.insertedteacher.firstname}"
                 class="insert rounded" placeholder="First name" autofocus/>
          <br><br>
          <button class="search-btn rounded color-btn" type="submit">Insert</button>
        </form>
      </div>
    </div>
    <div>
      <div class="button-container">
        <form method="post" action="${pageContext.request.contextPath}/schoolapp/static/templates/menu.jsp">
          <button class="btn"><i class="fa fa-bars"></i> Menu</button>
        </form>
      </div>
    </div>
  </div>


  <div class="center">
    <c:if test="${requestScope.isError}">
      <p>${requestScope.message}</p>
    </c:if>
  </div>

  <div class="center">
    <c:if test="${requestScope.teachersNotFound}">
      <p>No teachers found</p>
    </c:if>

    <p>${requestScope.error}</p>
  </div>


</body>
</html>


