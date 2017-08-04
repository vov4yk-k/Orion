<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vova
  Date: 23.04.2017
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://orionwork.eu/wp-content/uploads/2016/08/cropped-favicon-32x32.png">

    <title>Sign in</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/cover.css" rel="stylesheet">
</head>

<body>

<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">

            <div class="masthead clearfix">
                <div class="inner">

                    <img src="resources/img/cropped-NAV-LOGOstd.png" alt="Mountain View" style="width:134px;height:41px;margin-left:5%" class="masthead-brand">
                    <nav class="nav nav-masthead">
                        <a class="nav-link active" href="#">Home</a>
                        <a class="nav-link" href="#">Features</a>
                        <a class="nav-link" href="#">Contact</a>
                    </nav>
                </div>
            </div>

            <div class="inner cover">

                <div class="container">
                    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                        <font color="white">
                            Your login attempt was not successful due to <br/><br/>
                            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                        </font>
                    </c:if>
                    <spring:url var="authUrl" value="/j_spring_security_check" />
                    <form class="form-signin" name='frm' method='POST' action="${authUrl}?${_csrf.parameterName}=${_csrf.token}">
                        <h2 class="form-signin-heading" >Please sign in</h2>
                        <label for="inputEmail" class="sr-only">Email address</label>
                        <input type="text" id="inputEmail" class="form-control" placeholder="Email address" name='username' required="" autofocus="">
                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name='password' required="">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="remember-me" name="_spring_security_remember_me"> Remember me
                            </label>
                        </div>
                        <button value="Sign in" id="btnSubmit" class="btn btn-lg btn-primary btn-block" name="submit" type="submit">Sign in</button>
                        <!--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />-->
                    </form>

                </div>


            </div>

            <div class="mastfoot">
                <div class="inner">
                </div>
            </div>

        </div>

    </div>

</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/resources/js/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/resources/js/jquery.min.js"><\/script>')</script><script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/resources/js/ie10-viewport-bug-workaround.js"></script>


</body></html>
</html>
