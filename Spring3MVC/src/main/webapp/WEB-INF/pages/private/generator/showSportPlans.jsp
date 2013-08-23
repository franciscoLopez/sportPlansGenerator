<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<section>
	<div class="redondeado shadow fondoForm padding2">	
		<c:choose>
		  <c:when test="${numPlanes eq 0}">
		    <h2>Aun no tiene planes de entrenamiento generados.</h2>
		  </c:when>
		  <c:otherwise>
		  <c:if test="${!empty listSportPlans}">
		  	<table>
		  		<c:forEach items="${listSportPlans}" var="sportPlan">	    	
					<tr>
						<td>${sportPlan.sportPlanId}</td>
						<td>${sportPlan.name}</td>
						<td>${sportPlan.level}</td>
						<td>${sportPlan.sportTrainerName}</td>
						<td><input type="button" class="estiloBotones" onclick="location='showSportPlan.do?sportPlanId=<c:out value="${sportPlan.sportPlanId}"/>'" value="<spring:message code="etiqueta.modelo.customer.consultar.plan"/>"/></td>
					</tr>	
				</c:forEach>
		  	</table>
		  </c:if>	  		   
		  </c:otherwise>
		</c:choose>
	</div>	
</section>