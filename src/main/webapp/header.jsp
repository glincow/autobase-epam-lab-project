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

            <form class="navbar-form" method="get">
                <i class="fa fa-globe"></i>
                <select id="language" name="language" onchange="submit()">
                    <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>EN</option>
                    <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>RU</option>
                </select>
                <input hidden name="action" value=<%= request.getParameter("action") %>>
                <input hidden name="id" value=<%= request.getParameter("id") %>>
                <input hidden name="rideId" value=<%= request.getParameter("rideId") %>>
            </form>
        </li>

        <li>
            <form class="navbar-form" action="/signOut" method="post">
                <button class="btn btn-outline btn-default" type="submit" value="SignOut"><i
                        class="fa fa-sign-out fa-fw"></i><fmt:message key="header.button.signOut"/>
                </button>
            </form>
        </li>


    </ul>




    <!-- /.dropdown -->



    <p class="navbar-text navbar-right"><i class="fa fa-user fa-fw"></i> <fmt:message key="role.${user.getRole()}"/></p>
    <p class="navbar-text navbar-right"><fmt:message key="header.text.greeting"/> ${user.getName()}!</p>

</nav>

</body>

</html>
