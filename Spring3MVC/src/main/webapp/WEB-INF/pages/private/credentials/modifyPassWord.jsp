<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#formModifyPassWord").validate();
	});
</script>
<section id="fModifyPassWord">
	<form:form id="formModifyPassWord" action="modifyPassWord.do"
		commandName="user" class="redondeado shadow fondoForm">

		<p class="titleForm">
			<spring:message code="etiqueta.titulo.modify.password" />
		</p>
		<c:if test="${not empty error}">
			<div class="errorblock redondeado shadow">
				<spring:hasBindErrors name="user">
					<p>
						<form:errors path="password" />
					</p>
					<p>
						<form:errors path="repetirPassword" />
					</p>
				</spring:hasBindErrors>
			</div>
		</c:if>
		<div>
			<label for="password" class="labelForm"><spring:message
					code="etiqueta.modelo.sportTrainer.password" /></label>
			<form:password path="password" class="required" minlength="6" />
		</div>
		<div>
			<label for="repetirPassword" class="labelForm"><spring:message
					code="etiqueta.modelo.sportTrainer.repetir.password" /></label>
			<form:password path="repetirPassword" class="required" minlength="6"  />
		</div>
		<input id="submitModifyPassWord" name="submit" type="submit"
			value="<spring:message code="etiqueta.link.modificar" />"
			class="estiloBotones" />
	</form:form>
</section>
