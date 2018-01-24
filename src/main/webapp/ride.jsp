<%--
  Created by IntelliJ IDEA.
  User: Lind
  Date: 1/23/2018
  Time: 9:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Add new ride</title>
</head>
<body>
<form method="POST" action='RideController' name="frmAddRide">
    Ride ID : <input type="text" readonly="readonly" name="id"
                     value="<c:out value="${ride.id}" />" /> <br />
    Ride Name : <input
        type="text" name="name"
        value="<c:out value="${ride.name}" />" /> <br />
    Ride mass : <input
        type="text" name="mass"
        value="<c:out value="${ride.mass}" />" /> <br />
    Ride volume : <input
        type="text" name="volume"
        value="<c:out value="${ride.volume}" />" /> <br />
    Ride status : <input
        type="text" name="status"
        value="<c:out value="${ride.status}" />" /> <br />
            <input
        type="submit" value="Submit" />
</form>
</body>
</html>
