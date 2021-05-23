<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>
	
<style>
	nav div div ul a:hover {
		background-color:#FF7770 !important;
	}	
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            window.location.replace('?lang=' + selectedOption);
			localStorage.setItem("lang", selectedOption);
        }
    });
	if (localStorage.getItem("lang") != null)
		document.getElementById('locales').value=localStorage.getItem("lang");
});
</script>


<nav class="navbar navbar-default" role="navigation">
	<span><spring:message code="lang.change"/></span>:
	<select id="locales">
		<option value="en" selected><spring:message code="lang.en"/></option>
		<option value="es"><spring:message code="lang.es"/></option>
	</select>
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span><spring:message code="menu.home"/></span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'owners'}" url="/owners/find"
					title="find owners">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span><spring:message code="menu.findowners"/></span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
					title="veterinarians">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span><spring:message code="menu.vets"/></span>
				</petclinic:menuItem>
				
				<petclinic:menuItem active="${name eq 'causes'}" url="/causas/list"
					title="causes">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span><spring:message code="menu.causes"/></span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'adoption'}" url="/adoption"
					title="adoption">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span><spring:message code="menu.adoption"/></span>
				</petclinic:menuItem>
				
				<petclinic:menuItem active="${name eq 'about'}" url="/about"
					title="about">
					<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
					<span><spring:message code="menu.about"/></span>
				</petclinic:menuItem>
				
				<petclinic:menuItem active="${name eq 'health'}" url="/manage/health"
					title="health">
					<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
					<span><spring:message code="menu.health"/></span>
				</petclinic:menuItem>

				<!-- <petclinic:menuItem active="${name eq 'error'}" url="/oups"
					title="trigger a RuntimeException to see how it is handled">
					<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
					<span><spring:message code="menu.error"/></span>
				</petclinic:menuItem> --!>

			</ul>




			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />"><spring:message code="menu.login"/></a></li>
					<li><a href="<c:url value="/users/new" />"><spring:message code="menu.register"/></a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm"><spring:message code="logout"/></a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
<!-- 							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="#" class="btn btn-primary btn-block">My Profile</a>
												<a href="#" class="btn btn-danger btn-block">Change
													Password</a>
											</p>
										</div>
									</div>
								</div>
							</li>
-->
						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>
