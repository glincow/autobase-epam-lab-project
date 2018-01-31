<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Users</title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Registered users</h1>
            </div>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-lg-6">
                    <table width="100%" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>User name</th>
                            <th>Login</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td><c:out value="${user.name}"/></td>
                                <td><c:out value="${user.login}"/></td>
                                <td><c:out value="${user.role}"/></td>
                                <td><button onclick="location.href='AdminController?action=edit&id=<c:out
                                        value="${user.id}"/>'" type="button" class="btn btn-primary">Edit user
                                </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
<p><a href="AdminController?action=insertManager">Add manager</a></p>
<p><a href="AdminController?action=insertDriver">Add driver</a></p>
            <p>
                <!-- <a href="AdminController?action=insert">Add User</a> -->
                <button onclick="location.href='AdminController?action=insert'" type="button" class="btn btn-primary">Add
                    user
                </button>
            </p>
        </div>


    </div>
</div>
</body>
</html>