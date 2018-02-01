<%@include file="../WEB-INF/i18n.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title><fmt:message key="manager.text.title"/></title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header"><fmt:message key="manager.text.header"/></h1>
            </div>
        </div>


        <div class="panel-body">
            <div class="row">
                <div class="col-lg-8">

                    <table border=1 width="100%" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th><fmt:message key="ride.id"/></th>
                            <th><fmt:message key="ride.destination"/></th>
                            <th><fmt:message key="ride.mass"/></th>
                            <th><fmt:message key="ride.volume"/></th>
                            <th><fmt:message key="ride.status"/></th>
                            <th colspan=1><fmt:message key="ride.action"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${rides}" var="ride">
                            <tr>
                                <td><c:out value="${ride.id}"/></td>
                                <td><c:out value="${ride.name}"/></td>
                                <td><c:out value="${ride.mass}"/></td>
                                <td><c:out value="${ride.volume}"/></td>
                                <td><fmt:message key="status.${ride.status}"/></td>
                                <td><c:if test="${ride.status=='UNASSIGNED'}">
                                    <button onclick="location.href='ManagerController?action=transportList&id=<c:out
                                            value="${ride.id}"/>'" type="button" class="btn btn-primary">
                                        <fmt:message key="manager.button.choose"/>
                                    </button>
                                </c:if></td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>