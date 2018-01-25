<%--
  Created by IntelliJ IDEA.
  User: Lind
  Date: 1/25/2018
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Choose transport</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Transport Id</th>
        <th>Max mass</th>
        <th>Max volume</th>
        <th colspan=1>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${transportList}" var="transport">
        <tr>
            <td><c:out value="${transport.id}" /></td>
            <td><c:out value="${transport.maxMass}" /></td>
            <td><c:out value="${transport.maxVolume}" /></td>
            <%--<td><c:if test="${ride.status=='UNASSIGNED'}">
                <a href="ManagerController?action=transportList&id=<c:out value="${ride.id}"/>">Choose transport</a>
            </c:if></td>--%>
            <td><a href="ManagerController?action=chooseTransport&id=<c:out value="${transport.id}"/>&rideId=<c:out value="${ride.id}"/>">Choose transport</a></td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>