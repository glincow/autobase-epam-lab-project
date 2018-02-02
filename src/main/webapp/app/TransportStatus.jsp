<%@include file="../WEB-INF/i18n.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title><fmt:message key="trstatus.text.title"/></title>
</head>
<body>

<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header"><fmt:message key="trstatus.text.header"/></h1>
            </div>
        </div>

        <div class="col-lg-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <fmt:message key="trstatus.text.statusheader"/>
                </div>
                <div class="panel-body">
                    <form method="POST" action='DriverController' name="frmEditStatus" role="form">
                        <fieldset>

                            <div class="form-group">
                                <label><fmt:message key="vehicle.works"/></label>
                                <c:choose> <%--for dynamic selected variant--%>
                                    <c:when test="${transport.isAutoWorks=='true'}">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoWorks" value="true" checked><fmt:message key="text.true"/>
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoWorks" value="false"><fmt:message key="text.false"/>
                                            </label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoWorks"  value="true"><fmt:message key="text.true"/>
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoWorks"  value="false" checked><fmt:message key="text.false"/>
                                            </label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="form-group">
                                <label><fmt:message key="vehicle.available"/></label>
                                <c:choose> <%--for dynamic selected variant--%>
                                    <c:when test="${transport.isAutoAvailable=='true'}">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoAvailable"  value="true" checked><fmt:message key="text.true"/>
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoAvailable"  value="false"><fmt:message key="text.false"/>
                                            </label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoAvailable"  value="true"><fmt:message key="text.true"/>
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="isAutoAvailable"  value="false" checked><fmt:message key="text.false"/>
                                            </label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </fieldset>
                        <br>
                        <div class="row">
                            <div class="col-lg-6">
                                <button onclick="location.href='/DriverController?action='"
                                        type="button"
                                        class="btn btn-primary"><fmt:message key="adduser.button.back"/>
                                </button>
                            </div>
                            <div class="col-lg-6">
                                <button class="btn btn-success pull-right" type="submit" value="Submit"><fmt:message key="trstatus.button.confirm"/></button>
                            </div>
                        </div>

                    </form>

                </div>
                <!-- <div class="panel-footer">

                </div> -->
            </div>
        </div>

        <div class="col-lg-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <fmt:message key="trstatus.text.vehicleheader"/>
                </div>
                <div class="panel-body">
                    <table width="100%" class="table">
                        <tbody>
                        <tr>
                            <th><fmt:message key="vehicle.id"/></th>
                            <td><c:out value="${transport.id}"/></td>
                        </tr>
                        <tr>
                            <th><fmt:message key="vehicle.mass"/></th>
                            <td><c:out value="${transport.maxMass}"/></td>
                        </tr>
                        <tr>
                            <th><fmt:message key="vehicle.volume"/></th>
                            <td><c:out value="${transport.maxVolume}"/></td>
                        </tr>
                        <tr>
                            <th><fmt:message key="vehicle.works"/></th>
                            <td><fmt:message key="text.${transport.isAutoWorks}"/></td>
                        </tr>
                        <tr>
                            <th><fmt:message key="vehicle.available"/></th>
                            <td><fmt:message key="text.${transport.isAutoAvailable}"/></td>
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
