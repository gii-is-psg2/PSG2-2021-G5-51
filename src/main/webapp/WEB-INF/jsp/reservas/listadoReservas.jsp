<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="reservas">
    <h2>Reserves for hotel</h2>

    <table id="reservasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Pet</th>
            <th>Start date</th>
            <th>Finish date</th>
            <th>Delete</th>
            <th>Edit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reservas}" var="reserva">
                <tr>
                    <td><c:out value="${reserva.pet.name}"/></td>
                    
                    <td><c:out value="${reserva.startDate}"/></td>
                    
                    <td><c:out value="${reserva.finishDate}"/></td>
                        
                    <td>
                        <spring:url value="/owners/{ownerId}/pets/{petId}/reservas/{reservaId}/delete" var="reservaUrl">
                            <spring:param name="ownerId" value="${ownerId}"/>
                            <spring:param name="petId" value="${petId}"/>
                            <spring:param name="reservaId" value="${reserva.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(reservaUrl)}"><span class="glyphicon glyphicon-trash"></span></a>
                    </td>
                    <td>
                        <spring:url value="/owners/{ownerId}/pets/{petId}/reservas/{reservaId}/edit" var="reservaUrl">
                            <spring:param name="ownerId" value="${ownerId}"/>
                            <spring:param name="petId" value="${petId}"/>
                            <spring:param name="reservaId" value="${reserva.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(reservaUrl)}"><span class="glyphicon glyphicon-pencil"></span></a>
                    </td>
                    
                </tr>
        </c:forEach>
        </tbody>
    </table>
    <spring:url value="/owners/{ownerId}/pets/{petId}/reservas/new" var="reservaUrl">
        <spring:param name="ownerId" value="${ownerId}"/>
        <spring:param name="petId" value="${petId}"/>
    </spring:url>
    <a href="${fn:escapeXml(reservaUrl)}" class="btn btn-default">New reserve</a>
 
</petclinic:layout>