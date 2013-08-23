<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<table style="margin-top:100px;text-align: center;">
<tr><td>ID</td>
	<td class="width450 ">Ejercicio</td>
	<td class="width100">Material</td>
	<td class="width120">Tipo</td>
	<td class="width120">Musculo ppal(tipo)</td>
	<td class="width120">Musculos secundarios(tipo)</td>
	<td class="width60">Imagen</td>
	<td class="width60">Descripción</td>
</tr>
	<c:forEach items="${lst}" var="exercise">
		<c:set var="src" value="${exercise.image.src}"/>
		<c:set var="ruta" value="images\\storage\\exercises\\"/>
		<c:set var="barra" value="\\"/>
		<c:set var="ppalMuscle" value="${exercise.principalMuscleType.muscle.muscle}"/>
		<tr>		
		<td  class="width450 ">${exercise.name}</td>
		<c:if test="${not empty exercise.equipment.equipment}">
			<td  class="width100">${exercise.equipment.equipment}</td>
		</c:if>
		<c:if test="${empty exercise.equipment.equipment}">
			<td  class="width100" style="color:red">DEFINIR</td>
		</c:if>
		<c:if test="${not empty exercise.exerciseType.exerciseType}">
			<td  class="width120">${exercise.exerciseType.exerciseType}</td>
		</c:if>
		<c:if test="${empty exercise.exerciseType.exerciseType}">
			<td  class="width120" style="color:red">DEFINIR</td>
		</c:if>
		<td  class="width120">${exercise.principalMuscleType.muscle.muscle}(${exercise.principalMuscleType.type})</td>
		<%-- <td class="width60"><img width="40px" height="40px" alt="" src="<c:url value="${ruta}${ppalMuscle}${barra}${src}"/>"></td> --%>
		<td></td>
		</tr>	
	</c:forEach>
</table>
