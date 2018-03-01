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
    <%@ include file="jspf/navbar.jspf" %>
    <link rel="stylesheet" href="css/profile.css" type="text/css">
</head>
<body>
<div class="information">
    <span class="field">First name: ${sessionScope.user.firstName}</span><br>

    <span class="field">Last name: ${sessionScope.user.lastName}</span><br>

    <span class="field">Role: Administrator </span><br>
</div>
</body>
</html>
