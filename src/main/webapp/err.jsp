<%@include file="WEB-INF/i18n.jspf" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="header.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-4">
                <h1 class="page-header">
                    <fmt:message key="error.header"/>
                </h1>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <fmt:message key="error.forbidden"/>
                    </div>
                    <div class="panel-body">
                        <p><fmt:message key="error.forbidden.message"/></p>
                    </div>

                </div>
                <form method="post" action="/errRedirect">
                    <input name="login" value="<c:out value="${user.role}"/>" hidden>
                    <button class="btn btn-primary" type="submit" value="SignIn"><fmt:message key="trlist.button.back"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
