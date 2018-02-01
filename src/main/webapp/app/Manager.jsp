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


        <div class="row">
            <div class="col-lg-8">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Unassigned orders
                    </div>

                    <div class="panel-body">
                        <c:choose>
                            <c:when test="${ridesUnassigned.size()==0}">
                                <p>No unassigned rides at the moment</p>
                            </c:when>
                            <c:otherwise>
                                <table width="100%" class="table table-striped">
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
                                    <c:forEach items="${ridesUnassigned}" var="ride">
                                        <tr>
                                            <td><c:out value="${ride.id}"/></td>
                                            <td><c:out value="${ride.customer.name}"/></td>
                                            <td><c:out value="${ride.name}"/></td>
                                            <td><c:out value="${ride.mass}"/></td>
                                            <td><c:out value="${ride.volume}"/></td>
                                <td><fmt:message key="status.${ride.status}"/></td>
                                            <td>
                                                <button onclick="location.href='ManagerController?action=transportList&id=<c:out
                                            value="${ride.id}"/>'" type="button" class="btn btn-primary">
                                        <fmt:message key="manager.button.choose"/>
                                                    vehicle
                                        for the order
                                                </button>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <!-- /.col-lg-4 -->
        </div>


        <div class="row">
            <div class="col-lg-8">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        Orders in process
                    </div>

                    <div class="panel-body">
                        <c:choose>
                            <c:when test="${ridesInProcess.size()==0}">
                                <p>No rides in process at the moment</p>
                            </c:when>
                            <c:otherwise>
                                <table width="100%" class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>Ride Id</th>
                                        <th>Customer name</th>
                                        <th>Ride destination</th>
                                        <th>Mass</th>
                                        <th>Volume</th>
                                        <th>Driver name</th>
                                        <th>Order status</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${ridesInProcess}" var="ride">
                                        <tr>
                                            <td><c:out value="${ride.id}"/></td>
                                            <td><c:out value="${ride.customer.name}"/></td>
                                            <td><c:out value="${ride.name}"/></td>
                                            <td><c:out value="${ride.mass}"/></td>
                                            <td><c:out value="${ride.volume}"/></td>
                                            <td><c:out value="${ride.executor.driver.name}"/></td>
                                            <td><c:out value="${ride.status}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-8">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Archived orders
                    </div>

                    <div class="panel-body">
                        <c:choose>
                            <c:when test="${ridesFinished.size()==0 and ridesCanceled.size()==0}">
                                <p>No archived orders at the moment</p>
                            </c:when>
                            <c:otherwise>
                                <table width="100%" class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>Ride Id</th>
                                        <th>Customer name</th>
                                        <th>Ride destination</th>
                                        <th>Mass</th>
                                        <th>Volume</th>
                                        <th>Driver name</th>
                                        <th>Order status</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${ridesFinished}" var="ride">
                                        <tr>
                                            <td><c:out value="${ride.id}"/></td>
                                            <td><c:out value="${ride.customer.name}"/></td>
                                            <td><c:out value="${ride.name}"/></td>
                                            <td><c:out value="${ride.mass}"/></td>
                                            <td><c:out value="${ride.volume}"/></td>
                                            <td><c:out value="${ride.executor.driver.name}"/></td>
                                            <td><c:out value="${ride.status}"/></td>
                                        </tr>
                                    </c:forEach>
                                    <c:forEach items="${ridesCanceled}" var="ride">
                                        <tr>
                                            <td><c:out value="${ride.id}"/></td>
                                            <td><c:out value="${ride.customer.name}"/></td>
                                            <td><c:out value="${ride.name}"/></td>
                                            <td><c:out value="${ride.mass}"/></td>
                                            <td><c:out value="${ride.volume}"/></td>
                                            <td>None</td>
                                            <td><c:out value="${ride.status}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>


</div>
</body>
</html>