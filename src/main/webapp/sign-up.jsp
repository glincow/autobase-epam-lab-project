<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>
<form action="/signUp" method="post">
    <p><strong>Name: </strong>
        <input type="text" name="name" size="25">
    <p>
    <p><strong>Login: </strong>
        <input type="text" name="login" size="25">
    <p>
    <p><strong>Password: </strong>
        <input type="password" name="password" size="25">
    <p>
    <p>
        <input type="submit" value="SignUp">
</form>
<form>
    <input type="button" value="Go back!" onclick="history.back()">
</form>
</body>
</html>
