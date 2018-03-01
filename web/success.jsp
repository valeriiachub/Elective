<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 18.09.2017
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <td>№</td>
    <td>Course name</td>
    <td>Success</td>
    <c:forEach var="entry" items="${requestScope.success}">
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
