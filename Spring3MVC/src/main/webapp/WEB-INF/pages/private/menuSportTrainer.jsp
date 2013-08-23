<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script type="text/javascript">
/*codigo para el filtro en la tabla de socios*/
	$(function() { 
		var theTable2 = $('#customerTable')
		$("#filterCustomers").keyup(function() {
			$.uiTableFilter( theTable2, this.value );
		})
	});
$(document).ready(function() {
	$('#variableConfiguracion').hide();
	$('#fCustomer').hide();
	$('#formCustomer').validate();
	getCustomers();
});
function mostrarSocios() {
	$('#variableSocios').show();
	$('#variableConfiguracion').hide();
}
function mostrarConfiguracion() {
	$('#variableSocios').hide();
	$('#variableConfiguracion').show();
	$('#fCustomer').hide();
}
function muestraFormCustomer(){
	$('#fCustomer').toggle();
}
function getCustomers() {
	jQuery.getJSON("getCustomersFromSportTrainer.do", function(json) {
		muestraCustomers(json);
	});
}
function muestraCustomers(json) {
	var customers = json.listaCustomers;
	for (i = 0; i < customers.length; i++) {
		var locationInfo = "modifyInfoCustomer.do?userId="
				+ customers[i].id;
		var enlaceInfo = "<input type='button' onclick=location='"+locationInfo+"' class='estiloBotones boton' value='->' />";
		
		var locationEliminar = "deleteCustomer.do?userId="
				+ customers[i].id;
		var enlaceEliminar = "<input type='button' onclick='eliminarCustomer("+customers[i].id+");' class='estiloBotones boton' value='X' />";
		var locationGenerarPlan = "generarPlan.do?userId=" + customers[i].id;
		var enlaceGenerarPlan = "<a href='#' type='button' onclick=location='"+locationGenerarPlan+"' ><img id='generatorImg' src='<c:url value='images/generator.png'/>' alt='Generar Plan'/></a>";
		customer = "<tr id='customer"+customers[i].id+"'><td class='width34'>"+enlaceGenerarPlan+"</td><td class='width34'>"+ enlaceInfo + "</td><td class='width34'>"+ enlaceEliminar + "</td><td class='width100'>"+ customers[i].name + "</td><td class='width100'>"+ customers[i].surName + "</td></tr>";
		$('#customerTable').append(customer);
	}
	getInfoCustomers();
}
function getInfoCustomers() {
	jQuery.getJSON("getInfoCustomersFromSportTrainer.do", function(json) {
		muestraInfoCustomers(json);
	});
}
function muestraInfoCustomers(json) {
	var customers = json.listaInfoCustomers;
	for (i = 0; i < customers.length; i++) {
		var custo = customers[i];
		var edad = custo.dateBirth;
		var dateBirth = "<td class='width100'>"+getEdad(edad)+" años</td>";
		var weight = "<td class='width60'>"+custo.weight+" kg</td>";
		var height = "<td class='width80'>"+custo.height+" cm</td>";
		var laboralActivity = "<td class='width100'>"+custo.laboralActivity+"</td>";
		var customerLevel = "<td class='width100'>"+custo.customerLevel+"</td>";
		var customer = dateBirth+weight+height+laboralActivity+customerLevel;
		
		$('#customerTable > tbody >  #customer'+custo.idCustomer).append(customer);
		/* $('#customerTable tr').append("<td>NEW COLUMN</td>"); */
	}
}
function getEdad(edad){
	var anio = edad.substring(0,4);
	var date = new Date();
	return date.getFullYear() - anio;
}

function eliminarCustomer(id){
	  var answer = confirm("¿Eliminar este cliente?")
	    if (answer){
	        location="deleteCustomer.do?userId="+id;
	    }
	    
	    return false;
}
</script>
<section class="panelIzq">
	<div id="infoSportTrainer">
		<c:if test="${!empty nombreSportTrainer}">
			<h2 id="infoSportTrainerName">
				<spring:message code="etiqueta.titulo.sport.trainer" />
				${nombreSportTrainer}
			</h2>
		</c:if>
		<div class="redondeado shadow fondoForm padding2">
			<table style="width:100%;">
				<tr>
					<td><spring:message
							code="etiqueta.titulo.menu.sport.centre" />:</td>
					<td class="">
						<c:if test="${!empty sportCentre}">${sportCentre}</c:if>
						<c:if test="${empty sportCentre}">-</c:if>
					</td>
				</tr>
				<tr>
					<td><spring:message
							code="etiqueta.panel.sport.trainer.clientes" />:</td>
					<td class="">
						<c:if test="${!empty numCustomers}">${numCustomers}</c:if>
						<c:if test="${empty numCustomers}">-</c:if>
					</td>
				</tr>
				<tr>
					<td><spring:message code="etiqueta.panel.sport.centre.planes" />:</td>
					<td class="">
						<c:if test="${!empty numSportPlans}"> ${numSportPlans}</c:if>
						<c:if test="${empty numSportPlans}"> -</c:if>
					</td>
				</tr>
			</table>
		</div>
	</div>
</section>
<section class="panelDer">
	<div id="menuSportTrainer">
		<h2>
			<spring:message code="etiqueta.titulo.panel" />
		</h2>
		<div class="redondeado shadow fondoForm padding2">
			<table>
				<tr>
					<td><spring:message code="etiqueta.titulo.socios" /></td>
					<td class="paddingLeft35"><input id="perfilSportTrainer"
						type="button" onclick="mostrarSocios()"
						class="estiloBotones boton" value="->" /></td>
				</tr>
				<tr>
					<td><spring:message code="etiqueta.titulo.configuracion" /></td>
					<td class="paddingLeft35"><input id="perfilSportTrainer"
						type="button" onclick="mostrarConfiguracion()"
						class="estiloBotones boton" value="->" /></td>
				</tr>
			</table>
		</div>
	</div>
</section>
<section>
<div id="variableSocios">
		<h2>
			<spring:message code="etiqueta.titulo.socios" />
		</h2>		
		<div class="redondeado shadow fondoForm padding2">	
			<div><form id="filter-formCustomers"><label for="filterCustomers" id="labelFilterCustomers">Filtrar</label><input name="filter" id="filterCustomers" value="" maxlength="20" size="20" type="text"></form></div>	
			<table>
				<tr>
					<!-- <td class="width80">Generator</td>										
					<td class="width60">Info</td>
					<td class="width80">Eliminar</td> -->
					<td class="width34"></td>										
					<td class="width34"></td>
					<td class="width34"></td>
					<td class="width100">Nombre</td>
					<td class="width100">Apellido</td>
					<td class="width100">Edad</td>
					<td class="width60">Peso</td>
					<td class="width80">Altura</td>
					<td class="width100">Actividad Laboral</td>
					<td class="width100">Nivel</td>
				</tr>
			</table>
			<div class="scrollable" id="scrollableC">
				<table id="customerTable"></table>
			</div>
			<div>
				<spring:message code="etiqueta.link.registrar.customer" />
				<input id="nuevoCustomer" type="button"
					onclick="muestraFormCustomer()" class="estiloBotones boton"
					value="->" />
			</div>
		</div>
	</div>
	<div id="variableConfiguracion">
		<h2>
			<spring:message code="etiqueta.titulo.configuracion" />
		</h2>
		<div class="redondeado shadow fondoForm padding2">
			<div>
				<input id="perfilSportTrainer" type="button"
					onclick="location='modifyInfoSportTrainer.do?userId=${id}'"
					class="estiloBotones boton"
					value="<spring:message code="etiqueta.titulo.modify.credentials" />" />
			</div>
			<div>
				<input id="passSportCentre" type="button"
					onclick="location='modifyPassWord.do'"
					class="estiloBotones boton"
					value="<spring:message code="etiqueta.titulo.modify.password" />" />
			</div>
		</div>
	</div>
	
	<div id="fCustomer">
		<form:form action="addCustomer.do" id="formCustomer"
			commandName="customer"
			class="redondeado shadow fondoForm padding2">
			<p class="titleForm padding2">
				<spring:message code="etiqueta.titulo.nuevoCustomer" />
			</p>
			
			<c:if test="${not empty error}">
				<div class="errorblock redondeado shadow">
					<spring:hasBindErrors name="customer">
						<p>
							<form:errors path="username" />
						</p>
						<p>
							<form:errors path="password" />
						</p>
						<p>
							<form:errors path="repetirPassword" />
						</p>
						<p>
							<form:errors path="name" />
						</p>
						<p>
							<form:errors path="surName" />
						</p>
						<p>
							<form:errors path="email" />
						</p>
					</spring:hasBindErrors>
				</div>
			</c:if>
			<div>
				<label for="username" class="labelForm"><spring:message
						code="etiqueta.modelo.user.username" /></label>
				<form:input path="username" class="required" />
			</div>

			<div>
				<label for="password" class="labelForm"><spring:message
						code="etiqueta.modelo.user.password" /></label>
				<form:password path="password" class="required" minlength="6" />
			</div>
			<div>
				<label for="repetirPassword" class="labelForm"><spring:message
						code="etiqueta.modelo.user.repetirPassword" /></label>
				<form:password path="repetirPassword" class="required" minlength="6" />
			</div>
			<div id="columnaFormCustomer">
				<div>
					<label for="name" class="labelForm"><spring:message
							code="etiqueta.modelo.customer.nombre" /></label>
					<form:input path="name" id="inputNameFormSportTrainer"
						class="required" />
				</div>
				<div>
					<label for="surName" class="labelForm"><spring:message
							code="etiqueta.modelo.sportCentre.surname" /></label>
					<form:input path="surName" id="inputSurnameFormSportTrainer"
						class="required" />
				</div>
				<div>
					<label for="email" class="labelForm"><spring:message
							code="etiqueta.modelo.sportCentre.email" /></label>
					<form:input path="email" id="inputEmailFormSportTrainer"
						class="required email" />
				</div>
			</div>
			<input id="submitCustomer" name="submit" type="submit"
				value="<spring:message code="etiqueta.link.aniadirSporTrainer" />"
				class="estiloBotones" />
		</form:form>
	</div>
</section>