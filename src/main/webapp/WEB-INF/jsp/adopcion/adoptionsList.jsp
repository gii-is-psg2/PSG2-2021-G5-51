<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="adoptions">
    <h2><spring:message code="menu.adoption"/></h2>

    <table id="adoptionsTable" class="table table-striped table-header">
        <thead>
        <tr>
            <th style="width: 150px;"><spring:message code="name"/></th>
            <th style="width: 150px;"><spring:message code="options"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${adoptions}" var="adopcion">
            <tr>
                <td>
                    <c:out value="${adopcion.pet.name}"/>
                </td>
                <td>
                    <a href='<spring:url value="/adoption/${adopcion.id}/requests/new" htmlEscape="true"/>'><spring:message code="request"/></a>
                    <br><a href='<spring:url value="/adoption/${adopcion.id}/requests" htmlEscape="true"/>'><spring:message code="request.view"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
