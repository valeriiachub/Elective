<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 15.09.2017
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="jspf/studentNavbar.jspf" %>
</head>
<body>
<ul>
    <c:forEach var="c" items="${requestScope.currentCourses}">
        <li>
            <a href="">${c.name}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
