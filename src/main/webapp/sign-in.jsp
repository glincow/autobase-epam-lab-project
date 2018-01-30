<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>SignIn</title>

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
                    <h3 class="panel-title">Please Sign In</h3>
                </div>
                <div class="panel-body">
                    <form action="/signIn" method=post class="form-signin" role="form">

                        <fieldset>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input class="form-control" id="exampleInputEmail1" placeholder="Enter e-mail" name="login"
                                       type="email" autofocus>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input class="form-control" id="exampleInputPassword1" placeholder="Enter password"
                                       name="password" type="password"
                                       value="">
                            </div>

                            <!-- <div class="checkbox">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                </label>
                            </div> -->

                            <button class="btn btn-lg btn-success btn-block" type="submit" value="SignIn">Login</button>
                        </fieldset>
                    </form>
                    <p></p>
                    <div class="text-center">
                        <a class="d-block small mt-3" href="/sign-up.jsp">Register an Account</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--<form action="/signUp" method=get class="form-signin">
    <button class="btn btn-lg btn-primary btn-block" type="submit" value="SignUp">Sign up</button>
</form> -->

<!-- jQuery -->
<script src="bootstrap/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="bootstrap/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="bootstrap/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="bootstrap/dist/js/sb-admin-2.js"></script>

</body>
</html>
