<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="acceptRequest">
        <h2>
             Confirmación de aceptación de solicitud
        </h2>

        <div>
            <table id="requestsTable" class="table table-striped table-header">
                <thead>
                <tr>
                    <th style="width: 150px;">Full name</th>
                    <th style="width: 150px;">Address</th>
                    <th style="width: 150px;">Telephone</th>
                    <th style="width: 150px;">Date</th>
                    <th style="width: 150px;">Information</th>
                    <th style="width: 150px;"></th>
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
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Confirmar</button>
                </div>
            </div>
        </form:form>
</petclinic:layout>