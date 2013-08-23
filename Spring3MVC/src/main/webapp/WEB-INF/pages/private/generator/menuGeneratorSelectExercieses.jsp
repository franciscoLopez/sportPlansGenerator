<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script>
$(document).ready(function() {
	$('#slatMensaje').delay(10000).fadeOut(2000);
	buildAccordion();
});

function buildAccordion(){
 	$(".exercises").find(".muscle").each(function(){
		var id = $(this).attr('id');
		$("." + id).wrapAll("<div />");
	}); 
	setAccordion();
}
function setAccordion(){
	$( ".accordion" ).accordion({
    	collapsible: true,
    	active : false,
    	header: "h2",
    	heightStyle: "content"   	
    });
}
function handleClick(){
	var list = "";
	$(".exercises").find(".exercise > input[type='checkbox']:checked").each(function(){
		var value = $(this).val();
		list += value + ",";
	});
	var sportPlanId = "<c:out value='${sportPlan.id}' />";
	var action = "generateSportPlanStep3Manual.do?sportPlanId=" + sportPlanId + "&exercises=" + list;
	var form = document.getElementById("exercisesForm"); 
	form.action = action;
}
</script>
<section id="sportPlanExercises">
	<div class="redondeado shadow fondoForm padding2">	
		<div class="step redondeado shadow fondoForm">
			<p>PASO 3</p>
		</div>
		<p>Seleccione los ejercicios que desea incluir en el plan.</p>
		<div id="selectExercises" class="exercises accordion">
			<form:form id="exercisesForm" action="#" onsubmit="return handleClick(this);">
				<input id="submitSportPlansGenerator" name="submit" type="submit" class="estiloBotones" >
				<c:if test="${!empty exercises}">
					<c:set var="muscle" value="m"/>
					<c:forEach items="${exercises}" var="exercise">	
						<c:if test="${muscle ne exercise.principalMuscleType.muscle.muscle}">
							 <h2 id="${exercise.principalMuscleType.muscle.muscle}" class="muscle">${exercise.principalMuscleType.muscle.muscle}</h2>
						</c:if>			
						<div class="exercise ${exercise.principalMuscleType.muscle.muscle}">
							<input type="checkbox" name="exercises" value="${exercise.id}" checked><p>${exercise.name}</p>
							<div class="imageExercise">
							<!-- IMAGEN -->
								<c:set var="src" value="${exercise.image.src}"/>
								<c:set var="ruta" value="images\\storage\\exercises\\"/>
								<c:set var="barra" value="\\"/>
								<c:set var="ppalMuscle" value="${exercise.principalMuscleType.muscle.muscle}"/>
								<img alt="" class="generatorImage" src="<c:url value="${ruta}${ppalMuscle}${barra}${src}"/>">		
							<!-- FIN IMAGEN -->
							</div>
						</div>					
						<c:set var="muscle" value="${exercise.principalMuscleType.muscle.muscle}"/>								
					</c:forEach>
				</c:if>
				<input id="submitSportPlansGenerator" name="submit" type="submit"
				value="<spring:message code="etiqueta.link.siguiente" />"
				class="estiloBotones" />
			</form:form>
		</div>	
	</div>	
</section>