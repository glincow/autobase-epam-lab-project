<%--
  Created by IntelliJ IDEA.
  User: Lind
  Date: 1/31/2018
  Time: 9:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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

    <input
            type="submit" value="Submit" />
</form>
</body>
</html>
