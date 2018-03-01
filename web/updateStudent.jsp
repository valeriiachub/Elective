<%--
  Created by IntelliJ IDEA.
  User: Lera
  Date: 13.09.2017
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="jspf/studentNavbar.jspf" %>
    <link href="css/inputForm.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="/updateStudent" class="form-input" method="post" id="myform">
    <input type="hidden" name="userId" form="myform" value="${user.user_id}"/>
    <input type="hidden" name="blocked" form="myform" value="${user.blocked}"/>
    <div class="input-block">
        <label>Login:</label><input class="input-text" type="text" name="login" form="myform" value="${user.login}"/>
    </div>
    <div class="input-block">
        <label>Password: </label><input class="input-text" type="text" name="password" form="myform">
    </div>
    <div class="input-block">
        <label>First name:</label><input class="input-text" type="text" name="firstName" form="myform" value="${user.firstName}"/>
    </div>
    <div class="input-block">
        <label>Last name:</label><input class="input-text" type="text" name="lastName" form="myform" value="${user.lastName}"/>
    </div>
    <div class="input-block">
        <input type="submit" class="input-button" name="update" value="Update" form="myform"/>
    </div>
</form>
</body>
</html>
