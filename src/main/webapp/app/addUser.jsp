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
        <div class="col-lg-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <fmt:message key="adduser.table.header"/>
                </div>
                <div class="panel-body">
                    <form id="addManager" method="POST" action='AdminController' name="frmAddManager" role="form">
                        <fieldset>
                            <div class="form-group">
                                <label for="userName"><fmt:message key="user.name"/></label>
                                <input class="form-control" type="text" name="name" id="userName"
                                       value="<c:out value="${jspUser.name}" />" required
                                       placeholder="<fmt:message key="signUp.label.namepl"/>"
                                       data-parsley-required data-parsley-length="[3, 20]"/>
                            </div>
                            <div class="form-group">
                                <label for="userLogin"><fmt:message key="user.login"/></label>
                                <input class="form-control" type="text" name="login" id="userLogin"
                                       value="<c:out value="${jspUser.login}" />" required
                                       placeholder="<fmt:message key="signIn.label.loginpl"/>"
                                       data-parsley-required data-parsley-pattern="/^[a-z0-9_-]{3,16}$/"/>
                            </div>
                            <div class="form-group">
                                <label for="userPassword"><fmt:message key="user.password"/></label>
                                <input class="form-control" type="password" name="password" id="userPassword"
                                       value="<c:out value="${jspUser.password}" />" required
                                       placeholder="<fmt:message key="signIn.label.passwordpl"/>"
                                       data-parsley-required data-parsley-pattern="/^[a-z0-9_-]{3,18}$/"/>
                            </div>
                            <div class="form-group">
                                <label for="inputPasswordAgain"><fmt:message
                                        key="signUp.label.passwordAgain"/></label>
                                <input class="form-control" type="password" name="password" id="inputPasswordAgain"
                                       placeholder="<fmt:message key="signUp.label.passwordAgainpl"/>"
                                       value="" data-parsley-required data-parsley-length="[3, 10]"
                                       data-parsley-equalto="#userPassword"/>
                            </div>
                            <div class="form-group">
                                <c:choose> <%--for create only manager and edit everybody except driver--%>
                                    <c:when test="${jspUser.role == null}">
                                        <input type="hidden" name="role" value="MANAGER"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="role" value="<c:out value="${jspUser.role}" />"/>
                                    </c:otherwise>
                                </c:choose>
                                <input type="hidden" name="id" value="<c:out value="${jspUser.id}" />"/>
                            </div>
                        </fieldset>
                        <br>
                        <c:choose>
                            <c:when test="${errorId == 3}">
                                <div class="alert alert-danger">
                                    <fmt:message key="signUp.error"/>
                                </div>
                            </c:when>
                        </c:choose>
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

<!-- Form validation -->
<script type="text/javascript">
    $('#addManager').parsley();
</script>
</body>
</html>

