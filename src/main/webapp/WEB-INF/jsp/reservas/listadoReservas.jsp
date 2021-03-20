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
            <th>Delete resereve</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reservas}" var="reserva">
            <tr>
           		<td><c:out value="${reserva.pet.name}"/></td>
           		
           		<td><c:out value="${reserva.startDate}"/></td>
           		
           		<td><c:out value="${reserva.finishDate}"/></td>
           			  
                <td>
                	<spring:url value="/reservas/delete/{reservaId}" var="reservaUrl">
                        <spring:param name="reservaId" value="${reserva.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(reservaUrl)}"><span class="glyphicon glyphicon-trash"></span></a>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>

 
</petclinic:layout>