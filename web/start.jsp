<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="" />
<!DOCTYPE html>
<html lang=>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/startPageStyle.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>ELECTIVE</title>
</head>
<body>
<div class="wrapper">
    <header>
        <span class="logo"><a href="#">ELECTIVE</a></span>
        <nav class="navbar">
            <ul>
                <li><a href="login.jsp">Log In</a></li>
                <li><a href="/courses">Courses</a></li>
                <li class="menu"><a href="">Language
                    <ul>
                        <li>English</li>
                        <li>Russian</li>
                    </ul>
                </a></li>
            </ul>
        </nav>
    </header>
    <div class="content">
        <a href="login.jsp" class="registrate">Registrate</a>
    </div>


</div>


</body>
</html>