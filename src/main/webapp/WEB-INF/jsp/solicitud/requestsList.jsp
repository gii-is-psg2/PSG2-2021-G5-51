<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="requests">
    <h2><spring:message code="requests"/></h2>

    <table id="requestsTable" class="table table-striped table-header">
        <thead>
        <tr>
            <th style="width: 150px;"><spring:message code="form.fullname"/></th>
            <th style="width: 150px;"><spring:message code="form.address"/></th>
            <th style="width: 150px;"><spring:message code="form.telephone"/></th>
            <th style="width: 150px;"><spring:message code="form.date"/></th>
            <th style="width: 150px;"><spring:message code="info"/></th>
            <th style="width: 150px;"><spring:message code="options"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requests}" var="request">
            <tr>
                <td>
                    <c:out value="${request.newOwner.firstName} "/><c:out value="${request.newOwner.lastName}"/>
                </td>
                <td>
                    <c:out value="${request.newOwner.address}"/>
                </td>
                <td>
                    <c:out value="${request.newOwner.telephone}"/>
                </td>
                <td>
                    <c:out value="${request.requestDate}"/>
                </td>
                <td>
                    <c:out value="${request.info}"/>
                </td>
                <td>
                    <a href='<spring:url value="/adoption/${request.adoption.id}/requests/${request.id}/accept" htmlEscape="true"/>'><spring:message code="accept"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
