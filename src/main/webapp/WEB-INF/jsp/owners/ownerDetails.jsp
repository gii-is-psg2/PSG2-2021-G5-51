<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">

    <h2><spring:message code="owner.information"/></h2>


    <table class="table table-striped">
        <tr>
            <th><spring:message code="form.firstname"/></th>
            <td><b><c:out value="${owner.firstName} ${owner.lastName}"/></b></td>
        </tr>
        <tr>
            <th><spring:message code="form.address"/></th>
            <td><c:out value="${owner.address}"/></td>
        </tr>
        <tr>
            <th><spring:message code="form.city"/></th>
            <td><c:out value="${owner.city}"/></td>
        </tr>
        <tr>
            <th><spring:message code="form.telephone"/></th>
            <td><c:out value="${owner.telephone}"/></td>
        </tr>
    </table>

    <spring:url value="{ownerId}/edit" var="editUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default"><spring:message code="edit"/></a>

    <spring:url value="{ownerId}/pets/new" var="addUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default"><spring:message code="pet.new"/></a>

    <br/>
    <br/>
    <br/>
    <h2><spring:message code="visits-and-pets"/></h2>

    <table class="table table-striped">
        <c:forEach var="pet" items="${owner.pets}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt><spring:message code="name"/></dt>
                        <dd><c:out value="${pet.name}"/></dd>
                        <dt><spring:message code="form.birthdate"/></dt>
                        <dd><petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt><spring:message code="form.type"/></dt>
                        <dd><c:out value="${pet.type.name}"/></dd>
                        <dt>
                            <spring:url value="/owners/{ownerId}/pets/{petId}/reservas" var="reservaUrl">
                                <spring:param name="ownerId" value="${owner.id}"/>
                                <spring:param name="petId" value="${pet.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(reservaUrl)}">Reserves for hotel</a>
                        </dt>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                            <th><spring:message code="visit.date"/></th>
                            <th><spring:message code="visit.description"/></th>
                        </tr>
                        </thead>
                        <c:forEach var="visit" items="${pet.visits}">
                            <tr>
                                <td><petclinic:localDate date="${visit.date}" pattern="yyyy-MM-dd"/></td>
                                <td><c:out value="${visit.description}"/></td>
                                 <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visit/{visitId}/delete" var="visitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="visitId" value="${visit.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(visitUrl)}"><spring:message code="visit.delete"/></a>
                            </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/edit" var="petUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(petUrl)}"><spring:message code="pet.edit"/></a>
                            </td>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visits/new" var="visitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(visitUrl)}"><spring:message code="visit.new"/></a>
                            </td>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/delete" var="petUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(petUrl)}"><spring:message code="pet.delete"/></a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

        </c:forEach>
    </table>

</petclinic:layout>
