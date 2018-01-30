<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Ride</title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Edit your ride</h1>
            </div>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-lg-6">
                    <form method="POST" action='CustomerController' name="Ride" role="form">
                        <fieldset>
                            <div class="form-group">
                                <label for="rideId">Ride ID</label>
                                <input class="form-control" type="text" name="id" readonly="readonly" id="rideId"
                                       value="<c:out value="${ride.id}" />"/>
                            </div>
                            <div class="form-group">
                                <label for="rideDestination">Ride destination</label>
                                <input class="form-control" type="text" name="name" id="rideDestination"
                                       placeholder="Enter destination"
                                       value="<c:out value="${ride.name}" />" autofocus/>
                            </div>
                            <div class="form-group">
                                <label for="rideMass">Order mass</label>
                                <input class="form-control" type="number" min="1" max="100" step="0.1" name="mass"
                                       id="rideMass" placeholder="Enter mass"
                                       value="<c:out value="${ride.mass}" />"/>
                            </div>
                            <div class="form-group">
                                <label for="rideVolume">Order volume</label>
                                <input class="form-control" type="number" min="1" max="100" step="0.1" name="volume"
                                       id="rideVolume" placeholder="Enter volume"
                                       value="<c:out value="${ride.volume}" />"/>
                            </div>
                            <div class="form-group">
                                <label for="rideStatus">Order volume</label>
                                <input class="form-control" type="text" name="status" id="rideStatus"
                                       readonly="readonly"
                                        <c:choose>
                                            <c:when test="${ride.status==null}">
                                                value="UNASSIGNED"
                                            </c:when>
                                            <c:otherwise>
                                                value="<c:out value="${ride.status}"/>"
                                            </c:otherwise>
                                        </c:choose>/>
                            </div>
                        </fieldset>
                        <button class="btn btn-lg btn-primary" type="submit" value="Submit">Confirm</button>
                    </form>
                </div>
            </div>
</body>
</html>
