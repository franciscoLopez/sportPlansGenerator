<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#musclesTypes > div').hide();
	$('#musclesTypesSecundary > div').hide();
	seleccionaMusculoPorDefecto();
	muestraTiposMusculo();
	$('#slatMensaje').delay(10000).fadeOut(2000);
});
function clickPpalMuscle(myRadio) {    
	$('#musclesTypes > div').hide();
	$('#musclesTypes > div.'+myRadio.value).show();	
}
function clickSecundaryMuscle(myRadio){
	$('#musclesTypesSecundary > div').hide();
	$('#musclesTypesSecundary > div.'+myRadio.value).show();	
}
function seleccionaMusculoPorDefecto(){	
	var muscle = '<c:out value="${dtoExerciseModify.principalMuscle.muscle}" />';
	var type = '<c:out value="${dtoExerciseModify.principalMuscle.type}" />';
	$("#muscles :radio[value='" + muscle + "']").attr('checked', true);	
	$("#musclesTypes :radio[id='" + muscle+"."+type + "']").attr('checked', true);	
}
function muestraTiposMusculo(){
	var muscle = $('#muscles :radio:checked').val();
	var div = $('#musclesTypes > div.'+muscle);
	div.show();
}
</script>
<section id="fModifyExercise">
	<form:form id="formModifyExercise" action="modifyExercise.do"
		commandName="dtoExerciseModify" class="redondeado shadow fondoForm" accept-charset="UTF-8">
		<p class="titleForm">
			<spring:message code="etiqueta.titulo.modify.exercise" />
		</p>
		<div>
			<label for="name" class="labelForm"><spring:message
					code="etiqueta.modelo.exercise.name" /></label>
			<form:input path="name" class="required" id="nameInput"/>
		</div>
		<!-- IMAGEN -->
		<div id="imgExercise">
			<label><spring:message code="etiqueta.modelo.exercise.image"/></label>
			<c:set var="src" value="${dtoExerciseModify.src}"/>
			<c:set var="ruta" value="images\\storage\\exercises\\"/>
			<c:set var="barra" value="\\"/>
			<c:set var="ppalMuscle" value="${dtoExerciseModify.principalMuscle.muscle}"/>
			<%-- <img alt="" src='<c:url value="<c:out value="${ruta}${ppalMuscle}${src}" />"/>"> --%>
			<c:if test="${not empty ppalMuscle}">
				<img alt="" src="<c:url value="${ruta}${ppalMuscle}${barra}${src}"/>">
			</c:if>
			<c:if test="${empty ppalMuscle}">
				<img alt="" src="<c:url value="images\dialog-warning-2.png"/>">
			</c:if>
		</div>		
		<!-- FIN IMAGEN -->
		<div id="commentDiv">
			<label for="comment"><spring:message code="etiqueta.modelo.exercise.description"/></label>
			<form:textarea path="comment" rows="5" cols="30" />	
		</div>		
		
		<div id="exerciseTypeDiv">
			<label for="exerciseType"><spring:message code="etiqueta.modelo.exercise.tipo"/></label>
			<form:select path="exerciseType" id="exerciseType">
				<form:option value="-">-</form:option>
				<c:forEach items="${dtoExerciseModify.exerciseTypeDefault}" var="exerciseType">
					<form:option value="${exerciseType}" />
					<c:out value="${exerciseType}" />
				</c:forEach>
			</form:select>
		</div>
		<div id="equipmentDiv">
			<label for="equipment"><spring:message code="etiqueta.modelo.exercise.equipment"/></label>
			<form:select path="equipment" id="equipment">
			<form:option value="-">-</form:option>
				<c:forEach items="${dtoExerciseModify.equipmentDefault}" var="equipmentDefault">
					<form:option value="${equipmentDefault}" />
					<c:out value="${equipmentDefault}" />
				</c:forEach>
			</form:select>
		</div>
		<div id="principalMuscleDiv" class="redondeado fondoForm">
			<label><spring:message code="etiqueta.modelo.exercise.musculo.principal"/></label>
			<div id="muscles">
				<c:forEach items="${muscles}" var="muscle">
					<div>
						<%-- <input type="radio" name="muscle" value="${muscle}" onclick="clickPpalMuscle(this);"> --%>
						<form:radiobutton path="principalMuscle.muscle" value="${muscle.muscle}" onclick="clickPpalMuscle(this);"/>
						<c:out value="${muscle.muscle}" />
					</div>
				</c:forEach>
			</div>
			<div id="musclesTypes">
			<label><spring:message code="etiqueta.modelo.exercise.musculo.tipo"/></label>
 				<c:forEach items="${musclesTypes}" var="type">
					<div class="${type.muscle}">
						<form:radiobutton id="${type.muscle}.${type.type}" path="principalMuscle.type" value="${type.type}"/>
<%-- 						<input type="radio" name="type" value="${type.type}"> --%>
						<c:out value="${type.type}" />
					</div>
				</c:forEach>
			</div>
		</div>
		<div id="secundaryMuscleDiv" class="redondeado fondoForm">
			<label><spring:message code="etiqueta.modelo.exercise.musculo.secundario"/></label>
			<div id="musclesSecundary">
				<c:forEach items="${muscles}" var="muscle">
					<div>
						<%-- <input type="radio" name="muscle" value="${muscle}" onclick="clickSecundaryMuscle(this);"> --%>
						<form:radiobutton path="secundaryMuscles[${muscle.id}].muscle" name="muscle" value="${muscle.muscle}" onclick="clickSecundaryMuscle(this);"/>
						<c:out value="${muscle.muscle}" />
					</div>
				</c:forEach>
			</div>
			<div id="musclesTypesSecundary">
			<label><spring:message code="etiqueta.modelo.exercise.musculo.tipo"/></label>
				<c:forEach items="${musclesTypes}" var="type">
					<div class="${type.muscle}">
						<%-- <input type="checkbox" name="secundaryMuscles[${i}].type" value="${type.type}"> --%>
						<form:checkbox path="secundaryMuscles[${type.idMuscle}].type" name="type" value="${type.type}"/>
						<c:out value="${type.type}" />
					</div>
				</c:forEach>
			</div>
		</div>
		<input id="submitModifyExercise" name="submit" type="submit"
			value="<spring:message code="etiqueta.link.modificar" />"
			class="estiloBotones" />
		<input type="button" class="estiloBotones" onclick="location='menuAdmin.do'" value="<spring:message code="etiqueta.link.cancelar"/>"/>
		<form:hidden path="id" />
	</form:form>
	
	
</section>