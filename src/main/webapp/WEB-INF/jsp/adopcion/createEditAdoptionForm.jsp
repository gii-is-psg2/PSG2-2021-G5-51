<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Crear Editar Adopcion">
        <h2>
             <c:if test="${adopcion['new']}"><spring:message code="new-f"/> </c:if> <spring:message code="adoption"/>
        </h2>
     
        <form:form modelAttribute="adopcion" class="form-horizontal" id="add-adoption-form">
            <div class="form-group has-feedback">
                <petclinic:textAreaField translate="yes" label="description" name="description"/>
                <form:hidden path="pet" name="pet"/>
            	
            </div>
             <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${adopcion['new']}">
                            <button class="btn btn-default" type="submit"><spring:message code="adoption.add"/></button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit"><spring:message code="adoption.update"/></button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
</petclinic:layout>