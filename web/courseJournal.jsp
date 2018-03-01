<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 14.09.2017
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/inputForm.css" type="text/css">
    <%@ include file="jspf/teacherNavbar.jspf" %>
</head>
<body>
<c:url value="/updateJournal" var="updateJournal">
    <c:param name="id" value="${requestScope.id}"/>
</c:url>
<form action="${updateJournal}" method="post">
    <table>
        <td>№</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Mark</td>
        <c:forEach var="j" items="${requestScope.journal}" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${j.userFirstName}</td>
                <td>${j.userLastName}</td>
                <td><input type="text" name="${j.userId}" value="${j.mark}"></td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="submit" value="Update"></td>
        </tr>
    </table>
</form>
</body>
</html>
