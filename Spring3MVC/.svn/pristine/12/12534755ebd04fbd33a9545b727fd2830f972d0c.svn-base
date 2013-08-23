<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
    $("#formSportCentre").validate();
  });
</script>
<section id="fsportCentre">
	<form:form action="addSportCentre.do" id="formSportCentre"
		commandName="sportCentre" class="redondeado shadow fondoForm">

		<p class="titleForm">
			<spring:message code="etiqueta.titulo.nuevoSportCentre" />
		</p>
		<c:if test="${not empty error}">
		<div class="errorblock redondeado shadow">
			<spring:hasBindErrors name="sportCentre">
				<p>
					<form:errors path="username" />
				</p>
				<p>
					<form:errors path="password" />
				</p>
				<p>
					<form:errors path="repetirPassword" />
				</p>
				<p>
					<form:errors path="name" />
				</p>
				<p>
					<form:errors path="email" />
				</p>
			</spring:hasBindErrors>
		</div>
		</c:if>
		<div>
			<label for="username" class="labelForm"><spring:message
					code="etiqueta.modelo.user.username" /></label>
			<form:input path="username" class="required" />
		</div>

		<div>
			<label for="password" class="labelForm"><spring:message
					code="etiqueta.modelo.user.password" /></label>
			<form:password path="password" class="required" minlength="6"/>
		</div>
		<div>
			<label for="repetirPassword" class="labelForm"><spring:message
					code="etiqueta.modelo.user.repetirPassword" /></label>
			<form:password path="repetirPassword" class="required" minlength="6"/>
		</div>
		<div>
			<label for="name" class="labelForm"><spring:message
					code="etiqueta.modelo.sportCentre.name" /></label>
			<form:input path="name" id="inputNameFormSportCentre" class="required"/>
		</div>	
		<div>
			<label for="email" class="labelForm"><spring:message
					code="etiqueta.modelo.sportCentre.email" /></label>
			<form:input path="email" id="inputEmailFormSportCentre" class="required email"/>
		</div>

		<input id="submit" name="submit" type="submit"
			value="<spring:message code="etiqueta.link.aniadirSporCentre" />"
			class="estiloBotones" />
	</form:form>
</section>