<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#startDate").datepicker({dateFormat: 'yy/mm/dd'});
                $("#finishDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            <c:if test="${reserva['new']}"><spring:message code="new-f"/> </c:if> <spring:message code="reserve"/>
        </h2>
        <b><spring:message code="pet"/></b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th><spring:message code="name"/></th>
                <th><spring:message code="form.birthdate"/></th>
                <th><spring:message code="form.type"/></th>
                <th><spring:message code="owner"/></th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${pet.name}"/></td>
                <td><petclinic:localDate date="${pet.birthDate}" pattern="yyyy/MM/dd"/></td>
                <td><c:out value="${pet.type.name}"/></td>
                <td><c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/></td>
            </tr>
        </table>
        <form:form modelAttribute="reserva" class="form-horizontal" id="add-reserva-form">
            <div class="form-group has-feedback">
                <petclinic:inputField translate="yes" label="startdate" name="startDate"/>
                <petclinic:inputField translate="yes" label="finishdate" name="finishDate"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${reserva['new']}">
                            <button class="btn btn-default" type="submit"><spring:message code="reserve.add"/></button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit"><spring:message code="reserve.update"/></button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
    </jsp:body>
</petclinic:layout>
