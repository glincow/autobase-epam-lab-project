<%@include file="WEB-INF/i18n.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><fmt:message key="signUp.text.title"/></title>

    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bootstrap/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">


    <!-- Custom CSS -->
    <link href="bootstrap/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bootstrap/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-6">
                            <h3 class="panel-title"><fmt:message key="signIn.button.signUp"/></h3>
                        </div>
                        <div class="col-md-6" >
                            <form class="pull-right" method="get">

                                <select id="language" name="language" onchange="submit()">
                                    <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>EN</option>
                                    <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>RU</option>
                                </select>
                                <input hidden name="action" value=<%= request.getParameter("action") %>>
                                <input hidden name="id" value=<%= request.getParameter("id") %>>
                                <input hidden name="rideId" value=<%= request.getParameter("rideId") %>>
                                <i class="fa fa-globe"></i>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="panel-body">


                    <form action="/signUp" method="post" class="form-signin" role="form">
                        <fieldset>
                            <div class="form-group">
                                <label for="inputName"><fmt:message key="signUp.label.name"/></label>
                                <input class="form-control" id="inputName"
                                       placeholder="<fmt:message key="signUp.label.namepl" />" type="text"
                                       name="name" autofocus
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="inputLogin"><fmt:message key="signUp.label.login"/></label>
                                <input class="form-control" id="inputLogin"
                                       placeholder="<fmt:message key="signUp.label.loginpl" />"
                                       type="text" name="login" required>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword"><fmt:message key="signUp.label.password"/></label>
                                <input class="form-control" id="inputPassword"
                                       placeholder="<fmt:message key="signUp.label.passwordpl" />" type="password"
                                       name="password" value="">
                            </div>
                            <button class="btn btn-lg btn-success btn-block" type="submit" value="SignIn">
                                <fmt:message key="signUp.button.signUp"/>
                            </button>
                        </fieldset>


                        <!-- <label for="inputName" class="sr-only">Name</label>
                        <input type="text" id="inputName" name="name"
                               class="form-control" placeholder="Name" required>

                        <label for="inputLogin" class="sr-only">Login</label>
                        <input type="text" id="inputLogin" name="login"
                               class="form-control" placeholder="Login" required>

                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" id="inputPassword" name="password"
                               class="form-control" placeholder="Password" required>

                        <button class="btn btn-lg btn-primary btn-block" type="submit" value="SignUp">Sign up</button> -->
                    </form>
                    <p></p>
                    <div class="text-center">
                        <a class="d-block small mt-3" href="sign-in.jsp"><fmt:message key="signUp.button.back"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
