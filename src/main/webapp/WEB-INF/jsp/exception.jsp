<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

	<h2><spring:message code="error"/></h2>

    <spring:url value="/resources/images/exception.png" var="petsImage"/>
    <img src="${petsImage}" width="50%" height="50%"/>


    <p>${exception.message}</p>

</petclinic:layout>
