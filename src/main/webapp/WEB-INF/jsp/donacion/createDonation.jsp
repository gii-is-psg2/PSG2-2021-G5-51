<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Donacion">
        <h2>
            <spring:message code="donation.new"/>
        </h2>
      
        <form:form modelAttribute="donacion" class="form-horizontal" id="add-donacion-form">
            <div class="form-group has-feedback">
              
     			<petclinic:inputNumberField translate="yes" label="donation.amount" name="money"/>
     			<input name="causa" type="hidden" value="${causa.id}"/>
     			<input name="usuario" type="hidden" value="${usuario.username}"/>
     			
            	
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<button class="btn btn-default" type="submit">Añadir donación</button>
                </div>
            </div>
        </form:form>
        
        
</petclinic:layout>
