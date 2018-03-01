<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 14.09.2017
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="jspf/teacherNavbar.jspf" %>
    <link rel="stylesheet" href="css/getCourses.css" type="text/css">
</head>
<body>
<form class="form-input">
    <ul>
        <c:forEach var="c" items="${requestScope.courses}">
            <li class="">
                    ${c.name}
                <c:url value="/journal" var="journal">
                    <c:param name="id" value="${c.course_id}"/>
                    <c:param name="name" value="${c.name}"/>
                </c:url>
                <a href="${journal}" class="add-button">View journal</a>
            </li>
        </c:forEach>
    </ul>
</form>
<table>
    <td>Course name</td>
    <td>Success</td>
    <c:forEach var="entry" items="${requestScope.success}">
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
        </tr>
    </c:forEach>
    <tr>Total</tr>
    <tr>
        <c:forEach var="entry" items="${requestScope.success}">

        </c:forEach>
    </tr>
</table>
</body>
</html>
