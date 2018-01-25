<%--
  Created by IntelliJ IDEA.
  User: Lind
  Date: 1/25/2018
  Time: 2:04 PM
  To change this template use File | Settings | File Templates.
--%>

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
    <title>Transport status</title>
</head>
<body>
<form method="POST" action='DriverController' name="frmEditStatus">
    Transport ID : <input
        type="text" readonly="readonly" name="id"
        value="<c:out value="${transport.id}" />" /> <br />
    Max mass : <input
        type="text" readonly="readonly" name="maxMass"
        value="<c:out value="${transport.maxMass}" />" /> <br />
    Max volume : <input
        type="text" readonly="readonly" name="maxVolume"
        value="<c:out value="${transport.maxVolume}" />" /> <br />
    <%--Works : <input
        type="text" name="isWorks"
        value="<c:out value="${transport.isAutoWorks}" />" /> <br />--%>
    Works : <select name="isAutoWorks">
                <option selected value="true">True</option>
                <option value="false">False</option>
            </select> <br />
    Available : <select name="isAutoAvailable">
                    <option value="true">True</option>
                    <option value="false">False</option>
                </select> <br />
    <input
            type="submit" value="Submit" />
</form>
</body>
</html>
