package com.project.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.model.DayOfWeek;
import com.project.model.Equipment;
import com.project.model.SportPlan;


public class DTOSportPlan {
	
	private Long sportPlanId;

	private Long customerId;
	
	private Long sportTrainerId;
	
	private String sportTrainerName;
	
	private String name;

	private String level;
	
	private String objetive;
	
	private String physicalQuality;
	
	private Integer periods;

	private Integer weeks;	
	
	private Integer sessionsPerWeek;
	
	private List<String> daysOfWeek;
	
	private Integer recuperationTime;
	
	private Integer recuperationTimeMuscleGroup;
	
	private String routineType;
	
	private String routineOrder;
	
	private Integer exercisesPerSession;
	
	private List<String> equipmentFilter;	
	
	private Date startDate;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getSportTrainerId() {
		return sportTrainerId;
	}

	public void setSportTrainerId(Long sportTrainerId) {
		this.sportTrainerId = sportTrainerId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjetive() {
		return objetive;
	}

	public void setObjetive(String objetive) {
		this.objetive = objetive;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPhysicalQuality() {
		return physicalQuality;
	}

	public void setPhysicalQuality(String physicalQuality) {
		this.physicalQuality = physicalQuality;
	}

	public Integer getWeeks() {
		return weeks;
	}

	public void setWeeks(Integer weeks) {
		this.weeks = weeks;
	}

	public Integer getSessionsPerWeek() {
		return sessionsPerWeek;
	}

	public void setSessionsPerWeek(Integer sessionsPerWeek) {
		this.sessionsPerWeek = sessionsPerWeek;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public List<String> getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(List<String> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}
	public Integer getRecuperationTime() {
		return recuperationTime;
	}

	public void setRecuperationTime(Integer recuperationTime) {
		this.recuperationTime = recuperationTime;
	}

	public Integer getRecuperationTimeMuscleGroup() {
		return recuperationTimeMuscleGroup;
	}

	public void setRecuperationTimeMuscleGroup(Integer recuperationTimeMuscleGroup) {
		this.recuperationTimeMuscleGroup = recuperationTimeMuscleGroup;
	}

	public String getRoutineType() {
		return routineType;
	}

	public void setRoutineType(String routineType) {
		this.routineType = routineType;
	}

	public String getRoutineOrder() {
		return routineOrder;
	}

	public void setRoutineOrder(String routineOrder) {
		this.routineOrder = routineOrder;
	}

	public Integer getExercisesPerSession() {
		return exercisesPerSession;
	}

	public void setExercisesPerSession(Integer exercisesPerSession) {
		this.exercisesPerSession = exercisesPerSession;
	}

	public Long getSportPlanId() {
		return sportPlanId;
	}

	public void setSportPlanId(Long sportPlanId) {
		this.sportPlanId = sportPlanId;
	}

	public List<String> getEquipmentFilter() {
		return equipmentFilter;
	}

	public void setEquipmentFilter(List<String> equipmentFilter) {
		this.equipmentFilter = equipmentFilter;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}		
	
	public String getSportTrainerName() {
		return sportTrainerName;
	}

	public void setSportTrainerName(String sportTrainerName) {
		this.sportTrainerName = sportTrainerName;
	}

	public DTOSportPlan getSportPlan(SportPlan sp){
		DTOSportPlan dto = new DTOSportPlan();
		if(sp != null){
			if(sp.getId() != null) { dto.setSportPlanId(sp.getId()); }
			
			if(sp.getName() != null) { dto.setName(sp.getName()); }
			
			if(sp.getCustomer() != null) { dto.setCustomerId(sp.getCustomer().getId()); }
			
			if(sp.getSportTrainer() != null) { dto.setSportTrainerId(sp.getSportTrainer().getId()); dto.setSportTrainerName(sp.getSportTrainer().getName() + " " + sp.getSportTrainer().getSurName()); }
			
			if(sp.getLevel() != null) { dto.setLevel(sp.getLevel().getLevel()); }
			
			if(sp.getObjetive() != null) { dto.setObjetive(sp.getObjetive().getObjetive()); }
			
			if(sp.getPhysicalQuality() != null) { dto.setPhysicalQuality(sp.getPhysicalQuality().getQuality()); }
			
			if(sp.getDuration() != null) { dto.setPeriods(sp.getDuration().getPeriods()); }
			
			if(sp.getDuration() != null) { dto.setWeeks(sp.getDuration().getWeeks());}
			
			if(sp.getDuration() != null) { dto.setSessionsPerWeek(sp.getDuration().getSessionsPerWeek()); }
			
			if(sp.getRoutineParams() != null) { dto.setRoutineType(sp.getRoutineParams().getRoutineType().getType()); dto.setExercisesPerSession(sp.getRoutineParams().getExercisesPerSession()); }
			
			if(sp.getRoutineParams() != null) { dto.setRoutineOrder(sp.getRoutineParams().getRoutineOrder().getRoutineOrder()); }														
			
			if(sp.getDuration() != null){
				List<String> daysOfWeek = new ArrayList<String>();
				for(DayOfWeek day : sp.getDuration().getDays()){
					daysOfWeek.add(day.getDay());
				}
				dto.setDaysOfWeek(daysOfWeek);
			}
			if(sp.getEquipmentFilter() != null){
				List<String> equipment = new ArrayList<String>();
				
				for(Equipment equip : sp.getEquipmentFilter()){
					equipment.add(equip.getEquipment());
				}
				dto.setEquipmentFilter(equipment);
			}
		}		
		return dto;
	}
}