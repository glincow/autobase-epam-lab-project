<%@include file="../WEB-INF/i18n.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title><fmt:message key="admin.text.title"/></title>
</head>
<body>
<div id="wrapper">

    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-8">
                <h1 class="page-header"><fmt:message key="admin.text.header"/></h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <fmt:message key="admin.table.header"/>
                    </div>
                    <div class="panel-body">


                        <table width="100%" class="table table-striped">
                            <thead>
                            <tr>
                                <th><fmt:message key="user.name"/></th>
                                <th><fmt:message key="user.login"/></th>
                                <th><fmt:message key="user.role"/></th>
                                <th><fmt:message key="user.action"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td><c:out value="${user.name}"/></td>
                                    <td><c:out value="${user.login}"/></td>
                                    <td><fmt:message key="role.${user.role}"/></td>
                                    <td>
                                        <button onclick="location.href='AdminController?action=edit&id=<c:out
                                                value="${user.id}"/>'" type="button" class="btn btn-primary">
                                            <fmt:message key="admin.button.edit"/>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
            <div class="col-lg-2">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <fmt:message key="admin.table.headeradd"/>
                    </div>
                    <div class="panel-body">
                        <p>
                            <button onclick="location.href='AdminController?action=insertManager'" type="button"
                                    class="btn btn-primary"><fmt:message key="admin.button.manager"/>
                            </button>
                        </p>
                        <p>
                            <button onclick="location.href='AdminController?action=insertDriver'" type="button"
                                    class="btn btn-primary"><fmt:message key="admin.button.driver"/>
                            </button>
                        </p>
                    </div>
                </div>
            </div>
        </div>


    </div>

</body>
</html>