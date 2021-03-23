<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">

	
    <jsp:body>
        <h2>
            <c:if test="${vet['new']}">New </c:if> Vet
        </h2>
        
        <form:form modelAttribute="vet"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${vet.id}"/>
            <input type="hidden" id="spv" value="${specialtiesOfVet}"/>
            <div class="form-group has-feedback">
                <petclinic:inputField label="First Name" name="firstName"/>
                <petclinic:inputField label="Last Name" name="lastName"/>
                <label>Add Specialties:</label>
                 <c:choose>
                        <c:when test="${vet['new']}">
                            <div class="control-group">
            	
                
                 <c:forEach items="${specialties}" var="sp">
                  <input type="checkbox" name="specialties" value="${sp.id}">  ${sp.name}<br>
                   </c:forEach>
                
            </div>
                        </c:when>
                        <c:otherwise>
                           <div class="control-group">
            	
                
                 <c:forEach items="${specialtiesRemaining}" var="sp">
                  <input type="checkbox" name="specialties" value="${sp.id}">  ${sp.name}<br>
                   </c:forEach>
                
            </div>
                        </c:otherwise>
                    </c:choose>
                
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${vet['new']}">
                            <button class="btn btn-default" type="submit">Add Vet</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Vet</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            
      
        </form:form>
        
    </jsp:body>
</petclinic:layout>
