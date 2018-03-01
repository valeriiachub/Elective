<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="MessagesBundle" />
<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <title>Sign-Up/Login Form</title>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="css/style.css">

</head>

<body>
<div class="form">

    <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
    </ul>

    <div class="tab-content">
        <div id="signup">
            <form action="/register" method="post">

                <div class="top-row">
                    <div class="field-wrap">
                        <label id="firstName">
                            First Name<span class="req">*</span>
                        </label>
                        <input type="text" name="firstName" required autocomplete="off"/>
                    </div>

                    <div class="field-wrap">
                        <label id="lastName">
                            Last Name<span class="req">*</span>
                        </label>
                        <input type="text" name="lastName" required autocomplete="off"/>
                    </div>
                </div>

                <div class="field-wrap">
                    <label id="regEmail">
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email" name="login" required autocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label id="regPassword">
                        Set A Password<span class="req">*</span>
                    </label>
                    <input type="password" name="password" required autocomplete="off"/>
                </div>

                <input id="regStart" type="submit" class="button button-block" value="Get Started">

            </form>

        </div>

        <div id="login">
            <h1>Welcome Back!</h1>

            <form action="/role" method="post">

                <div class="field-wrap">
                    <label id="logEmail">
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email" name="login" required autocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label id="logPassword">
                        Password<span class="req">*</span>
                    </label>
                    <input type="password" name="password" required autocomplete="off"/>
                </div>

                <input id="loginStart" type="submit" class="button button-block" value="Log In">

            </form>

        </div>

    </div><!-- tab-content -->

</div> <!-- /form -->
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script src="js/index.js"></script>

</body>
</html>
