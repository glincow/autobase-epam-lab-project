<%@include file="WEB-INF/i18n.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><fmt:message key="signIn.text.title"/></title>

    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bootstrap/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Validation error messages CSS -->
    <link href="bootstrap/parsley.css" rel="stylesheet">

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
                            <h3 class="panel-title"><fmt:message key="signIn.text.greeting"/></h3>
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
                    <form id="LogIn" action="/signIn" method=post class="form-signin" role="form" data-parsley-required>

                        <fieldset>
                            <div class="form-group">
                                <label for="exampleInputEmail1"><fmt:message key="signIn.label.login"/></label>
                                <input class="form-control" id="exampleInputEmail1"
                                       placeholder="<fmt:message key="signIn.label.loginpl"/>"
                                       name="login"
                                       type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1"><fmt:message key="signIn.label.password"/></label>
                                <input class="form-control" id="exampleInputPassword1"
                                       placeholder="<fmt:message key="signIn.label.passwordpl"/>"
                                       name="password" type="password"
                                       value="">
                            </div>

                            <!-- <div class="checkbox">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                </label>
                            </div> -->

                            <button class="btn btn-lg btn-success btn-block" type="submit" value="SignIn"><fmt:message
                                    key="signIn.button.signIn"/></button>
                        </fieldset>
                    </form>
                    <p></p>
                    <div class="text-center">
                        <a class="d-block small mt-3" href="/sign-up.jsp"><fmt:message key="signIn.button.signUp"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<!-- Form validation -->
<script src="js/jquery-3.3.1.js"></script>
<script src="js/parsley.js"></script>
<script src="js/i18n/${language}.js"></script>
<script type="text/javascript">
    $('#LogIn').parsley();
</script>

</body>
</html>
