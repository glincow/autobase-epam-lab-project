<%@include file="../WEB-INF/i18n.jspf" %>
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
        <form id="addDriver" method="POST" action='AdminController' name="frmAddUser" role="form">
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
                                           value="<c:out value="${jspUser.name}" />" required
                                           placeholder="<fmt:message key="signUp.label.namepl"/>"
                                           data-parsley-required data-parsley-length="[3, 20]"/>
                                </div>
                                <div class="form-group">
                                    <label for="userLogin"><fmt:message key="user.login"/></label>
                                    <input class="form-control" type="text" name="login" id="userLogin"
                                           value="<c:out value="${jspUser.login}" />" required
                                           placeholder="<fmt:message key="signIn.label.loginpl"/>"
                                           data-parsley-required data-parsley-pattern="/^[a-z0-9_-]{3,16}$/"/>
                                </div>
                                <div class="form-group">
                                    <label for="userPassword"><fmt:message key="user.password"/></label>
                                    <input class="form-control" type="password" name="password" id="userPassword"
                                           value="<c:out value="${jspUser.password}" />" required
                                           placeholder="<fmt:message key="signIn.label.passwordpl"/>"
                                           data-parsley-required data-parsley-pattern="/^[a-z0-9_-]{3,18}$/"/>
                                </div>
                                <div class="form-group">
                                    <label for="inputPasswordAgain"><fmt:message
                                            key="signUp.label.passwordAgain"/></label>
                                    <input class="form-control" type="password" name="password" id="inputPasswordAgain"
                                           placeholder="<fmt:message key="signUp.label.passwordAgainpl"/>"
                                           value="" data-parsley-required data-parsley-length="[3, 10]"
                                           data-parsley-equalto="#userPassword">
                                    <input type="hidden" name="role" value="DRIVER"/>
                                    <input type="hidden" name="id" value="<c:out value="${jspUser.id}" />"/>
                                </div>
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
                                        <input class="form-control" type="number" step="0.1"
                                               name="maxMass" id="maxMass"
                                               value="<c:out value="${transport.maxMass}" />" required
                                               placeholder="<fmt:message key="vehicle.masspl"/>"
                                               data-parsley-required data-parsley-type="number"
                                               data-parsley-range="[1, 10]"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="maxVolume"><fmt:message key="vehicle.volume"/></label>
                                        <input class="form-control" type="number" step="0.1"
                                               name="maxVolume" id="maxVolume"
                                               value="<c:out value="${transport.maxVolume}" />" required
                                               placeholder="<fmt:message key="vehicle.volumepl"/>"
                                               data-parsley-required data-parsley-type="number"
                                               data-parsley-range="[1, 100]"/>
                                    </div>

                                    <div class="form-group">
                                        <label><fmt:message key="vehicle.works"/></label>
                                        <c:choose>
                                            <c:when test="${transport.isAutoWorks=='true'}">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="isAutoWorks" value="true"
                                                               checked><fmt:message key="text.true"/>
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="isAutoWorks"
                                                               value="false"><fmt:message key="text.false"/>
                                                    </label>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="isAutoWorks" value="true"><fmt:message
                                                            key="text.true"/>
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="isAutoWorks" value="false"
                                                               checked><fmt:message key="text.false"/>
                                                    </label>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                        <label><fmt:message key="vehicle.available"/></label>
                                        <c:choose> <%--for dynamic selected variant--%>
                                            <c:when test="${transport.isAutoAvailable=='true'}">
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="isAutoAvailable" value="true"
                                                               checked><fmt:message key="text.true"/>
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="isAutoAvailable"
                                                               value="false"><fmt:message key="text.false"/>
                                                    </label>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="isAutoAvailable"
                                                               value="true"><fmt:message key="text.true"/>
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <label>
                                                        <input type="radio" name="isAutoAvailable" value="false"
                                                               checked><fmt:message key="text.false"/>
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
                <button class="btn btn-primary btn-success pull-right" type="submit" value="Submit"><fmt:message
                        key="adduser.button.confirm"/></button>
                <button onclick="location.href='/AdminController?action=listUsers'" type="button"
                        class="btn btn-primary pull-left"><fmt:message key="adduser.button.back"/>
                </button>
            </div>
        </form>
    </div>
</div>

<!-- Form validation -->
<script type="text/javascript">
    $('#addDriver').parsley();
</script>


</body>


</html>
