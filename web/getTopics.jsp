<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 11.09.2017
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="jspf/navbar.jspf" %>
    <link href="css/topicMenu.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:forEach var="c" items="${requestScope.topics}">
    <div class="topic">
        <span class="language">${c.topicName}</span>
        <a href="/courses?topic=${c.topicName}" class="get-courses">Get Courses</a>
    </div>

    <%--<div class="item">
        <output name="topic">${c.topicName}</output>
    </div>
    <div>
        <a href="/courses?topic=${c.topicName}">Get courses</a>
    </div>--%>
</c:forEach>
</body>
</html>


<%--<div class="topic">
    <span class="language">Language</span>
    <a href="" class="get-courses">Get Courses</a>
</div>
<div class="topic">
    <span class="language">Language</span>
    <a href="" class="get-courses">Get Courses</a>
</div>
<div class="topic">
    <span class="language">Language</span>
    <a href="" class="get-courses">Get Courses</a>
</div>
<div class="topic">
    <span class="language">Language</span>
    <a href="" class="get-courses">Get Courses</a>
</div>
<div class="topic">
    <span class="language">Language</span>
    <a href="" class="get-courses">Get Courses</a>
</div>--%>
