<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login Page</title>
</head>

<h2>Hello, please log in:</h2>
<form action="signIn" method=post>
    <p><strong>Please Enter Your User Name: </strong>
        <input type="text" name="login" size="25">
    <p>
    <p><strong>Please Enter Your Password: </strong>
        <input type="password" name="password" size="25">
    <p>
    <p>
        <input type="submit" value="SignIn">
</form>
<input type="button" value="SignUp">
</html>
