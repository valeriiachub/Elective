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
    <link href="css/inputForm.css" rel="stylesheet" type="text/css">
    <%@ include file="jspf/navbar.jspf" %>
</head>
<body>
<form class="form-input" action="/addTeacher" method="post">

    <div class="input-block">
        <label>Enter First name: </label> <input class="input-text" placeholder="First name" name="first_name" required/>
    </div>

    <div class="input-block">
        <label>Enter Last name: </label> <input class="input-text" placeholder="Last name" name="last_name" required/>
    </div>
    <div class="input-block">
        <label>Enter Login: </label> <input class="input-text" placeholder="Login" name="login" required/>

    </div>
    <div class="input-block">
        <label>Enter Password: </label> <input class="input-text" placeholder="Password" name="password" required/>
    </div>
    <input type="submit" value="Add" class="add-button"/>
</form>
</body>
</html>

