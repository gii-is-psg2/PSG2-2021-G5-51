<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causas">
        <h2>
            <spring:message code="causa.new"/>
        </h2>
     
        <form:form modelAttribute="causa" class="form-horizontal" id="add-causa-form">
            <div class="form-group has-feedback">
                <petclinic:inputField translate="yes" label="name" name="name"/>
                <petclinic:textAreaField translate="yes" label="description" name="description"/>
     			<petclinic:inputNumberField translate="yes" label="budget_target" name="budgetTarget"/>
     			<petclinic:inputField translate="yes" label="organization" name="organization"/>
            	
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<button class="btn btn-default" type="submit"><spring:message code="cause.add"/></button>
                </div>
            </div>
        </form:form>
</petclinic:layout>