<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="about">

<div class="about-section">
	<h1><spring:message code="about" text="default"/></h1>
</div>

<div class="container">
	<div class="row">
      <div class="col-md-12">
        <h2>PetClinic Team</h2>
        <p>Naós somos a equipe petclinic, nosso trabalho é cuidar de seus animais de estimação.</p>
        <p>657894119</p>
        <p>petclinic@yahoo.mx</p>
        <p>Avenida de los Choquitos Fritos</p>
        <img src="https://esports.as.com/2020/02/20/fortnite/Nuevo-mapa-Fortnite_1329777015_347438_1440x810.jpg" style="width:35%">
        <div><button class="button">Contact</button></div>
      </div>
  </div>
</div> 
</petclinic:layout>
