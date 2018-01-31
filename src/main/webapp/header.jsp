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

    <!-- dropdown menu in the navbar

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                    </li>
                    <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>

        </li>

    </ul>

    -->

    <ul class="nav navbar-top-links navbar-right">
        <li>
            <form class="navbar-form" action="/signOut" method="post">
                <button class="btn btn-outline btn-default" type="submit" value="SignOut"><i
                        class="fa fa-sign-out fa-fw"></i> <fmt:message key="header.button.signOut"/>
                </button>
            </form>
        </li>
    </ul>



    <p class="navbar-text navbar-right"><i class="fa fa-user fa-fw"></i> ${user.getRole()}</p>
    <p class="navbar-text navbar-right"><fmt:message key="header.text.greeting"/> ${user.getName()}!</p>

</nav>

</body>
</html>
