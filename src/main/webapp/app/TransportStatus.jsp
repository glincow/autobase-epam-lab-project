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
    <title>Transport status</title>
</head>
<body>

<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Update vehicle availability</h1>
            </div>
        </div>

        <div class="col-lg-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    Edit status
                </div>
                <div class="panel-body">
                    <form method="POST" action='DriverController' name="frmEditStatus" role="form">
                        <fieldset>

                            <div class="form-group">
                                <label>Works</label>
                                <c:choose> <%--for dynamic selected variant--%>
                                    <c:when test="${transport.isAutoWorks=='true'}">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoWorks" value="true" checked>True
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoWorks" value="false">False
                                            </label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoWorks"  value="true">True
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoWorks"  value="false" checked>False
                                            </label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="form-group">
                                <label>Available</label>
                                <c:choose> <%--for dynamic selected variant--%>
                                    <c:when test="${transport.isAutoAvailable=='true'}">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoAvailable"  value="true" checked>True
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoAvailable"  value="false">False
                                            </label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoAvailable"  value="true">True
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoAvailable"  value="false" checked>False
                                            </label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </fieldset>
                        <br>
                        <button class="btn btn-primary" type="submit" value="Submit">Confirm</button>
                    </form>

                </div>
                <!-- <div class="panel-footer">

                </div> -->
            </div>
        </div>

        <div class="col-lg-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    Vehicle Info
                </div>
                <div class="panel-body">
                    <table width="100%" class="table">
                        <tbody>
                        <tr>
                            <th>Vehicle ID</th>
                            <td><c:out value="${transport.id}"/></td>
                        </tr>
                        <tr>
                            <th>Payload (tons)</th>
                            <td><c:out value="${transport.maxMass}"/></td>
                        </tr>
                        <tr>
                            <th>Body volume (cubic meters)</th>
                            <td><c:out value="${transport.maxVolume}"/></td>
                        </tr>
                        <tr>
                            <th>isAutoWorks</th>
                            <td><c:out value="${transport.isAutoWorks}"/></td>
                        </tr>
                        <tr>
                            <th>isAutoAvailable</th>
                            <td><c:out value="${transport.isAutoAvailable}"/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>
