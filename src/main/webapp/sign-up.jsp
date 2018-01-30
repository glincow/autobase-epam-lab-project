<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link href="css/signin.css" rel="stylesheet">
</head>
<body>
<div class="text-center">
    <div>
        <form action="/signUp" method="post" class="form-signin">
            <label for="inputName" class="sr-only">Name</label>
            <input type="text" id="inputName" name="name"
                   class="form-control" placeholder="Name" required>

            <label for="inputLogin" class="sr-only">Login</label>
            <input type="text" id="inputLogin" name="login"
                   class="form-control" placeholder="Login" required>

            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" name="password"
                   class="form-control" placeholder="Password" required>

            <button class="btn btn-lg btn-primary btn-block" type="submit" value="SignUp">Sign up</button>
        </form>
    </div>
    <form action="/signIn" method="get" class="form-signin">
        <button class="btn btn-lg btn-primary btn-block" type="submit" value="SignIn">Back</button>
    </form>
</div>
</body>
</html>
