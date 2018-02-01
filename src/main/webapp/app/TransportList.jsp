<%@include file="../WEB-INF/i18n.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title><fmt:message key="trlist.text.title"/></title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header"><fmt:message key="trlist.text.header"/></h1>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <fmt:message key="trlist.text.vehicleheader"/>
                    </div>
                    <div class="panel-body">
                        <table width="100%" class="table table-striped">
                            <thead>
                            <tr>
                                <th><fmt:message key="vehicle.id"/></th>
                                <th><fmt:message key="vehicle.driver"/></th>
                                <th><fmt:message key="vehicle.mass"/></th>
                                <th><fmt:message key="vehicle.volume"/></th>
                                <th colspan=1><fmt:message key="vehicle.action"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${transportList}" var="transport">
                                <tr>
                                    <td><c:out value="${transport.id}"/></td>
                                    <td><c:out value="${transport.driver.name}"/></td>
                                    <td><c:out value="${transport.maxMass}"/></td>
                                    <td><c:out value="${transport.maxVolume}"/></td>
                                        <%--<td><c:if test="${ride.status=='UNASSIGNED'}">
                                            <a href="ManagerController?action=transportList&id=<c:out value="${ride.id}"/>">Choose transport</a>
                                        </c:if></td>--%>
                                    <td>
                                        <button onclick="location.href='ManagerController?action=chooseTransport&id=<c:out value="${transport.id}"/>&rideId=<c:out value="${ride.id}"/>'"
                                                type="button" class="btn btn-primary">
                                            <fmt:message key="trlist.button.assign"/>
                                        </button>
                                    </td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>


            <div class="col-lg-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <fmt:message key="trlist.text.rideheader"/>
                    </div>
                    <div class="panel-body">
                        <table width="100%" class="table table-striped">
                            <thead>
                            <tr>
                                <th><fmt:message key="ride.id"/></th>
                                <th><fmt:message key="ride.destination"/></th>
                                <th><fmt:message key="ride.mass"/></th>
                                <th><fmt:message key="ride.volume"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><c:out value="${ride.id}"/></td>
                                <td><c:out value="${ride.name}"/></td>
                                <td><c:out value="${ride.mass}"/></td>
                                <td><c:out value="${ride.volume}"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <p>
                    <button onclick="location.href='/ManagerController?action='" type="button"
                            class="btn btn-primary"><fmt:message key="trlist.button.back"/>
                    </button>
                </p>

            </div>
            <!-- /.col-lg-4 -->


        </div>


    </div>
</div>
</body>
</html>