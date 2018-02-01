<%@include file="../WEB-INF/i18n.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title><fmt:message key="adddriver.text.title"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                <c:choose>
                    <c:when test="${jspUser.role == null}">
                        <fmt:message key="adddriver.text.addheader"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="adddriver.text.editheader"/>
                    </c:otherwise>
                </c:choose>
            </h1>
        </div>
    </div>

            <div class="col-lg-8">
                <form method="POST" action='AdminController' name="frmAddUser" role="form">
                    <div class="row">
                        <fieldset>
                            <div class="col-lg-6">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <fmt:message key="admin.text.header"/>
                                    </div>
                                    <div class="panel-body">

                                        <div class="form-group">
                                            <label for="userName"><fmt:message key="user.name"/></label>
                                            <input class="form-control" type="text" name="name" id="userName"
                                                   value="<c:out value="${jspUser.name}" />"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="userLogin"><fmt:message key="user.login"/></label>
                                            <input class="form-control" type="text" name="login" id="userLogin"
                                                   value="<c:out value="${jspUser.login}" />"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="userPassword"><fmt:message key="user.password"/></label>
                                            <input class="form-control" type="text" name="password" id="userPassword"
                                                   value="<c:out value="${jspUser.password}" />"/>
                                        </div>
                                        <input type="hidden" name="role" value="DRIVER"/>
                                        <input type="hidden" name="id" value="<c:out value="${jspUser.id}" />"/>
                                    </div>
                                </div>
                            </div>


                            <div class="col-lg-6">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <fmt:message key="trstatus.text.vehicleheader"/>
                                    </div>
                                    <div class="panel-body">


                                        <div class="form-group">
                                            <label for="maxMass"><fmt:message key="vehicle.mass"/></label>
                                            <input class="form-control" type="number" min="1" max="100" step="0.1"
                                                   name="maxMass" id="maxMass"
                                                   value="<c:out value="${transport.maxMass}" />"/>
                                        </div>

                                        <div class="form-group">
                                            <label for="maxVolume"><fmt:message key="vehicle.volume"/></label>
                                            <input class="form-control" type="number" min="1" max="100" step="0.1"
                                                   name="maxVolume" id="maxVolume"
                                                   value="<c:out value="${transport.maxVolume}" />"/>
                                        </div>

                                        <div class="form-group">
                                            <label><fmt:message key="vehicle.works"/></label>
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
                                                            <input type="radio" name="isAutoWorks" value="true">True
                                                        </label>
                                                    </div>
                                                    <div class="radio">
                                                        <label>
                                                            <input type="radio" name="isAutoWorks" value="false"
                                                                   checked>False
                                                        </label>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                            <!-- </div>
                                            <div class="form-group"> -->
                                            <label><fmt:message key="vehicle.available"/></label>
                                            <c:choose> <%--for dynamic selected variant--%>
                                                <c:when test="${transport.isAutoAvailable=='true'}">
                                                    <div class="radio">
                                                        <label>
                                                            <input type="radio" name="isAutoAvailable" value="true"
                                                                   checked>True
                                                        </label>
                                                    </div>
                                                    <div class="radio">
                                                        <label>
                                                            <input type="radio" name="isAutoAvailable" value="false">False
                                                        </label>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="radio">
                                                        <label>
                                                            <input type="radio" name="isAutoAvailable" value="true">True
                                                        </label>
                                                    </div>
                                                    <div class="radio">
                                                        <label>
                                                            <input type="radio" name="isAutoAvailable" value="false"
                                                                   checked>False
                                                        </label>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </fieldset>

                        <input type="hidden" name="transportId" value="<c:out value="${transport.id}" />"/>

                    </div>
                    <div class="row">
                        <button class="btn btn-primary btn-success pull-right" type="submit" value="Submit"><fmt:message key="adduser.button.confirm"/></button>
                        <button onclick="location.href='/AdminController?action=listUsers'" type="button"
                                class="btn btn-primary pull-left"> <fmt:message key="adduser.button.back"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>


</body>
</html>
