<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="acceptRequest">
        <h2>
            <spring:message code="request.accept.confirmation"/>
        </h2>

        <div>
            <table id="requestsTable" class="table table-striped table-header">
                <thead>
                <tr>
                    <th style="width: 150px;"><spring:message code="form.fullname"/></th>
                    <th style="width: 150px;"><spring:message code="form.address"/></th>
                    <th style="width: 150px;"><spring:message code="form.telephone"/></th>
                    <th style="width: 150px;"><spring:message code="form.date"/></th>
                    <th style="width: 150px;"><spring:message code="info"/></th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <c:out value="${request.newOwner.firstName} "/><c:out value="${request.newOwner.lastName}"/>
                        </td>
                        <td>
                            <c:out value="${request.newOwner.address}"/>
                        </td>
                        <td>
                            <c:out value="${request.newOwner.telephone}"/>
                        </td>
                        <td>
                            <c:out value="${request.requestDate}"/>
                        </td>
                        <td>
                            <c:out value="${request.info}"/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <form:form modelAttribute="solicitud" class="form-horizontal" id="add-adoption-form">
            <div class="form-group">
               <div class="col-sm-offset-2 col-sm-3"></div>
                   <button class="btn  btn-success" type="submit"><spring:message code="confirm"/></button>
               </div>
           </div>
       </form:form>
</petclinic:layout>