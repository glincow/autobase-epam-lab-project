<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>SignIn</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
</head>
<div class="text-center">
    <div>
        <form action="/signIn" method=post class="form-signin">
            <h1 class="h3 mb-3 font-weight-normal">Hello, please log in:</h1>
            <label for="inputLogin" class="sr-only">Login</label>
            <input type="text" id="inputLogin" name="login" class="form-control" placeholder="Login" required>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password"
                   required>
            <button class="btn btn-lg btn-primary btn-block" type="submit" value="SignIn">Sign in</button>
        </form>
    </div>
    <div>
        <form action="/signUp" method=get class="form-signin">
            <button class="btn btn-lg btn-primary btn-block" type="submit" value="SignUp">Sign up</button>
        </form>
    </div>
</div>
</body>
</html>
