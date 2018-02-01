<%@include file="../WEB-INF/i18n.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title><fmt:message key="driver.text.title"/></title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-8">
                <h1 class="page-header"><fmt:message key="driver.text.header"/></h1>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <fmt:message key="driver.actride.title"/>
                    </div>
                    <c:choose>
                        <c:when test="${activeRidesCount==0}">
                            <div class="panel-body">
                                <p><fmt:message key="driver.text.noactivity"/></p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="panel-body">
                                <table width="100%" class="table">
                                    <tbody>
                                    <tr>
                                        <th><fmt:message key="ride.id"/></th>
                                        <td><c:out value="${activeRide.id}"/></td>
                                    </tr>
                                    <tr>
                                        <th><fmt:message key="ride.destination"/></th>
                                        <td><c:out value="${activeRide.name}"/></td>
                                    </tr>
                                    <tr>
                                        <th><fmt:message key="ride.mass"/></th>
                                        <td><c:out value="${activeRide.mass}"/></td>
                                    </tr>
                                    <tr>
                                        <th><fmt:message key="ride.volume"/></th>
                                        <td><c:out value="${activeRide.volume}"/></td>
                                    </tr>
                                    <tr>
                                        <th><fmt:message key="ride.manager"/></th>
                                        <td><c:out value="${activeRide.manager.name}"/></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="panel-footer">
                                <button onclick="location.href='DriverController?action=finishRide&id=<c:out
                                        value="${activeRide.id}"/>'" type="button" class="btn btn-primary"><fmt:message
                                        key="driver.button.finish"/>
                                </button>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <!-- /.col-lg-4 -->


            <div class="col-lg-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <fmt:message key="driver.vehicle.title"/>
                    </div>
                    <div class="panel-body">
                        <table width="100%" class="table">
                            <tbody>
                            <tr>
                                <th><fmt:message key="vehicle.id"/></th>
                                <td><c:out value="${transport.id}"/></td>
                            </tr>
                            <tr>
                                <th><fmt:message key="vehicle.mass"/></th>
                                <td><c:out value="${transport.maxMass}"/></td>
                            </tr>
                            <tr>
                                <th><fmt:message key="vehicle.volume"/></th>
                                <td><c:out value="${transport.maxVolume}"/></td>
                            </tr>
                            <tr>
                                <th><fmt:message key="vehicle.works"/></th>
                                <td><c:out value="${transport.isAutoWorks}"/></td>
                            </tr>
                            <tr>
                                <th><fmt:message key="vehicle.available"/></th>
                                <td><c:out value="${transport.isAutoAvailable}"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <button onclick="location.href='/DriverController?action=statusEdit&id=<c:out
                                value="${ride.id}"/>'" type="button" class="btn btn-primary"><fmt:message
                                key="driver.button.edit"/>
                        </button>
                    </div>
                </div>
            </div>
            <!-- /.col-lg-4 -->
        </div>

        <div class="row">
            <div class="col-lg-8">
                <div class="panel panel-default">

                    <div class="panel-heading">
                        <fmt:message key="driver.ride.title"/>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-8">
                                <table width="100%" class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th><fmt:message key="ride.id"/></th>
                                        <th><fmt:message key="ride.destination"/></th>
                                        <th><fmt:message key="ride.mass"/></th>
                                        <th><fmt:message key="ride.volume"/></th>
                                        <th><fmt:message key="ride.manager"/></th>
                                        <th><fmt:message key="ride.status"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${rides}" var="ride">
                                        <tr>
                                            <td><c:out value="${ride.id}"/></td>
                                            <td><c:out value="${ride.name}"/></td>
                                            <td><c:out value="${ride.mass}"/></td>
                                            <td><c:out value="${ride.volume}"/></td>
                                            <td><c:out value="${ride.manager.name}"/></td>
                                            <td><fmt:message key="status.${ride.status}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>