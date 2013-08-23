<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
    $("#formModifySportCentre").validate();
  });
</script>
<section id="fModifySportCentre">
	<form:form id="formModifySportCentre" action="modifySportCentre.do" 
		commandName="sportCentre" class="redondeado shadow fondoForm">

		<p class="titleForm">
			<spring:message code="etiqueta.titulo.modify.credentials" />
		</p>
		<c:if test="${not empty error}">
			<div class="errorblock redondeado shadow">
				<spring:hasBindErrors name="sportCentre">
					<p>
						<form:errors path="name" />
					</p>
					<p>
						<form:errors path="contactInfo.mobileNumber" />
					</p>
					<p>
						<form:errors path="contactInfo.phoneNumber" />
					</p>
					<p>
						<form:errors path="contactInfo.address.city" />
					</p>
					<p>
						<form:errors path="contactInfo.address.country" />
					</p>
					<p>
						<form:errors path="contactInfo.address.postalCode" />
					</p>
					<p>
						<form:errors path="contactInfo.address.addres" />
					</p>
				</spring:hasBindErrors>
			</div>
		</c:if>
		<div>
			<label for="name" class="labelForm"><spring:message
					code="etiqueta.modelo.sportTrainer.name" /></label>
			<form:input path="name" class="required" />
		</div>
		<div>
			<label for="phoneNumber" class="labelForm"><spring:message
					code="etiqueta.modelo.sportCentre.phoneNumber" /></label>
			<form:input path="contactInfo.phoneNumber"  />
		</div>
		<div>
			<label for="mobileNumber" class="labelForm"><spring:message
					code="etiqueta.modelo.sportCentre.mobileNumber" /></label>
			<form:input path="contactInfo.mobileNumber"  />
		</div>
		<div id="columnaFormModifySportCentre">
			<div>
				<label for="city" class="labelForm"><spring:message
						code="etiqueta.modelo.sportCentre.city" /></label>
				<form:input path="contactInfo.address.city" />
			</div>
			<div>
				<label for="country" class="labelForm"><spring:message
						code="etiqueta.modelo.sportCentre.country" /></label>
				<form:input path="contactInfo.address.country"  />
			</div>
			<div>
				<label for="postalCode" class="labelForm"><spring:message
						code="etiqueta.modelo.sportCentre.postalCode" /></label>
				<form:input path="contactInfo.address.postalCode"  />
			</div>
			<div>
				<label for="address" class="labelForm"><spring:message
						code="etiqueta.modelo.sportCentre.address" /></label>
				<form:input path="contactInfo.address.address"  />
			</div>
			<div>
				<form:input type="hidden" path="id"  />
			</div>
		</div>
		<input id="submitModifySportCentre" name="submit" type="submit"
			value="<spring:message code="etiqueta.link.modificar" />"
			class="estiloBotones"/>
	</form:form>
</section>
