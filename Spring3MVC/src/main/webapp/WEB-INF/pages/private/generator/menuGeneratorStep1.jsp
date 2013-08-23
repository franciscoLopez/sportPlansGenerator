<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script>
$(document).ready(function() {
	$('#slatMensaje').delay(10000).fadeOut(2000);
	var level = '<c:out value="${dtoSportPlan.level}" />';
	$('#sessions').html('0');
	calculaSessionsPerWeek(level,$('#objetive').val());
	getRecuperationTime();	
});
window.onload = function() {
	setAction();
}

/* $(function() {
    $( "#datepicker" ).datepicker({onSelect: function() {
        alert($(this).val());
    }});
}); */
$(function() {
	var valor   = parseInt('<c:out value="${weeks}" />');
	var offset  = parseInt('<c:out value="${offset}" />');
	var periods = parseInt('<c:out value="${periodsNumber}" />');
	var minimo = valor - offset;
	var maximo = valor + offset;
    $( "#slider" ).slider({
        value: valor,
        min: minimo,
        max: maximo,
        step: 1,
        slide: function( event, ui ) {
        	if(periods < 2){
        		$( "#amount" ).html(periods + " periodo de " +ui.value + " semanas" );
        	}else{
        		$( "#amount" ).html(periods + " periodos de " +ui.value + " semanas" );	
        	}
        	$('#periods').val(periods);
        	$('#weeks').val(ui.value);
        	updateDurationSpan();
        }
    });
    var valor = $( "#slider" ).slider( "value" );
    if(periods < 2){
    	$( "#amount" ).html(periods + " periodo de " + valor + " semanas" );
    }else{
    	$( "#amount" ).html(periods + " periodos de " + valor + " semanas" );
    }
    $('#periods').val(periods);
    $('#weeks').val(valor); 
});

function updateDurationSpan(){
	var sessions = $('#sessionsPerWeek').val();
	var weeks =  $('#weeks').val();
	var periods = $('#periods').val(); 
	$('#durationSpan').html('('+weeks * periods+' semanas , '+weeks * periods * sessions+' sesiones )');
}

$(function (){
	// SessionsPerWeek
    var sessions = $('#sessions').html();
	$( "#sliderSessions" ).slider({
        value: sessions,
        min: 1,
        max: sessions,
        step: 1,
        slide: function( event, ui ) {
        	$( "#sessions" ).html(ui.value + " sesiones" );
        	$('#sessionsPerWeek').val(ui.value);
        	updateDurationSpan();
        	selectDefaultDays();
        }
    });
	var value = $( "#sliderSessions" ).slider( "value" ) ;
	$( "#sessions" ).html(value);
	$('#sessionsPerWeek').val(value);
});

function calculaSessionsPerWeek(level, objetive){
	jQuery.getJSON("getSessionsPerWeek.do?level="+level+"&objetive="+objetive, function(json) {
		
	}).success(function(json){
		$('#sessions').html(json.sessionsPerWeek + " sesiones");
		$('#sliderSessions').slider("option",{max : json.sessionsPerWeek,value : json.sessionsPerWeek });
		$('#sessionsPerWeek').val($( "#sliderSessions" ).slider( "value" ));
		updateDurationSpan();
		selectDefaultDays();
	});	
}
$("#objetive").live('change', function() {
	var level = '<c:out value="${dtoSportPlan.level}" />';
	calculaSessionsPerWeek(level,$(this).children(":selected").html());
	getRecuperationTime();	
	selectDefaultDays();
});

//checkbox daysOfWeek
$("input:checkbox").live('click', function(){
    // total allowed to be checked.
    var max_allowed = $('#sessionsPerWeek').val();
    // count how many checked.
    var checked = $("input:checked").size();
    // perform test.
    if ( checked > max_allowed ) {
        // is more than the max so uncheck.
        $(this).removeAttr("checked");
        // display error message.
        alert("M�ximo " + max_allowed + " sesiones");
    }else{
    	//comprobamos si respeta el tiempo de descanso
    	var recuperationTime = $('#recuperationTimeSpan').html();
    	if(recuperationTime != -1){
    		var ids = new Array();
    		$('#daysOfWeekDiv input:checkbox').each(function(){
    			if($(this).attr("checked")){
    				var id = $(this).attr("name");
    				id = id.substring(id.length - 2, id.length - 1);    				
    				ids.push(id);    				
    			}
    		});    		
    		var tam = ids.length;
    		if(tam > 1){
    			for(i = 0 ; i < tam; i++){        			
    				var aux = ids[i];
        			for(j = i + 1 ; j < tam; j ++){
        				if(Math.abs(aux - ids[j]) <= 1){
        					alert('Debe respetar el tiempo de recuperacion ('+recuperationTime+' horas)');
        					$('#daysOfWeek'+ids[j]+'1').removeAttr("checked");
        				}
        			}
        		}	
    		}    	
    	}
    }
});
function desmarcaDaysOfWeek(){
	$("input:checkbox").removeAttr("checked");
}
function getRecuperationTime(){
	var level = '<c:out value="${dtoSportPlan.level}" />';
	var objetive = $('#objetive').val();
	jQuery.getJSON("getRecuperationTime.do?level="+level+"&objetive="+objetive, function(json) {	
	}).success(function(json){
		var recuperationTime = json.recuperationTime;
		if(recuperationTime == -1){
			$('#recuperationTimeP').hide();
			$('#recuperationTimeMuscleGroup').val(parseInt(48));
		}else{
			$('#recuperationTimeP').show();			
			$('#recuperationTimeMuscleGroup').val(parseInt(-1));
		}
		$('#recuperationTime').val(parseInt(recuperationTime));
		$('#recuperationTimeSpan').html(recuperationTime);		
	});	
}

function selectDefaultDays(){
	desmarcaDaysOfWeek();
	var sessions = $('#sessionsPerWeek').val();
	if(sessions == 1){// Lunes
		$('#daysOfWeek11').attr("checked","checked");
	}
	else if(sessions == 2){// Lunes y miercoles
		$('#daysOfWeek11').attr("checked","checked");
		$('#daysOfWeek31').attr("checked","checked");
	}
	else if(sessions == 3){// Lunes, miercoles y viernes
		$('#daysOfWeek11').attr("checked","checked");
		$('#daysOfWeek31').attr("checked","checked");
		$('#daysOfWeek51').attr("checked","checked");
	}else if(sessions == 4){// Lunes, martes, jueves y viernes
		$('#daysOfWeek11').attr("checked","checked");
		$('#daysOfWeek21').attr("checked","checked");
		$('#daysOfWeek41').attr("checked","checked");
		$('#daysOfWeek51').attr("checked","checked");
	}else if(sessions == 5){// Lunes, martes,miercoles, jueves y viernes
		$('#daysOfWeek11').attr("checked","checked");
		$('#daysOfWeek21').attr("checked","checked");
		$('#daysOfWeek31').attr("checked","checked");
		$('#daysOfWeek41').attr("checked","checked");
		$('#daysOfWeek51').attr("checked","checked");
	}
	else if(sessions == 6){// Lunes, martes,miercoles, jueves ,viernes y sabado
		$('#daysOfWeek11').attr("checked","checked");
		$('#daysOfWeek21').attr("checked","checked");
		$('#daysOfWeek31').attr("checked","checked");
		$('#daysOfWeek41').attr("checked","checked");
		$('#daysOfWeek51').attr("checked","checked");
		$('#daysOfWeek61').attr("checked","checked");
	}
	
}
function setAction(){
	var manual = '<c:out value="${manual}" />';
	console.log(manual);
	var action = "";
	if(manual == 0){
		action = "generateSportPlanStep1.do";
	}else{
		action = "generateSportPlanStep1Manual.do";
	}
	var form = document.getElementById("formSportPlansGenerator"); 
	form.action = action;
}
</script>
<section id="fSportPlansGenerator">
	<form:form id="formSportPlansGenerator"  commandName="dtoSportPlan" class="redondeado shadow fondoForm padding2">	
		<p class="titleForm">
			<spring:message code="etiqueta.titulo.generate" />
		</p>
		<c:if test="${not empty error}">
			<div class="errorblock redondeado shadow">
				<spring:hasBindErrors name="dtoSportPlan">
					<p>
						<form:errors path="name" />
					</p>
					<p>
						<form:errors path="objetive" />
					</p>
					<p>
						<form:errors path="physicalQuality" />
					</p>
					<p>
						<form:errors path="sessionsPerWeek" />
					</p>
					<p>
						<form:errors path="daysOfWeek" />
					</p>
				</spring:hasBindErrors>
			</div>
		</c:if>
		<div class="step redondeado shadow fondoForm">
			<p>PASO 1</p>
		</div>
		<div id="name">
			<label for="name" class="labelForm"><spring:message code="etiqueta.modelo.sport.plan.name" /></label>
			<form:input path="name" class="required" />
		</div>		
		<div id="level">
			<p>NIVEL: <span id="contenidoLevel">${dtoSportPlan.level}</span></p>
		</div>
		<div id="objetive">
			<label for="objetive" class="labelForm"><spring:message
					code="etiqueta.modelo.sport.plan.objetive" /></label>
			<form:select path="objetive" id="objetive" name="objetive" class="required">
				<c:forEach items="${objetives}" var="objetive">
					 <form:option value="${objetive.objetive}"/> 					
				</c:forEach>
			</form:select> 
		</div>
<%-- 		<div id="startDate" class="marginTop5">
			<label for="startDate"><spring:message code="etiqueta.modelo.sport.plan.startDate" /></label>
			<div id="datepicker"></div>
			<form:hidden path="startDate"/>
		</div> --%>
		<div id="durationDiv">			
			<label for="duration"><spring:message code="etiqueta.modelo.sport.plan.duration" /></label>
			<p>
			    <span id="amount" style="color: #f6931f; font-weight: bold;"></span>
			</p>
			<div id="slider" style="width: 20%;">
				<form:hidden path="periods" id="periods"/>
				<form:hidden path="weeks" id="weeks"/>
			</div>
			<div class="marginTop5">
				<label for="sessionsPerWeek"><spring:message code="etiqueta.modelo.sport.plan.sessions.per.week" /></label>
				<p>
				    <span id="sessions" style="color: #f6931f; font-weight: bold;"></span>
				</p>
				<div id="sliderSessions" style="width: 20%;">
					<form:hidden path="sessionsPerWeek" id="sessionsPerWeek"/>
				</div>
				<%-- <form:input path="sessionsPerWeek" class="required"/> --%>				
			</div>
			<div class="marginTop5">
				<div>TOTAL: </div><div id="durationSpan"></div>
			</div>
		</div>
		<div id="physicalQualityDiv" >
			<label for="physicalQuality" class="labelForm"><spring:message code="etiqueta.modelo.sport.plan.physical.quality" /></label>
			<form:select path="physicalQuality" id="physicalQuality" class="required">
				<c:forEach items="${physicalQualities}" var="pQuality">
					<form:option value="${pQuality.quality}"/>
					<c:out value="${pQuality.quality}" />
				</c:forEach>
			</form:select>
		</div>
		<div id="daysOfWeekDiv">
			<label for="days" class="labelForm"><spring:message code="etiqueta.modelo.sport.days.of.week" /></label>
			<!-- Tiempo de recuperacion en horas -->
			<p id ="recuperationTimeP">(Tiempo entre sesiones <span id="recuperationTimeSpan"></span> horas)</p>
			<c:forEach items="${daysOfWeek}" var="day">
				<div>
					<form:checkbox path="daysOfWeek[${day.id}]" name="day" value="${day.day}"/>
					<c:out value="${day.day}" />
				</div>
			</c:forEach>
		</div>
		<form:hidden path="customerId"/>
		<form:hidden path="sportTrainerId"/>
		<form:hidden path="level"/>
		<form:hidden path="recuperationTime"/>
		<form:hidden path="recuperationTimeMuscleGroup"/>
				<input id="submitSportPlansGenerator" name="submit" type="submit"
				value="<spring:message code="etiqueta.link.siguiente" />"
				class="estiloBotones" />				
			<input type="button" class="estiloBotones" onclick="location='menuSportTrainer.do'" value="<spring:message code="etiqueta.link.cancelar"/>"/>
	</form:form>
</section>