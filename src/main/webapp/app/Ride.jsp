<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Ride</title>
</head>
<body>
<form method="POST" action='CustomerController' name="Ride">
    Ride ID : <input type="text" readonly="readonly" name="id"
                     value="<c:out value="${ride.id}" />"/> <br/>
    Ride Name : <input
        type="text" name="name"
        value="<c:out value="${ride.name}" />"/> <br/>
    Ride mass : <input
        type="text" name="mass"
        value="<c:out value="${ride.mass}" />"/> <br/>
    Ride volume : <input
        type="text" name="volume"
        value="<c:out value="${ride.volume}" />"/> <br/>
    Ride status : <input
        type="text" readonly="readonly" name="status"
        <c:choose>
            <c:when test="${ride.status==null}">
                value="UNASSIGNED"
            </c:when>
            <c:otherwise>
                value="<c:out value="${ride.status}"/>"
            </c:otherwise>
        </c:choose>/>


    <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
