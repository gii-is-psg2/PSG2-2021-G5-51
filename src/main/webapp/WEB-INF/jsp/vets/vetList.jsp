<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="vets">
	<h2><spring:message code="menu.vets"/></h2>

	<table id="vetsTable" class="table table-striped">
		<thead>
			<tr>
				<th><spring:message code="name"/></th>
				<th><spring:message code="specialties"/></th>
				<c:if test="${isAdmin eq true}">
				<th><spring:message code="delete"/></th>
            	<th><spring:message code="edit"/></th>
            	</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${vets.vetList}" var="vet">
				<tr>
					<td><c:out value="${vet.firstName} ${vet.lastName}" /></td>
					<td><c:forEach var="specialty" items="${vet.specialties}">
							<c:out value="${specialty.name} " />
						</c:forEach> <c:if test="${vet.nrOfSpecialties == 0}"><spring:message code="none"/></c:if></td>
					<c:if test="${isAdmin eq true}">
                	<td>
	                    <a href="vets/${vet.id}/delete"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                	</td>
                	
                			
					<td><a href="/vets/${vet.id}/edit"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                		</a>
                	</td>
                	</c:if>	
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${isAdmin eq true}">
	<table class="table-buttons">
		<tr>
			<!-- <td><a href="<spring:url value="/vets.xml" htmlEscape="true" />">View
					as XML</a></td> -->
			<td><a href="<spring:url value="/vets/new" htmlEscape="true" />" class="btn  btn-success"><span
					class="glyphicon glyphicon-plus" aria-hidden="true"></span><spring:message code="vet.add"/></a></td>
		</tr>
	</table>
	</c:if>	

</petclinic:layout>
