
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Your rides</title>
</head>
<body>
<table border=1>
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
            <td><c:out value="${ride.id}" /></td>
            <td><c:out value="${ride.name}" /></td>
            <td><c:out value="${ride.mass}" /></td>
            <td><c:out value="${ride.volume}" /></td>
            <td><c:out value="${ride.status}" /></td>
            <td><c:if test="${ride.status=='UNASSIGNED'}">
                <a href="ManagerController?action=transportList&id=<c:out value="${ride.id}"/>">Choose transport</a>
            </c:if></td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>