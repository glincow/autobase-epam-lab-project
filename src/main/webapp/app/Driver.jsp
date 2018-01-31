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
            <div class="col-lg-12">
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
                                <table width="100%" class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>Ride Id</th>
                                        <th>Ride name</th>
                                        <th>Ride mass</th>
                                        <th>Ride volume</th>
                                        <th>Ride status</th>
                                        <th colspan=1>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${activeRides}" var="ride">
                                        <tr>
                                            <td><c:out value="${ride.id}"/></td>
                                            <td><c:out value="${ride.name}"/></td>
                                            <td><c:out value="${ride.mass}"/></td>
                                            <td><c:out value="${ride.volume}"/></td>
                                            <td><c:out value="${ride.status}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="panel-footer">

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
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tincidunt est vitae
                            ultrices
                            accumsan. Aliquam ornare lacus adipiscing, posuere lectus et, fringilla augue.</p>
                    </div>
                    <div class="panel-footer">
                        Panel Footer
                    </div>
                </div>
            </div>
            <!-- /.col-lg-4 -->
        </div>


        <div class="panel panel-default">
            <div class="panel-heading">
                Your vehicle information
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-8">
                        <table width="100%" class="table table-striped">
                            <thead>
                            <tr>
                                <th>Transport Id</th>
                                <th>Max mass</th>
                                <th>Max volume</th>
                                <th>isAutoWorks</th>
                                <th>isAutoAvailable</th>
                                <th colspan=1>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><c:out value="${transport.id}"/></td>
                                <td><c:out value="${transport.maxMass}"/></td>
                                <td><c:out value="${transport.maxVolume}"/></td>
                                <td><c:out value="${transport.isAutoWorks}"/></td>
                                <td><c:out value="${transport.isAutoAvailable}"/></td>
                                <td>
                                    <a href="/DriverController?action=statusEdit&id=<c:out value="${ride.id}"/>">Edit</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <br>
            <br>
        </div>
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
                                <th>Ride Id</th>
                                <th>Ride name</th>
                                <th>Ride mass</th>
                                <th>Ride volume</th>
                                <th>Ride status</th>
                                <th colspan=1>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${rides}" var="ride">
                                <tr>
                                    <td><c:out value="${ride.id}"/></td>
                                    <td><c:out value="${ride.name}"/></td>
                                    <td><c:out value="${ride.mass}"/></td>
                                    <td><c:out value="${ride.volume}"/></td>
                                    <td><c:out value="${ride.status}"/></td>
                                    <td><c:if test="${ride.status=='IN_PROCESS'}">
                                        <a href="DriverController?action=finishRide&id=<c:out value="${ride.id}"/>">Finish
                                            ride</a>
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