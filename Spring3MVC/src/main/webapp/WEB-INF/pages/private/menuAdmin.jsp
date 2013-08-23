<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script type="text/javascript">
/*codigo para el filtro en la tabla de ejercicios*/
	$(function() { 
		var theTable = $('#exercisesTable')
		$("#filterExercises").keyup(function() {
			$.uiTableFilter( theTable, this.value );
			getNumberExercises();
		})
		
	});
	
	var musculos;
	
	$(document).ready(function() {
		getMusculos();
		getEjercicios();
		$('#slatMensaje').delay(10000).fadeOut(2000);
	});
	function getMusculos() {
		jQuery.getJSON("getMuscles.do", function(json) {
			muestraMusculos(json);
		});
	}
	function muestraMusculos(json) {
		musculos = json.listaMusculos;
		var file = '<tr id="filaFiltro"></tr>';
		var file1 = '<tr id="filaFiltroCheckbox"></tr>';
		$('#filtrosTable').append(file);
		$('#filtrosTable').append(file1);
		for (i = 0; i < musculos.length; i++) {
			var muscle = '<td><div>'+musculos[i].name+'</div></td>';
			var filtro = '<td><input type="checkbox" name='+musculos[i].name+' value='+musculos[i].name+' checked></td>';	
			$('#filaFiltro').append(muscle);
			$('#filaFiltroCheckbox').append(filtro);
		}
		
	}
	
	function getEjercicios(ejercicios){
		jQuery.getJSON("getExercises.do", function(json) {
			muestraEjercicios(json);
		});
	}
	
	function muestraEjercicios(json){
		var ejercicios = json.listaEjercicios;
		for(i = 0; i < ejercicios.length; i++){
			// <td><img class="icono" alt="" src="<c:url value="'+ejercicios[i].src+'"/>"></td>
			var exercise = ejercicios[i];
			var modify = "modifyExercise.do?exercise="+ exercise.id;
			var name = exercise.name;
			var muscle;
			var type; 
			if(exercise.principalMuscle != null){
				muscle = exercise.principalMuscle.muscle;
				type = exercise.principalMuscle.type;
			}else{
				muscle = "undefined";
				type = "undefined";
			}		
			var exercise = '<tr class="'+muscle+'"><td class="name width450">'+name+'</td><td class="width120">'+muscle+'</td><td class="width120">'+type+'</td><td class="width60"><input type="button" onclick=location="'+modify+'" class="estiloBotones boton" value="->" /></td></tr>';
			$('#exercisesTable').append(exercise);
		}
		getNumberExercises();
	}
	function getNumberExercises(){
		var total = 0;
		$('#exercisesTable tr').each(function(n){
			if(this.style.display != 'none'){
				total++;
			}
		}); 
		$('#numberExercises').html(total);		
	}
</script>
<div id="Notification"></div>
<div id="panelAdmin">
	<h1>ADMIN</h1>	
</div>
<section id="administrarEjercicios">
	<h6>Ejercicios (<span id="numberExercises"></span>)</h6>
	<div><form id="filter-formExercises"><label for="filterExercises">Filtro</label><input name="filter" id="filterExercises" value="" maxlength="20" size="20" type="text"></form></div>
	<div id="ejerciciosFiltrado" class="padding2 redondeado shadow fondoForm">
		<table>
			<tr>
				<td class="width450">Nombre</td>
				<td class="width120">Musculo</td>
				<td class="width120">Tipo</td>
				<td class="width60">Editar</td>
			</tr>
		</table>
		<div class="scrollable">
			<table id="exercisesTable">				
			</table>
		</div>
	</div>
</section>