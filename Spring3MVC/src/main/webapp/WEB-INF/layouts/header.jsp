<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>

<%@ page import="java.util.Calendar"%>

<div id="header-wrap">
	<header>
		<hgroup>
			<h1>
				<a href="<c:url value='/welcome.do' />">Sport Plans Generator</a>
			</h1>
			<h3>Dise&ntilde;a tus planes de entrenamiento a medida.</h3>
		</hgroup>

		<!-- 		<nav>
			<ul>
				<li><a href="#main">Home</a></li>
				<li><a href="#services">Services</a></li>
				<li><a href="#portfolio">Our Works</a></li>
				<li><a href="#about-us">About Us</a></li>
				<li><a href="#styles">Styles</a></li>
				<li><a href="#contact">Contact Us</a></li>
			</ul>
		</nav> -->

		<div id="subheaderside">

			<c:choose>
				<c:when
					test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication!=null}">
					<div id="logininfo">
						<spring:message code="etiqueta.titulo.bienvenido" />
						${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}
					</div>

					<div id="dateinfo">
						<%
							/* String[] meses = { "January", "February", "March", "April",
															"May", "June", "July", "August", "September",
															"October", "November", "December" }; */
									String[] meses = {"Enero", "Febrero", "Marzo", "Abril",
											"Mayo", "Junio", "Julio", "Agosto", "Septiembre",
											"Octubre", "Noviembre", "Diciembre"};

									Calendar calendario = Calendar.getInstance();
									out.print(calendario.get(Calendar.DAY_OF_MONTH) + " de "
											+ meses[calendario.get(Calendar.MONTH)] + " de "
											+ calendario.get(Calendar.YEAR));
						%>
						<a id="closeSession"
							href="<c:url value='/j_spring_security_logout' />">Close
							session</a>
					</div>
					<!-- <script type="text/javascript">
						$(document).ready(
								function() {
									jQuery.getJSON("getNotifications.do",
											function(json) {
												muestraNotificaciones(json);
											});
								});
						function muestraNotificaciones(json) {
							var lstNotifications = json.listaNotifications;
							if (lstNotifications.length > 0) {
								for (i = 0; i < lstNotifications.length; i++) {
									if (lstNotifications[i].showed == false) {
										$('#Notification')
												.jnotifyAddMessage(
														{
															text : lstNotifications[i].notification,
															permanent : false
														});
										lstNotifications[i].showed = true;
									}
								}
								var lst = lstNotifications.serializeObject();
								$.postJSON("lstNotifications",
										lstNotifications, function(data) {
										});
							}
						}
					</script> -->
				</c:when>
			</c:choose>
		</div>
	</header>
</div>