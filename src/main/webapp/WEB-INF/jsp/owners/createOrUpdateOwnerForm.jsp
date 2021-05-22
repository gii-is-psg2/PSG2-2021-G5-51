<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
    <h2>
        <c:if test="${owner['new']}"><spring:message code="new.owner"/> </c:if><spring:message code="owner"/>
    </h2>
    <form:form modelAttribute="owner" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField translate="yes" label="form.firstname" name="firstName"/>
            <petclinic:inputField translate="yes" label="form.lastname" name="lastName"/>
            <petclinic:inputField translate="yes" label="form.address" name="address"/>
            <petclinic:inputField translate="yes" label="form.city" name="city"/>
            <petclinic:inputField translate="yes" label="form.telephone" name="telephone"/>
            <petclinic:inputField translate="yes" label="form.email" name="email"/>
            <petclinic:inputField translate="yes" label="form.username" name="user.username"/>
            <petclinic:inputField translate="yes" label="form.password" name="user.password"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${owner['new']}">
                        <button class="btn btn-default" type="submit"><spring:message code="form.addowner"/></button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit"><spring:message code="form.updateowner"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    <div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<c:if test="${!owner['new']}">
				<a class="btn btn-default" href="/owners/${owner.id}/delete"><spring:message code="owner.delete"/></a>
			</c:if>
    	</div>
	</div>
</petclinic:layout>
