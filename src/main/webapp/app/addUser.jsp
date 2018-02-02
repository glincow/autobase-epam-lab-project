<%@include file="../WEB-INF/i18n.jspf" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title><fmt:message key="adduser.text.title"/></title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-8">
                <h1 class="page-header">
                    <c:choose>
                        <c:when test="${jspUser.role == null}">
                            <fmt:message key="adduser.text.manheader"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="adduser.text.editheader"/>
                        </c:otherwise>
                    </c:choose>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <fmt:message key="adduser.table.header"/>
                    </div>
                    <div class="panel-body">

                        <form method="POST" action='AdminController' name="frmAddManager" role="form">
                            <fieldset>
                                <div class="form-group">
                                    <label for="userName"><fmt:message key="user.name"/></label>
                                    <input class="form-control" type="text" name="name" id="userName"
                                           value="<c:out value="${jspUser.name}" />" required
                                           placeholder="<fmt:message key="signUp.label.namepl"/>"/>
                                </div>
                                <div class="form-group">
                                    <label for="userLogin"><fmt:message key="user.login"/></label>
                                    <input class="form-control" type="text" name="login" id="userLogin"
                                           value="<c:out value="${jspUser.login}" />" required
                                           placeholder="<fmt:message key="signIn.label.loginpl"/>"/>
                                </div>
                                <div class="form-group">
                                    <label for="userPassword"><fmt:message key="user.password"/></label>
                                    <input class="form-control" type="password" name="password" id="userPassword"
                                           value="<c:out value="${jspUser.password}" />" required
                                           placeholder="<fmt:message key="signIn.label.passwordpl"/>"/>
                                </div>


                                <c:choose> <%--for create only manager and edit everybody except driver--%>
                                <c:when test="${jspUser.role == null}">
                                <input type="hidden" name="role" value="MANAGER"/>
                                </c:when>
                                <c:otherwise>
                                <input type="hidden" name="role" value="<c:out value="${jspUser.role}" />"/>
                                </c:otherwise>
                                </c:choose>
                                <input type="hidden" name="id" value="<c:out value="${jspUser.id}" />"/>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <button onclick="location.href='/AdminController?action=listUsers'"
                                                type="button"
                                                class="btn btn-primary"><fmt:message key="adduser.button.back"/>
                                        </button>
                                    </div>
                                    <div class="col-lg-6">
                                        <button class="btn btn-primary btn-success pull-right" type="submit"
                                                value="Submit">
                                            <fmt:message key="adduser.button.confirm"/>
                                        </button>
                                    </div>
                                </div>

                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

