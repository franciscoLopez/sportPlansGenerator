<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script>
var equipments;
$(document).ready(function() {
	$('#slatMensaje').delay(10000).fadeOut(2000);
	$('#exercisesPerSessionDiv').hide();
	selectByDefault();
	$('#sportPlanId').val('<c:out value="${sportPlanId}" />');
	getEquipments();
	
	$("input:checkbox").live('click',function(){
		var min_checked = 1;
		// count how many checked.
	    var checked = $("input:checkbox:checked").size();
		if(checked < min_checked){
			$(this).attr("checked","checked");
			 // display error message.
	        alert("M�nimo " + min_checked + " tipo de equipamiento");
		}
	});
});
window.onload = function() {
	setAction();
}

function clickRoutineType(myRadio){
	var routineType = myRadio.value;
	var level ='<c:out value="${dtoSportPlan.level}" />';
	var objetive = '<c:out value="${dtoSportPlan.objetive}" />';
	jQuery.getJSON("getExercisesPerSession.do?level="+level+"&objetive="+objetive+"&routineType="+routineType, function(json) {	
	}).success(function(json){
		showExercisesPerSession(json.exercisesPerSession);
	});	
}

function showExercisesPerSession(exercisesPerSession){
	var min = exercisesPerSession[0];
	var max = exercisesPerSession[1];
	var med = Math.floor((max - min) / 2);
	$('#sliderExercisesPerSession').slider("option",{max : max,value :max - med, min : min });
	$("#exercisesPersessionSpan" ).html(max - med + " ejercicios");
	$('#exercisesPerSession').val(max - med);
	$('#exercisesPerSessionDiv').show();
}
//ExercisesPerSession
$(function (){	
	$( "#sliderExercisesPerSession" ).slider({
        step: 1,
        slide: function( event, ui ) {
        	$( "#exercisesPersessionSpan" ).html(ui.value + " ejercicios" );
        	$('#exercisesPerSession').val(ui.value);
        }
    });
	var value = $( "#sliderExercisesPerSession" ).slider( "value" ) ;
	$( "#exercisesPersessionSpan" ).html(value + " ejercicios");
	$('#exercisesPerSession').val(value);
});

function selectByDefault(){
	$('#routineOrder1').prop('checked',true);
	$('#routineType1').prop('checked',true);
	clickRoutineType($('#routineType1'));
	$("INPUT[type='checkbox']").attr('checked',true);
}

function getEquipments(){
	var level ='<c:out value="${dtoSportPlan.level}" />';
	jQuery.getJSON("getEquipments.do?level="+level, function(json) {	
	}).success(function(json){
		updateEquipments(json.equipments);
	});
}
function updateEquipments(eqs){
	var checkbox = "";
	for(i = 0; i < eqs.length; i++){
		var equipment = eqs[i];
		checkbox += '<input type="checkbox" value="'+equipment.equipment+'" name="equipmentFilter['+i+']" id="equipmentFilter'+equipment.id+'" checked="checked"><span>'+equipment.equipment+'</span>'; 
	}	
	$('#equipmentCheckbox').append(checkbox);	
}
function setAction(){
	var manual = '<c:out value="${manual}" />';
	console.log(manual);
	var action = "";
	if(manual == 0){
		action = "generateSportPlanStep2.do";
	}else{
		action = "generateSportPlanStep2Manual.do";
	}
	var form = document.getElementById("formSportPlansGenerator"); 
	form.action = action;
}
</script>
<section id="fSportPlansGenerator">
	<form:form id="formSportPlansGenerator" commandName="dtoSportPlan" class="redondeado shadow fondoForm padding2">

		<p class="titleForm">
			<spring:message code="etiqueta.titulo.generate" />
		</p>
		<c:if test="${not empty error}">
			<div class="errorblock redondeado shadow">
				<spring:hasBindErrors name="dtoSportPlan">
				<p>
					<form:errors path="routineType" />
				</p>
				<p>
					<form:errors path="routineOrder" />
				</p>
				<p>
					<form:errors path="exercisesPerSession" />
				</p>				
				</spring:hasBindErrors>
			</div>
		</c:if>
		<div class="step redondeado shadow fondoForm">
			<p>PASO 2</p>
		</div>
		<div id="levelDivStep1">
			<p>NIVEL: <span id="contenidoLevel">${dtoSportPlan.level}</span></p>
		</div>
		<div id="objetiveDivStep1">
			<p>OBJETIVO: <span id="contenidoObjetive">${dtoSportPlan.objetive}</span></p>
		</div>
		<div id="durationDivStep1">
			<p>DURACI�N: <span id="contenidoLevel">${dtoSportPlan.periods} periodos de ${dtoSportPlan.weeks} semanas <br>( ${dtoSportPlan.sessionsPerWeek} dias a la semana - ${dtoSportPlan.sessionsPerWeek * dtoSportPlan.periods * dtoSportPlan.weeks } d�as en total )</span></p>
		</div>
		<div id="routineOrderDiv">
			<label><spring:message code="etiqueta.modelo.sport.plan.routine.order"/></label>
			<c:forEach items="${routineOrders}" var="order">
				<div>						
					<form:radiobutton path="routineOrder" name="order" value="${order.routineOrder}"/>
					<c:out value="${order.routineOrder}" />
				</div>
			</c:forEach>
		</div>
		<div id="routineTypeDiv">
			<label><spring:message code="etiqueta.modelo.sport.plan.routine.type"/></label>
			<c:forEach items="${routineTypes}" var="type">
				<div>						
					<form:radiobutton path="routineType" name="type" value="${type.type}" onclick="clickRoutineType(this);"/>
					<c:out value="${type.type}" />
				</div>
			</c:forEach>
		</div>
		<div id="exercisesPerSessionDiv">
			<label for="exercisesPerSession"><spring:message code="etiqueta.modelo.sport.plan.exercises.per.session" /></label>
			<p>
			    <span id="exercisesPersessionSpan" style="color: #f6931f; font-weight: bold;"></span>
			</p>
			<div id="sliderExercisesPerSession" style="width: 50%;">
				<form:hidden path="exercisesPerSession" id="exercisesPerSession"/>
			</div>
			<%-- <form:input path="sessionsPerWeek" class="required"/> --%>				
		</div>
		<div id="equipmentFilterDiv" class="marginTop15">
			<label><spring:message code="etiqueta.modelo.sport.plan.equipment.to.discard"/></label>
			<span><spring:message code="etiqueta.modelo.sport.plan.equipment.explanation"/></span><br>
			<div id="equipmentCheckbox"></div>
<%--  			<c:forEach items="${equipments}" var="equipment">
				<form:checkbox path="equipmentFilter[${equipment.id}]" name="equipment" value="${equipment.equipment}"/>
				<c:out value="${equipment.equipment}" />
			</c:forEach> --%>
		</div>
		<form:hidden path="customerId"/>
		<form:hidden path="sportTrainerId"/>
		<form:hidden path="sportPlanId"/>
		<div class="marginTop15">
			<input id="submitSportPlansGenerator" name="submit" type="submit"
				value="<spring:message code="etiqueta.link.siguiente" />"
				class="estiloBotones" />		
			<input type="button" class="estiloBotones" onclick="location='menuSportTrainer.do'" value="<spring:message code="etiqueta.link.cancelar"/>"/>
		</div>
	</form:form>
</section>