<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp</title>

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
                    <h3 class="panel-title">Register an account</h3>
                </div>
                <div class="panel-body">


                    <form action="/signUp" method="post" class="form-signin" role="form">
                        <fieldset>
                            <div class="form-group">
                                <label for="inputName">Name</label>
                                <input class="form-control" id="inputName" placeholder="Enter name" type="text"
                                       name="name" autofocus
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="inputLogin">Login</label>
                                <input class="form-control" id="inputLogin" placeholder="Enter login"
                                       type="text" name="login" required>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword">Password</label>
                                <input class="form-control" id="inputPassword"
                                       placeholder="Enter password" type="password" name="password" value="">
                            </div>
                            <button class="btn btn-lg btn-success btn-block" type="submit" value="SignIn">
                                Register
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
                        <a class="d-block small mt-3" href="sign-in.jsp">Login Page</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
