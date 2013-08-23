<%@ include file="/WEB-INF/controllers/configuration/includes.jsp"%>
<script>
$(document).ready(function() {
	$('#slatMensaje').delay(10000).fadeOut(2000);
});
$(function() {
    $( ".accordion" ).accordion({
    	collapsible: true,
    	active : false,
    	icons : "null",
    	heightStyle: "content" 
    });
    $( ".explanation" ).tooltip({
    	position: {
    		my: "center bottom",
    		at: "center top",	
    	}    	
    });
    /* $( ".explanation p" ).hide();
    $( ".explanation" ).on('click',function(){
    	$( ".explanation p" ).toggle();	
    }); */
});
</script>
<section id="sportPlanExercises">
	<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication!=null}">
		<sec:authorize ifAnyGranted="ROLE_SPORT_TRAINER">
			<input type="button" class="estiloBotones" onclick="location='menuSportTrainer.do'" value="<spring:message code="etiqueta.link.menu"/>"/>
		</sec:authorize>
		<sec:authorize ifAnyGranted="ROLE_CUSTOMER">
			<input type="button" class="estiloBotones" onclick="location='menuCustomer.do'" value="<spring:message code="etiqueta.link.menu"/>"/>
		</sec:authorize>		
	</c:if>
	<div class="sportPlanDiv redondeado shadow fondoForm padding2">
		<h1>Plan de entrenamiento</h1>
		<div class="inconoGenerarPDF">
			<a href="#" onclick="createPDF();" title="Crear PDF"><img id='generarPDF' src='<c:url value='images/pdficon.png'/>' alt='Generar Plan'/></a>
		</div>		
		<div class="sportPlanName">
			<h2>"${sportPlan.name}"</h2>
		</div>
		
		<div class="sportPlanGeneratedDate">
			<h3>Generado por el monitor deportivo ${sportPlan.sportTrainer.name} el <fmt:formatDate value="${sportPlan.insertDate}" pattern="dd.MM.yyyy" /></h3>
		</div>
		
		<div class="explanation" title="El plan está separado en periodos y semanas.
			En cada semana aparecen los días en los que se ha planificado entrenamiento.<br>
			En la cabecera de la sesión se indica la frecuencia con la que se ha de realizar los ejercicios (RM = Repeticiones Máximas por minuto).">
			<h5>Como interpretar el plan de entrenamiento</h5>
		</div>
		<div id="sportPlanInfo" class="redondeado shadow fondoForm padding2">			
			<div class="sportPlanLevel">
				Nivel <span>${sportPlan.level.level}</span>
			</div>
			<div class="sportPlanObjetive">
				Objetivo <span>${sportPlan.objetive.objetive}</span>
			</div>
			<div class="sportPlanPhysicalQuality">
				Cualidad física a entrenar <span>${sportPlan.physicalQuality.quality}</span>
			</div>
			<div class="sportPlanTotalDuration">
				Duración total <span>${sportPlan.duration.weeks} semanas</span>
			</div>
			<div class="sportPlanSpeedExecution">
				Realizar los ejercicios a una velocidad ${fn:toLowerCase(sportPlan.routineParams.speedExecution.speedExecution)}
			</div>
		</div>
		<div id="exercises">
		<h2 class="exercisesTitle redondeado">Ejercicios</h2>
		<c:set var="periodCount" value="1"/>
		<c:set var="weekCount" value="1"/>
			<script>
				var planDeportivo = new Array();
				var periodo = 0;
			</script>
			<c:forEach items="${sportPlan.plan.periods}" var="period">
				<script>
					var periodoArray = new Array();
					planDeportivo.push(periodoArray);
				</script>
				<div class="periodo accordion">
				<h1>Periodo ${periodCount}</h1>
				<div>
				<script>
					var semana = 0;
				</script>
				<c:forEach items="${period.weeks}" var="week">
					<script>
						var semanaArray = new Array();
						planDeportivo[periodo].push(semanaArray);
					</script>
					<div class="week">
						<h2>Semana ${weekCount}</h2>
						<script>
							var sesion = 0;
						</script>
						<c:forEach items="${week.sessions}" var="session">
						<script>
							var sesionArray = new Array();
							planDeportivo[periodo][semana].push(sesionArray);
						</script>						
							<div class="session accordion">
								<h3>Sesión ${session.dayOfWeek.day} (${session.intensity.minIntensity} - ${session.intensity.maxIntensity} RM)</h3>
								<div>
								<span class="numExercisesPerSession redondeado">
									${fn:length(session.exercises)} ejercicios
								</span>
									<script>
										var diaSession = '<c:out value="${session.dayOfWeek.day}" />';
										planDeportivo[periodo][semana][sesion].push(diaSession);
										var ejercicio = 0;
									</script>
									<c:forEach items="${session.exercises}" var="exercise">		
										<script>
											var bloqueEjercicio = new Array();
											
											var nombre = '<c:out value="${exercise.exercise.name}" />';
											bloqueEjercicio.push(nombre);
											
											var musculo = '<c:out value="${exercise.exercise.principalMuscleType.muscle.muscle}" />';
											bloqueEjercicio.push(musculo);
											
											var equip = '<c:out value="${exercise.exercise.equipment.equipment}" />';
											bloqueEjercicio.push(equip);
											
											var numSeries = '<c:out value="${exercise.numSeries}" />';
											bloqueEjercicio.push(numSeries);
											
											var descanso = '<c:out value="${session.restTime}" />';
											bloqueEjercicio.push(descanso);
											
											
																					
										</script>								
										<div class="exerciseBlock">																	
											<div class="exerciseImage">
												<!-- IMAGEN -->
													<c:set var="src" value="${exercise.exercise.image.src}"/>
													<c:set var="ruta" value="images\\storage\\exercises\\"/>
													<c:set var="barra" value="\\"/>
													<c:set var="ppalMuscle" value="${exercise.exercise.principalMuscleType.muscle.muscle}"/>
													<c:set var="string2" value="${fn:substringBefore(src,'.jpg')}" />
													<img alt="" id="<c:url value="${string2}"/>" class="generatorImage" src='<c:url value="${ruta}${ppalMuscle}${barra}${src}"/>'>		
												<!-- FIN IMAGEN -->
											</div>
											<script>
											var ruta = "<c:out value='${string2}' />";
											bloqueEjercicio.push(ruta);
											
											planDeportivo[periodo][semana][sesion].push(bloqueEjercicio);
											</script>
											<div class="exerciseInfo">
											<div class="exerciseName">${exercise.exercise.name}</div>	
											<table>
												<tr class="muscleName"><td>Músculo</td><td>${exercise.exercise.principalMuscleType.muscle.muscle}</td></tr>
												<tr class="equipmentName"><td>Equipamiento</td><td>${exercise.exercise.equipment.equipment}</td></tr>
												<tr class="numSeries"><td>Número de series</td><td>${exercise.numSeries}</td></tr>
												<tr class="restTime"><td>Descanso</td><td>${session.restTime}  segundos</td></tr>
											</table>										
											</div>										
										</div>
										<script>
											ejercicio = ejercicio + 1;
										</script>
									</c:forEach>
								</div>												
							</div>
							<script>
								sesion = sesion + 1;
							</script>									
						</c:forEach>
						</div>
						<c:set var="weekCount" value="${weekCount + 1 }"/>
						<script>
						semana = semana + 1;
					</script>
				</c:forEach>
				</div>
				</div>
				<c:set var="periodCount" value="${periodCount + 1 }"/>
				<script>
					periodo = periodo + 1;
				</script>
			</c:forEach>	
			<script>				
			function createPDF(){
				var doc = new jsPDF();
				var arrayPlanDeportivo = planDeportivo;
				doc = createCabecera(doc);
				doc = addInfoPlan(doc);
				doc = addTituloEjercicio(doc);
				doc = addEjercicios(doc,arrayPlanDeportivo, 90);
				var nombrePlan = '<c:out value="${sportPlan.name}" />';
				var fechaGeneracion = '<fmt:formatDate value="${sportPlan.insertDate}" pattern="dd.MM.yyyy" />';
				doc.save("Plan de entrenamiento deportivo - " + nombrePlan + " - " + fechaGeneracion + ".pdf" );
			}
			function createCabecera(doc){	
				var col = 20;
				
				var cabecera = "Plan de Entrenamiento Deportivo ";	
				doc.setFontSize(22);
				doc.setTextColor(4,100,22);
				doc.text(col, 15, cabecera);	
				
				var nombrePlan = '<c:out value="${sportPlan.name}" />';	
				doc.setFontSize(16);
				doc.setTextColor(4,100,22);
				doc.text(col, 22, nombrePlan);
				
				var nombreMonitor = '<c:out value="${sportPlan.sportTrainer.name}" />';
				var fechaGeneracion = '<fmt:formatDate value="${sportPlan.insertDate}" pattern="dd.MM.yyyy" />';
				doc.setFontSize(12);
				doc.setTextColor(0);
				var generadoPor = "Generado por el monitor deportivo " + nombreMonitor + " el " + fechaGeneracion +".";
				doc.text(col, 28, generadoPor);
				
				doc.setLineWidth(0.2);
				doc.setDrawColor(4,100,22); // draw red lines
				doc.line(col, 32, 320, 32);

				return doc;
			}

			function addInfoPlan(doc){
				var col = 20;
				var nivel = '<c:out value="${sportPlan.level.level}" />';
				var objetivo = '<c:out value="${sportPlan.objetive.objetive}" />';
				var cualidadFisica = '<c:out value="${sportPlan.physicalQuality.quality}" />';
				var duracionTotal = '<c:out value="${sportPlan.duration.weeks}" />';
				
				var line = 37;
				
				nivel = "Nivel: " + nivel;
				drawTexto(doc,nivel,11,col,line);
				line = line + 5;
				
				objetivo = "Objetivo: " + objetivo;
				drawTexto(doc,objetivo,11,col,line);
				line = line + 5;
				
				cualidadFisica = "Cualidad Física: " + cualidadFisica;
				drawTexto(doc,cualidadFisica,11,col,line);
				line = line + 5;
				
				duracionTotal = "Duración: " + duracionTotal + " semanas";
				drawTexto(doc,duracionTotal,11,col,line);
				line = line + 5;
				
				doc.setLineWidth(0.2);
				doc.setDrawColor(4,100,22);
				doc.line(col, 58, 580, 58);
				
				return doc;
			}

			function addTituloEjercicio(doc){
				var ej = "- Ejercicios -";
				drawTexto(doc,ej,18,90,66);
				return doc;
			}

			var URL;
			function addEjercicios(doc,arrayPlanDeportivo, line){
				var numPeriods = arrayPlanDeportivo.length;
				for( i = 0; i < numPeriods; i++){
					var textoPeriodo = "Periodo " + (i+1);
					if(line > 285){
						doc.addPage();
						line = 15;
					}
					drawTexto(doc,textoPeriodo,20,20,line);
					line = line + 8;
					var numSemanas = arrayPlanDeportivo[i].length;
					for( j = 0; j < numSemanas; j++){
						var textoSemana = "Semana " + (j+1);
						if(line > 285){
							doc.addPage();
							line = 15;
						}
						drawTexto(doc,textoSemana,16,25,line);
						line = line + 6;
						var numSesions = arrayPlanDeportivo[i][j].length;
						for( k = 0; k < numSesions; k++){
							var numEjercicios = arrayPlanDeportivo[i][j][k].length;
							var diaSession = arrayPlanDeportivo[i][j][k][0];
							diaSession = "Sesión " + diaSession;  
							if(line > 285){
								doc.addPage();
								line = 15;
							}
							drawTexto(doc,diaSession,16,35,line);
							line = line + 10;
							for( z = 1; z < numEjercicios; z++){									
								var nombreEjercicio = arrayPlanDeportivo[i][j][k][z][0];
								var musculoEjercicio = arrayPlanDeportivo[i][j][k][z][1];
								var materialEjercicio = arrayPlanDeportivo[i][j][k][z][2];
								var numSeriesEjercicio = arrayPlanDeportivo[i][j][k][z][3];
								var restEjercicio = arrayPlanDeportivo[i][j][k][z][4];
								var urlIMG = arrayPlanDeportivo[i][j][k][z][5];														
								
								var dataURL = getBase64Image(urlIMG);																					
								
								// Image
								if(line > 285){
									doc.addPage();
									line = 15;
								}
								// addImage(imagen, tipo, x , y , width , height)
								doc.addImage(dataURL, 'JPEG', 10, line,35, 30);
								

								// Nombre
								if(line > 285){
									doc.addPage();
									line = 15;
								}			
								doc.setFontStyle("bold");
								drawTexto(doc,nombreEjercicio,11,50,line);
								doc.setFontStyle("normal");
								line = line + 6;
								
								// Musculo
								if(line > 285){
									doc.addPage();
									line = 15;
								}
								doc.setTextColor(0,124,250);
								drawTexto(doc,"Músculo",10,55,line);
								doc.setTextColor(0);
								drawTexto(doc,musculoEjercicio,10,125,line);
								line = line + 6;
								
								// Material
								if(line > 285){
									doc.addPage();
									line = 15;
								}
								doc.setTextColor(0,124,250);
								drawTexto(doc,"Material",10,55,line);
								doc.setTextColor(0);
								drawTexto(doc,materialEjercicio,10,125,line);
								line = line + 6;
								
								// Series
								if(line > 285){
									doc.addPage();
									line = 15;
								}
								var nSe = numSeriesEjercicio + " series";
								doc.setTextColor(0,124,250);
								drawTexto(doc,"Número de series",10,55,line);
								doc.setTextColor(0);
								drawTexto(doc,nSe,10,125,line);
								line = line + 6;
								
								// Descanso
								if(line > 285){
									doc.addPage();
									line = 15;
								}
								var descanso = restEjercicio + " segundos";
								doc.setTextColor(0,124,250);
								drawTexto(doc,"Descanso",10,55,line);
								doc.setTextColor(0);
								drawTexto(doc,descanso,10,125,line);
								
								line = line + 10;																	
								
							}
							line = line + 10;
						}							
						doc.setLineWidth(0.2);
						doc.setDrawColor(4,100,22);
						doc.line(25, line -5, (line-5)*10, line-5);
						line = line + 5;
					}
					line = line + 5;
				}
				return doc;
			}

			function drawTexto(doc,string,tam,col,lin){
				doc.setFontSize(tam);
				doc.text(col, lin, string);
			}			
			
			function getBase64Image(ruta) {
				var img = document.getElementById(ruta);
				
				// Take action when the image has loaded
			    var imgCanvas = document.createElement("canvas"),
			        imgContext = imgCanvas.getContext("2d");
			 
			    // Make sure canvas is as big as the picture
			    imgCanvas.width = img.width;
			    imgCanvas.height = img.height;
			 
			    // Draw image into canvas element
			    imgContext.drawImage(img, 0, 0, img.width, img.height);
			 
			    // Get canvas contents as a data URL
			    var imgAsDataURL = imgCanvas.toDataURL("image/jpeg");
			 
			    return imgAsDataURL;			
			}
			</script>	
		</div>
	</div>
</section>
<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication!=null}">
	<sec:authorize ifAnyGranted="ROLE_SPORT_TRAINER">
		<input type="button" class="estiloBotones" onclick="location='menuSportTrainer.do'" value="<spring:message code="etiqueta.link.menu"/>"/>
	</sec:authorize>
	<sec:authorize ifAnyGranted="ROLE_CUSTOMER">
		<input type="button" class="estiloBotones" onclick="location='menuCustomer.do'" value="<spring:message code="etiqueta.link.menu"/>"/>
	</sec:authorize>
</c:if>
