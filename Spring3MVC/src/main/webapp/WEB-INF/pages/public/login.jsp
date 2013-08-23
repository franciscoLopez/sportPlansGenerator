<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<!-- main -->
<section id="login">
	<c:if test="${not empty error}">
		<div class="errorblock redondeado shadow">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<div id="formLogin" class="redondeado shadow fondoForm">
		<form name='f' action="<c:url value='j_spring_security_check' />"
			method='POST'>
			<div>
				<p class="titleForm">
					<spring:message code="etiqueta.titulo.inicio" />
				</p>
				<div>
					<label for="userLogin" class="labelForm"> <spring:message
							code="etiqueta.modelo.user.username" />
					</label>
					<div>
						<input id="usernameInput" type='text' name='j_username' value=''>
					</div>
				</div>
				<div>
					<label for="userPass" class="labelForm"> <spring:message
							code="etiqueta.modelo.user.password" />
					</label>
					<div>
						<input id="passwordInput" type='password' name='j_password'>
					</div>
				</div>
				<div>
					<input id="submit" name="submit" type="submit" value="Acceder"
						class="estiloBotones" /> <input id="registroLogin" type="button"
						onclick="location='formSportCentre.do'" class="estiloBotones"
						value="<spring:message code="etiqueta.link.registrar" />" />
				</div>
			</div>
		</form>
		<%-- <img alt="" src="<c:url value="images\storage\exercises\Triceps\PRESS_FRANCES_EN_BANCO_PLANO.png"/>"> --%>
	</div>
</section>