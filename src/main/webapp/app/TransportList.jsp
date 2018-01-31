<%--
  Created by IntelliJ IDEA.
  User: Lind
  Date: 1/25/2018
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Choose transport</title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Assign a vehicle for the order</h1>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Avaliable vehicles
                    </div>
                    <div class="panel-body">
                        <table width="100%" class="table table-striped">
                            <thead>
                            <tr>
                                <th>Vehicle ID</th>
                                <th>Driver name</th>
                                <th>Max mass</th>
                                <th>Max volume</th>
                                <th colspan=1>Action</th>
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
                                        <a href="ManagerController?action=chooseTransport&id=<c:out value="${transport.id}"/>&rideId=<c:out value="${ride.id}"/>">Choose
                                            transport</a>
                                        <button onclick="location.href='ManagerController?action=chooseTransport&id=<c:out value="${transport.id}"/>&rideId=<c:out value="${ride.id}"/>'"
                                                type="button" class="btn btn-primary">Assign vehicle
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
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        The ride order to assign
                    </div>
                    <div class="panel-body">
                        <table width="100%" class="table table-striped">
                            <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Ride Destination</th>
                                <th>Mass</th>
                                <th>Volume</th>
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
                            class="btn btn-primary"> Back to orders list
                    </button>
                </p>

            </div>
            <!-- /.col-lg-4 -->



        </div>



    </div>
</div>
</body>
</html>