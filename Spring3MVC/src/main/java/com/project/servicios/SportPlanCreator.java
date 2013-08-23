package com.project.servicios;

import java.util.List;

import com.project.model.Exercise;
import com.project.model.SportPlan;

// Implementacion del patron software "Template method"
public abstract class SportPlanCreator {

	// Metodos primitivos
	public abstract SportPlan createSportPlanStructure(SportPlan sportPlan);
	
	public abstract SportPlan definePeriodization(SportPlan sportPlan);
	
	public abstract SportPlan defineExercisesType(SportPlan sportPlan);
	
	public abstract List<Exercise> getExercises(SportPlan sportPlan);
	
	public abstract SportPlan defineExercises(SportPlan sportPlan,List<Exercise> exercises);
	
	public abstract SportPlan defineSpeedExecution(SportPlan sportPlan);

	public abstract SportPlan defineIntensity(SportPlan sportPlan);
	
	public abstract SportPlan defineNumOfSeries(SportPlan sportPlan);
	
	// Metodos template
	public SportPlan createSportPlanAutomatically(SportPlan sportPlan){
		createSportPlanStructure(sportPlan);
		definePeriodization(sportPlan);
		defineExercisesType(sportPlan);
		List<Exercise> exercises = getExercises(sportPlan);
		defineExercises(sportPlan,exercises);
		defineNumOfSeries(sportPlan);
		defineSpeedExecution(sportPlan);
		defineIntensity(sportPlan);
				
		return sportPlan;
	}
	
	public SportPlan createSportPlanManuallyStepOne( SportPlan sportPlan){
		createSportPlanStructure(sportPlan);
		definePeriodization(sportPlan);
		defineExercisesType(sportPlan);
		return sportPlan;
	}
	
	public SportPlan createSportPlanManuallyStepTwo (SportPlan sportPlan, List<Exercise> exercises){
		defineExercises(sportPlan,exercises);
		defineNumOfSeries(sportPlan);
		defineSpeedExecution(sportPlan);
		defineIntensity(sportPlan);				
		return sportPlan;
	}
}