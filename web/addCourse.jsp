<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 13.09.2017
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="jspf/navbar.jspf" %>
    <link href="css/inputForm.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="/addCourse" method="post" class="form-input">
    <div class="input-block">
        <input type="hidden" name="course_id">
    </div>
    <div class="input-block">
        <label>Course Name</label>
        <input type="text" class="input-text" name="course_name" placeholder="Course name" required>
    </div>
    <div class="input-block">
        <label>Topic</label>
        <input type="text" class="input-text" name="topic" value="${sessionScope.topic}">
    </div>
    <div class="input-block">
        <label>Teacher</label>
        <select class="input-text" name="teacher">
            <c:forEach var="t" items="${requestScope.teachers}">
                <option selected="selected">${t.firstName} ${t.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="input-block">
        <label>Duration</label>
        <input class="input-text" type="text" name="duration" placeholder="Duration" required >
    </div>
    <div class="input-block">
        <label>Start date</label>
        <input class="input-text" type="date" name="startDate" placeholder="Start date" required>
    </div>
    <div class="input-block">
        <label>End date</label>
        <input class="input-text" type="date" name="endDate" placeholder="End date">
    </div>
    <input type="submit" class="input-button" name="add" value="Add Course">
</form>

</body>
</html>
