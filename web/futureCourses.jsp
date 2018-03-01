<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 15.09.2017
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="jspf/studentNavbar.jspf" %>
    <link href="/css/inputForm.css" rel="stylesheet" type="text/css">
</head>
<body>
<form></form>
<ol>
    <c:forEach var="entry" items="${requestScope.registered}">
        <li>${entry.key.name}
            <c:set var="b" value="${entry.value}"/>
            <c:choose>
                <c:when test="${b == true}">
                    <form action="/regOnCourse?reg=false&courseName=${entry.key.name}" method="post">
                        <input type="submit" value="Unregister">
                    </form>
                    <%--<a class="btn btn-default"
                       href="/regOnCourse?reg=false&courseName=${entry.key.name}&login=${requestScope.login}">Unregister</a>--%>
                </c:when>
                <c:otherwise>
                    <form action="/regOnCourse?reg=true&courseName=${entry.key.name}" method="post">
                        <input type="submit" value="Register">
                    </form>
                    <%--<a class="btn btn-default"
                       href="/regOnCourse?reg=true&courseName=${entry.key.name}&login=${requestScope.login}">Register</a>--%>
                </c:otherwise>
            </c:choose>
        </li>
    </c:forEach>
</ol>
</form>
</body>
</html>
