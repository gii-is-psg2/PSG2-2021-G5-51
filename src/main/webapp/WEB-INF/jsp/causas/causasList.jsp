<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<petclinic:layout pageName="causas">
    <h2><spring:message code="causas.list"/></h2>

    <table id="causasTable" class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="name"/></th>
            <th><spring:message code="description"/></th>
            <th><spring:message code="budget_target"/></th>
            <th><spring:message code="organization"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${causas}" var="causa">
                <tr>
                    <td><c:out value="${causa.name}"/></td>
                    
                    <td><c:out value="${causa.description}"/></td>
                    
                    <td><c:out value="${causa.budgetTarget}"/></td>
                    
                    <td><c:out value="${causa.organization}"/></td>
                    
                </tr>
        </c:forEach>
        </tbody>
    </table>


   <table class="table-buttons">
		<tr>
	
		<spring:url value="/causas/new" var="causaUrl">
    	</spring:url>
    	
			<td>
				<a href="${fn:escapeXml(causaUrl)}"  class="btn  btn-success">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				<spring:message code="cause.add"/></a>
			</td>
		</tr>
	</table>
  
 
</petclinic:layout>