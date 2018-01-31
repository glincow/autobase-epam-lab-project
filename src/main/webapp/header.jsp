<%@include file="WEB-INF/i18n.jspf" %>
<html>
<head>
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

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <a class="navbar-brand"><fmt:message key="header.text.name"/></a>
    </div>

    <ul class="nav navbar-top-links navbar-right">
        <li>
            <form class="navbar-form" action="/signOut" method="post">
                <button class="btn btn-outline btn-default" type="submit" value="SignOut"><i
                        class="fa fa-sign-out fa-fw"></i> <fmt:message key="header.button.signOut"/>
                </button>
            </form>
        </li>
    </ul>

    <p class="navbar-text navbar-right"><i class="fa fa-user fa-fw"></i><fmt:message
            key="header.text.signedIn"/> ${user.getName()} (${user.getRole()})</p>

</nav>

</body>
</html>
