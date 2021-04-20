<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causas">

    <h2>Cause Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><c:out value="${causa.name}"/></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><b><c:out value="${causa.description}"/></b></td>
        </tr>
        <tr>
            <th>BudgetTarget</th>
            <td><c:out value="${causa.budgetTarget}"/></td>
        </tr>
        <tr>
            <th>Organization</th>
            <td><c:out value="${causa.organization}"/></td>
        </tr>
        
    </table>

   

    <br/>
    <br/>
    <br/>
    <h2>Donations</h2>

    <table class="table table-striped">
        <c:forEach var="d" items="${causa.donaciones}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Name</dt>
                        <dd><c:out value="${d.usuario.username}"/></dd>
                        <dt>Money</dt>
                        <dd><c:out value="${d.money}"/></dd>
                        
                    </dl>
                </td>
                
            </tr>

        </c:forEach>
        
    </table>
    
    		<spring:url value="/donaciones/{causaId}/donate" var="donacionUrl">

    			<spring:param name="causaId" value="${causa.id}"/>

    	 	</spring:url>

			<td>

				<a href="${fn:escapeXml(donacionUrl)}" class="btn btn-success">

				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>

				add donation</a>

			</td>
	
</petclinic:layout>
