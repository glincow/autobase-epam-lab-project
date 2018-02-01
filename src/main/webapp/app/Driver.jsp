<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Your rides</title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-8">
                <h1 class="page-header">Driver Page</h1>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Active ride
                    </div>
                    <c:choose>
                        <c:when test="${activeRidesCount==0}">
                            <div class="panel-body">
                                <p>You have no Active Rides at the moment</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="panel-body">
                                <table width="100%" class="table">
                                    <tbody>
                                    <tr>
                                        <th>Ride ID</th>
                                        <td><c:out value="${activeRide.id}"/></td>
                                    </tr>
                                    <tr>
                                        <th>Ride destination</th>
                                        <td><c:out value="${activeRide.name}"/></td>
                                    </tr>
                                    <tr>
                                        <th>Mass</th>
                                        <td><c:out value="${activeRide.mass}"/></td>
                                    </tr>
                                    <tr>
                                        <th>Volume</th>
                                        <td><c:out value="${activeRide.volume}"/></td>
                                    </tr>
                                    <tr>
                                        <th>Manager</th>
                                        <td><c:out value="${activeRide.manager.name}"/></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="panel-footer">
                                <button onclick="location.href='DriverController?action=finishRide&id=<c:out
                                        value="${activeRide.id}"/>'" type="button" class="btn btn-primary">Finish ride
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
                        Vehicle Info
                    </div>
                    <div class="panel-body">
                        <table width="100%" class="table">
                            <tbody>
                            <tr>
                                <th>Vehicle ID</th>
                                <td><c:out value="${transport.id}"/></td>
                            </tr>
                            <tr>
                                <th>Payload (tons)</th>
                                <td><c:out value="${transport.maxMass}"/></td>
                            </tr>
                            <tr>
                                <th>Body volume (cubic meters)</th>
                                <td><c:out value="${transport.maxVolume}"/></td>
                            </tr>
                            <tr>
                                <th>isAutoWorks</th>
                                <td><c:out value="${transport.isAutoWorks}"/></td>
                            </tr>
                            <tr>
                                <th>isAutoAvailable</th>
                                <td><c:out value="${transport.isAutoAvailable}"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <button onclick="location.href='/DriverController?action=statusEdit&id=<c:out
                                value="${ride.id}"/>'" type="button" class="btn btn-primary">Edit vehicle status
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
                        Your rides
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-8">
                                <table width="100%" class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>Order ID</th>
                                        <th>Ride destination</th>
                                        <th>Mass</th>
                                        <th>Volume</th>
                                        <th>Manager</th>
                                        <th>Order status</th>
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
                                            <td><c:out value="${ride.status}"/></td>
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