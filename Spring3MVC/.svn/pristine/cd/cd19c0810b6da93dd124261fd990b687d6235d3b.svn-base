<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script>
$(document).ready(function() {
	$('#slatMensaje').delay(10000).fadeOut(2000);	
});
</script>
<section id="fSportPlansGenerator">	
	<div class="redondeado shadow fondoForm padding2">
		<c:if test="${not empty customer.name}">
			<div class="customerName centre"><h2>Cliente: ${customer.name}</h2></div>	
		</c:if>
		
		<div class="perfilMenu">Consultar perfil <br><input type="button" class="estiloBotones" onclick="location='modifyProfile.do?customerId=<c:out value="${customerId}" />'" value="<spring:message code="etiqueta.link.modify.credentials.SporCentre"/>"/></div>
		<div class="planesGeneradosMenu">Consultar planes generados <br><input type="button" class="estiloBotones" onclick="location='showSportPlans.do?customerId=<c:out value="${customerId}" />'" value="<spring:message code="etiqueta.panel.sport.centre.planes"/>"/></div>
		<div class="GPAMenu"><input type="button" class="estiloBotones" onclick="location='menuGenerarPlanStep1.do?customerId=<c:out value="${customerId}" />'" value="<spring:message code="etiqueta.link.generar.plan.automatico"/>"/><img id='gearIcon' src='<c:url value='images/gear.png'/>' alt='Generar Plan Automaticamente' onclick="location='menuGenerarPlanStep1.do?customerId=<c:out value="${customerId}" />'"/></div>
		<div class="GPMMenu"><input type="button" class="estiloBotones" onclick="location='menuGenerarPlanStep1Manual.do?customerId=<c:out value="${customerId}" />'" value="<spring:message code="etiqueta.link.generar.plan.manual"/>"/><img id='todoIcon' src='<c:url value='images/gtg_icon.png'/>' alt='Generar Plan Manualmente' onclick="location='menuGenerarPlanStep1Manual.do?customerId=<c:out value="${customerId}" />'"/></div>
		<div class="atrasMenu"><input type="button" class="estiloBotones" onclick="location='menuSportTrainer.do'" value="<spring:message code="etiqueta.link.cancelar"/>"/></div>
	</div>	
</section>