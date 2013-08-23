package com.project.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.SPORTPLAN)
@Inheritance(strategy = InheritanceType.JOINED)
public class SportPlan extends BaseEntity implements Serializable{

	private static final long serialVersionUID 		= 1L;
	
	// Constantes para referencias externas a atributos con hibernate.
	public static final String SP_CUSTOMER 			= "customer";
	public static final String SP_SPORT_TRAINER		= "sportTrainer";
	
	private Date insertDate;
	
	private Date modifyDate;
	
	private Date startDate;
	
	@Column(name = "name")
	private String name;

	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private SportTrainer sportTrainer;
	
	@ManyToOne
	private Objetive objetive;
	
	@ManyToOne
	private CustomerLevel level;
	
	@ManyToOne
	private PhysicalQuality physicalQuality;
	
	@OneToOne
	@JoinColumn(name = "duration_ID")
	private Duration duration;
	
	@OneToOne
	@JoinColumn(name = "routine_ID")
	private RoutineParams routineParams;
	
	@ManyToMany
	private List<Equipment> equipmentFilter;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Plan plan;
	
	private String monitorTips;
	
	@ManyToMany
	private List<ExerciseType> exerciseTypes;
	
	@ManyToMany
	private List<Periodization> periodization;
	
	@Column
	private Boolean defined;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public SportTrainer getSportTrainer() {
		return sportTrainer;
	}

	public void setSportTrainer(SportTrainer sportTrainer) {
		this.sportTrainer = sportTrainer;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Objetive getObjetive() {
		return objetive;
	}

	public void setObjetive(Objetive objetive) {
		this.objetive = objetive;
	}

	public PhysicalQuality getPhysicalQuality() {
		return physicalQuality;
	}

	public void setPhysicalQuality(PhysicalQuality physicalQuality) {
		this.physicalQuality = physicalQuality;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public CustomerLevel getLevel() {
		return level;
	}

	public void setLevel(CustomerLevel level) {
		this.level = level;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Boolean getDefined() {
		return defined;
	}

	public void setDefined(Boolean defined) {
		this.defined = defined;
	}

	public RoutineParams getRoutineParams() {
		return routineParams;
	}

	public void setRoutineParams(RoutineParams routineParams) {
		this.routineParams = routineParams;
	}

	public List<Equipment> getEquipmentFilter() {
		return equipmentFilter;
	}

	public void setEquipmentFilter(List<Equipment> equipmentFilter) {
		this.equipmentFilter = equipmentFilter;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public String getMonitorTips() {
		return monitorTips;
	}

	public void setMonitorTips(String monitorTips) {
		this.monitorTips = monitorTips;
	}

	public List<ExerciseType> getExerciseTypes() {
		return exerciseTypes;
	}

	public void setExerciseTypes(List<ExerciseType> exerciseTypes) {
		this.exerciseTypes = exerciseTypes;
	}

	public List<Periodization> getPeriodization() {
		return periodization;
	}

	public void setPeriodization(List<Periodization> periodization) {
		this.periodization = periodization;
	}

}