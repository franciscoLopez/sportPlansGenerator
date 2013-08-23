<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script type="text/javascript">
	$(function() {
        $( "#dateBirth" ).datepicker({
            changeMonth: true,
            changeYear: true,
            yearRange: '1920:2000'
        });
    });
	
	$(document).ready(function() {
		$("#formModifyProfile").validate();
		var dateBirth = $("#dateBirth").val();
		if(typeof(dateBirth) != 'undefined' && dateBirth != null){
			var anio = dateBirth.substring(0,4);
			var mes = dateBirth.substring(5,7);
			var dia = dateBirth.substring(8,10);
			$("#dateBirth").val(mes + "/" + dia + "/" + anio);
		}				
	});
</script>
<section id="fModifyProfile">
	<form:form id="formModifyProfile" action="modifyProfile.do" modelAttribute="dtoProfile"
		commandName="dtoProfile" class="redondeado shadow fondoForm">

		<p class="titleForm">
			<spring:message code="etiqueta.titulo.modify.profile" />
		</p>
		<c:if test="${not empty error}">
			<div class="errorblock redondeado shadow">
				<spring:hasBindErrors name="dtoProfile">
					<p>
						<form:errors path="dateBirth" />
					</p>
					<p>
						<form:errors path="weight" />
					</p>
					<p>
						<form:errors path="height" />
					</p>
					<p>
						<form:errors path="laboralActivity" />
					</p>
					<p>
						<form:errors path="customerLevel" />
					</p> 
				</spring:hasBindErrors>
			</div>
		</c:if>
		<div>
			<label for="dateBirth" class="labelForm"><spring:message
					code="etiqueta.modelo.profile.dateBirth" /></label>
			<form:input path="dateBirth" class="required" id="dateBirth" />
		</div>
		<div>
			<label for="weight" class="labelForm"><spring:message
					code="etiqueta.modelo.profile.weight" /></label>
			<form:input path="weight" class="required" />
		</div>
		<div>
			<label for="height" class="labelForm"><spring:message
					code="etiqueta.modelo.profile.height" /></label>
			<form:input path="height" class="required" />
		</div>
		<div>
			<label for="laboralActivity" class="labelForm"><spring:message
					code="etiqueta.modelo.profile.laboralActivity" /></label>
			<form:input path="laboralActivity"  />
		</div>
 		<div>
			<label for="customerLevel" class="labelForm"><spring:message
					code="etiqueta.modelo.profile.customerLevel" /></label>
			<form:select path="customerLevel" id="customerLevel">
				<c:forEach items="${dtoProfile.customerLevelDefault}" var="level">
					<form:option value="${level}"/>
					<c:out value="${level}"></c:out>
				</c:forEach>
			</form:select>
		</div>
		<form:hidden path="idCustomer"/>
		<input id="submitModifyProfile" name="submit" type="submit"
			value="<spring:message code="etiqueta.link.modificar" />"
			class="estiloBotones" />
		<input type="button" class="estiloBotones" onclick="location='menuGenerarPlan.do?customerId=<c:out value="${dtoProfile.idCustomer}" />'" value="<spring:message code="etiqueta.link.cancelar"/>"/>
	</form:form>
</section>
