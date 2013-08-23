<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<section>
	<div id="infoSportCentre" class="redondeado shadow fondoForm padding2">
		<c:if test="${!empty customer}">
			<h2 class="nombreCustomer">
				${customer.name} ${customer.surName} 				
			</h2>
			<h3><c:if test="${!empty sportCentre}">
					Centro deportivo ${sportCentre} 
				</c:if>
			</h3>
		</c:if>
		
		<br>
		<c:if test="${!empty customerLevel}">
			<h4>
				<spring:message code="etiqueta.modelo.customer.customerLevel" />: 
				${customerLevel}
			</h4>
			
		</c:if>
		<c:if test="${!empty numPlanes}">
			<h4>
				<spring:message code="etiqueta.panel.sport.centre.planes" />: 
				${numPlanes}
			</h4>	
		</c:if>
	</div>	
</section>
<div class="redondeado shadow fondoForm padding2">	
	<c:choose>
	  <c:when test="${numPlanes eq 0}">
	    <h2>Aun no tiene planes de entrenamiento generados.</h2>
	  </c:when>
	  <c:otherwise>
	  <c:if test="${!empty listSportPlans}">
	  	<table class="listaSportPlans">
	  		<thead>
	  			<tr>
	  			  <th class="hidden"></th>
			      <th class="nombrePlan">Plan Deportivo</th>
			      <th class="nivelPlan">Nivel</th>
			      <th class="monitorPlan">Monitor Deportivo</th>
			    </tr>
	  		</thead>
	  		<c:forEach items="${listSportPlans}" var="sportPlan">	    					
				<tr class="planDeportivo">
					<td class="hidden"><span class="planDeportivoID hidden"><c:out value="${sportPlan.sportPlanId}"/></span></td>					
					<td class="nombrePlan">${sportPlan.name}</td>
					<td class="nivelPlan">${sportPlan.level}</td>
					<td class="monitorPlan">${sportPlan.sportTrainerName}</td>
				</tr>	
			</c:forEach>
			<script>
				$(".planDeportivo").on("click",function(){
					var id = $(this).find(".planDeportivoID").html();
					window.location= "showSportPlan.do?sportPlanId=" + id;
				}); 
			</script>
	  	</table>
	  </c:if>	  		   
	  </c:otherwise>
	</c:choose>
</div>