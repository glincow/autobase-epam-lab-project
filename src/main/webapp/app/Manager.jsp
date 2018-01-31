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
                <h1 class="page-header">Your ride orders</h1>
            </div>
        </div>

        <div class="panel-body">
            <div class="row">
                <div class="col-lg-8">

                    <table border=1 width="100%" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Ride Id</th>
                            <th>Ride destination</th>
                            <th>Mass</th>
                            <th>Volume</th>
                            <th>Order status</th>
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
                                <td><c:if test="${ride.status=='UNASSIGNED'}">
                                    <button onclick="location.href='ManagerController?action=transportList&id=<c:out
                                            value="${ride.id}"/>'" type="button" class="btn btn-primary">Choose vehicle
                                        for the order
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