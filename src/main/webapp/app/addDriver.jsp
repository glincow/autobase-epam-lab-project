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
    <title>Add new driver</title>
</head>
<body>
<form method="POST" action='AdminController' name="frmAddUser">
    User Name : <input
        type="text" name="name"
        value="<c:out value="${jspUser.name}" />" /> <br />
    User login : <input
        type="text" name="login"
        value="<c:out value="${jspUser.login}" />" /> <br />
    User password : <input
        type="text" name="password"
        value="<c:out value="${jspUser.password}" />" /> <br />
    <input type="hidden" name="role" value="DRIVER" />
    <input type="hidden" name="id" value="<c:out value="${jspUser.id}" />" />

    <br/>
    <br/>
    Max mass : <input
        type="text" name="maxMass"
        value="<c:out value="${transport.maxMass}" />"/> <br/>
    Max volume : <input
        type="text" name="maxVolume"
        value="<c:out value="${transport.maxVolume}" />"/> <br/>
    Works : <select name="isAutoWorks">
                <option value="true">True</option>
                <option value="false">False</option>
            </select> <br/>
    Available : <select name="isAutoAvailable">
                    <option value="true">True</option>
                    <option selected value="false">False</option>
                </select> <br/>
    <input type="hidden" name="transportId" value="<c:out value="${transport.id}" />" />

    <input
            type="submit" value="Submit" />
</form>
</body>
</html>
