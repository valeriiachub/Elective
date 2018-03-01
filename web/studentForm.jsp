<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 10.09.2017
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="jspf/studentNavbar.jspf" %>
    <link href="css/studentProfile.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="profile">

    <div class="information">
        <h1>Profile</h1><br>
        <span class="name">First name:${sessionScope.user.firstName}</span><br>

        <span class="name">Last name: ${sessionScope.user.lastName}</span><br>

        <span class="name">Role: Student </span><br>
        <a class="update" href="/updateStudent?login=${sessionScope.user.login}">Update profile</a><br><br>
    </div>
    <div class="courses">
        <h2>My courses:</h2>
        <ul>
            <li><a href="/currentCourses">Current courses</a></li>
            <li><a href="/passedCourses">Passed courses</a></li>
        </ul>
    </div>
    <div class="future-courses">
        <h2>Future courses:</h2>

        <a href="/futureCourses">View</a>

    </div>
</div>


<%--<div class="profile">
    <h1>Profile</h1><br>
    <div class="information">
        <span class="label label-info">First name: ${sessionScope.user.firstName}</span><br>

        <span class="label label-info">Last name: ${sessionScope.user.lastName}</span><br>

        <span class="label label-info">Role: Student </span><br>
        <a href="/updateStudent?login=${sessionScope.user.login}">Update profile</a><br><br>
    </div>
    <div class="courses">
        My courses:
        <ul>
            <li><a href="/currentCourses">Current courses</a></li>
            <li><a href="/passedCourses">Passed courses</a></li>
        </ul>
    </div>
    <div class="future-courses">
        Future courses:
        <ul>
            <li><a href="/futureCourses">View</a></li>
        </ul>
    </div>
</div>--%>
</body>
</html>

