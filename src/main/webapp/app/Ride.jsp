<%@include file="../WEB-INF/i18n.jspf" %>
<html>
<head>
    <title><fmt:message key="ride.text.title"/></title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../header.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header"><fmt:message key="ride.text.header"/></h1>
            </div>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-lg-4">
                    <form method="POST" action='CustomerController' name="Ride" role="form">
                        <fieldset>
                            <div class="form-group" hidden>
                                <label for="rideId">Ride ID</label>
                                <input class="form-control" type="text" name="id" readonly="readonly" id="rideId"
                                       value="<c:out value="${ride.id}" />"/>
                            </div>
                            <div class="form-group">
                                <label for="rideDestination"><fmt:message key="ride.destination"/></label>
                                <input class="form-control" type="text" name="name" id="rideDestination"
                                       placeholder="<fmt:message key="ride.placeholder.destination"/>"
                                       value="<c:out value="${ride.name}" />" autofocus/>
                            </div>
                            <div class="form-group">
                                <label for="rideMass"><fmt:message key="ride.mass"/></label>
                                <input class="form-control" type="number" min="1" max="100" step="0.1" name="mass"
                                       id="rideMass" placeholder="<fmt:message key="ride.placeholder.mass"/>"
                                       value="<c:out value="${ride.mass}" />"/>
                            </div>
                            <div class="form-group">
                                <label for="rideVolume"><fmt:message key="ride.volume"/></label>
                                <input class="form-control" type="number" min="1" max="100" step="0.1" name="volume"
                                       id="rideVolume" placeholder="<fmt:message key="ride.placeholder.volume"/>"
                                       value="<c:out value="${ride.volume}" />"/>
                            </div>
                            <div class="form-group" hidden>
                                <label for="rideStatus">Ride status</label>
                                <input class="form-control" type="text" name="status" id="rideStatus"
                                       readonly="readonly"
                                        <c:choose>
                                            <c:when test="${ride.status==null}">
                                                value="UNASSIGNED"
                                            </c:when>
                                            <c:otherwise>
                                                value="<c:out value="${ride.status}"/>"
                                            </c:otherwise>
                                        </c:choose>/>
                            </div>
                        </fieldset>
                        <button class="btn btn-lg btn-primary" type="submit" value="Submit"><fmt:message key="ride.button.confirm"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
