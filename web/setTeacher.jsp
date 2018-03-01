<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 14.09.2017
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="css/inputForm.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="/setTeacher" method="post" class="form-input">
    <div>
        <input type="hidden" name="name" value="${requestScope.name}">
        <label>Please, choose teacher: </label>
        <select name="teacher" class="input-text">
            <c:forEach var="t" items="${requestScope.teachers}">
                <option>${t.firstName} ${t.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <input type="submit" class="input-button" name="setTeacher" value="Change">
</form>
</body>
</html>
