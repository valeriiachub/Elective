<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 12.09.2017
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="jspf/navbar.jspf" %>
    <link rel="stylesheet" type="text/css" href="css/getCourses.css">
</head>
<body>
<form class="input-block">
    <ul>
        <c:forEach var="c" items="${sessionScope.courses}">
            <c:url value="/updateCourse" var="myURL">
                <c:param name="name" value="${c.name}"/>
            </c:url>
            <li>
                <span class="name">${c.name}</span>
                <a href="${myURL}" class="btn btn-default">Update Course</a>
                <a href="/deleteCourse?id=${c.course_id}" class="btn btn-default">Delete Course</a>
                <a href="/setTeacher?name=${c.name}" class="btn btn-default">Set Teacher</a>
                <a href="/addCourse" class="add-button">Add new course</a>
            </li>

        </c:forEach>
    </ul>

</form>
<%--<form class="form-input">
    <ul>
        <c:forEach var="c" items="${sessionScope.courses}">
            <c:url value="/updateCourse" var="myURL">
                <c:param name="name" value="${c.name}"/>
            </c:url>
            <li>${c.name}
                <a href="${myURL}" class="btn btn-default">Update Course</a>
                <a href="/deleteCourse?id=${c.course_id}" class="btn btn-default">Delete Course</a>
                <a href="/setTeacher?name=${c.name}" class="btn btn-default">Set Teacher</a></li>
        </c:forEach>
    </ul>
    <a href="/addCourse" class="add-button">Add new course</a>
</form>--%>
</body>
</html>
