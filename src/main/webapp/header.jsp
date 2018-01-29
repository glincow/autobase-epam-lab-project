<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand mb-0">WebSiteName</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <form class="form-inline" action="signOut" method="post">
                    <p class="navbar-text">Signed in as ${user.getName()} (${user.getRole()})</p>
                    <button class="btn btn-danger navbar-btn" type="submit" value="/signOut">Sign out</button>
                </form>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
