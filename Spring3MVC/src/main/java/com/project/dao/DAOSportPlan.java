package com.project.dao;

import java.util.List;

import com.project.dto.DTOSportPlan;
import com.project.model.Equipment;
import com.project.model.Exercise;
import com.project.model.Orden;
import com.project.model.RoutineType;
import com.project.model.SportPlan;

public interface DAOSportPlan {
	
	public SportPlan getSportPlan(Long sportPlanId);

	public Long createSportPlanStepOne(DTOSportPlan dtoSportPlan);
	
	public SportPlan createSportPlanStepTwo(DTOSportPlan dtoSportPlan);

	public Integer getSessionsPerWeek(String levelId, String objetiveId);

	public Integer getRecuperationTime(String level, String objetive);

	public List<Orden> getRoutineOrders();

	public List<RoutineType> getRoutineTypes();
	
	public Integer[] getExercisesPerSession(String level, String objetive,String routineType);
	
	public Orden getRoutineOrder(String order);
	
	public RoutineType getRoutineType(String type);

	public List<Equipment> getEquipments();

	public List<Equipment> getEquipments(String level);

	public List<DTOSportPlan> getSportPlansByUserId(Long id);

	public SportPlan createSportPlanStepTwoManually(DTOSportPlan dtoSportPlan);
	
	public List<Exercise> getExercises(SportPlan sportPlan);

	public SportPlan createSportPlanStepThreeManually(Long sportPlanId,
			String exercises);
}
