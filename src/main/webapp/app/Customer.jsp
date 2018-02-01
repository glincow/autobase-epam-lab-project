<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Your rides</title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Your ride orders</h1>
            </div>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-lg-8">
                    <table width="100%" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Ride destination</th>
                            <th scope="col">Mass</th>
                            <th scope="col">Volume</th>
                            <th scope="col">Order status</th>
                            <th scope="col" colspan=2>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${rides}" var="ride">
                            <tr>
                                <td><c:out value="${ride.name}"/></td>
                                <td><c:out value="${ride.mass}"/></td>
                                <td><c:out value="${ride.volume}"/></td>
                                <td><c:out value="${ride.status}"/></td>

                                    <td>
                                        <c:if test="${ride.status=='UNASSIGNED'}">
                                        <button onclick="location.href='CustomerController?action=edit&id=<c:out
                                                value="${ride.id}"/>'" type="button" class="btn btn-primary">Edit order
                                        </button>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${ride.status=='UNASSIGNED'}">
                                        <button onclick="location.href='CustomerController?action=cancel&id=<c:out
                                                value="${ride.id}"/>'" type="button" class="btn btn-danger">Cancel order
                                        </button>
                                        </c:if>
                                    </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <p>
                        <button onclick="location.href='CustomerController?action=insert'" type="button"
                                class="btn btn-primary"> Create new order
                        </button>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!-- Page Content -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="../vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="../vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>

</body>
</html>
