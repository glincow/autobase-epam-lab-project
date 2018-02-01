<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="header.jsp"/>
    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-8">
                <h1 class="page-header">
                    Oops!
                </h1>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        Error 403: forbidden
                    </div>
                    <div class="panel-body">
                        <p>You don't have permission to access this page</p>
                    </div>

                </div>
                <form method="post" action="/errRedirect">
                    <input name="login" value="<c:out value="${user.role}"/>" hidden>
                    <button class="btn btn-primary" type="submit" value="SignIn">Back</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
