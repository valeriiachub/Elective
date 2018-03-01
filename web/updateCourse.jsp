<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 13.09.2017
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.Date" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="jspf/navbar.jspf" %>
    <link href="css/inputForm.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="/updateCourse" method="post" class="form-input" id="myform">

    <input  type="hidden" name="course_id" form="myform" value="${course.course_id}">
    <div class="input-block">
        <label>Course name:</label> <input class="input-text" type="text" name="course_name" form="myform" value="${course.name}">
    </div>
    <div class="input-block">
        <label>Topic:</label> <input class="input-text" type="text" name="topic" form="myform" value="${course.topic}">
    </div>
    <div class="input-block">
        <label>Teacher:</label>  <select class="input-text" name="teacher">
        <c:forEach var="t" items="${requestScope.teachers}">
            <option selected="">${t.firstName} ${t.lastName}</option>
        </c:forEach>
    </select>
    </div>
    <div class="input-block">
        <label>Duration:</label> <input class="input-text" type="text" name="duration" form="myform" value="${course.duration}">
    </div>
    <div class="input-block">
        <label>Start date:</label>  <input class="input-text" type="date" name="startDate" form="myform" value="${course.startDate}">
    </div>
    <div class="input-block">
        <label>End date:</label><input class="input-text" type="date" name="endDate" form="myform" value="${course.endDate}">
    </div>
    <div class="input-block">
        <input type="submit" class="input-button" name="update" value="Update" form="myform">
    </div>
</form>
</body>
</html>
