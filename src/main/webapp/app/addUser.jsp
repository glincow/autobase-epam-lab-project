<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Add new user</title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-8">
                <h1 class="page-header">
                    <c:choose>
                        <c:when test="${jspUser.role == null}">
                            Add new manager
                        </c:when>
                        <c:otherwise>
                            Edit user information
                        </c:otherwise>
                    </c:choose>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        User info
                    </div>
                    <div class="panel-body">

                        <form method="POST" action='AdminController' name="frmAddManager" role="form">
                            <fieldset>
                                <div class="form-group">
                                    <label for="userName">User name</label>
                                    <input class="form-control" type="text" name="name" id="userName"
                                           value="<c:out value="${jspUser.name}" />"/>
                                </div>
                                <div class="form-group">
                                    <label for="userLogin">User login</label>
                                    <input class="form-control" type="text" name="login" id="userLogin"
                                           value="<c:out value="${jspUser.login}" />"/>
                                </div>
                                <div class="form-group">
                                    <label for="userPassword">User password</label>
                                    <input class="form-control" type="text" name="password" id="userPassword"
                                           value="<c:out value="${jspUser.password}" />"/>
                                </div>


                                <c:choose> <%--for create only manager and edit everybody except driver--%>
                                <c:when test="${jspUser.role == null}">
                                <input type="hidden" name="role" value="MANAGER"/>
                                </c:when>
                                <c:otherwise>
                                <input type="hidden" name="role" value="<c:out value="${jspUser.role}" />"/>
                                </c:otherwise>
                                </c:choose>
                                <input type="hidden" name="id" value="<c:out value="${jspUser.id}" />"/>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <button onclick="location.href='/AdminController?action=listUsers'"
                                                type="button"
                                                class="btn btn-primary"> Back
                                        </button>
                                    </div>
                                    <div class="col-lg-6">
                                        <button class="btn btn-primary btn-success pull-right" type="submit" value="Submit">
                                            Confirm
                                        </button>
                                    </div>
                                </div>

                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

