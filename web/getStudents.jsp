<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 12.09.2017
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="jspf/navbar.jspf" %>
    <link href="css/getCourses.css" rel="stylesheet" type="text/css">
</head>
<body>
<form class="input-block">
    <ul>
        <c:forEach var="s" items="${sessionScope.students}">
            <li>
                <span class="data">${s.firstName} ${s.lastName} </span>
                <c:set var="b" value="${s.blocked}"/>
                <c:choose>
                    <c:when test="${b == false}">
                        <a class="add-button" href="/block?blocked=true&login=${s.login}">Block</a>
                    </c:when>
                    <c:otherwise> <a class="add-button" href="/block?blocked=false&login=${s.login}">Unblock</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:forEach>
    </ul>
</form>
</body>
</html>
