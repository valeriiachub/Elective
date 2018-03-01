<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 15.09.2017
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="jspf/studentNavbar.jspf" %>
    <link rel="stylesheet" href="css/inputForm.css" type="text/css">
</head>
<body>
<form class="form-input">
<table>
    <td>№</td>
    <td>Course Name</td>
    <td>Mark</td>
<c:forEach var="entry" items="${requestScope.passedCourses}" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${entry.key.name}</td>
            <td>${entry.value}</td>
        </tr>
</c:forEach>
</table>
</form>
</body>
</html>
