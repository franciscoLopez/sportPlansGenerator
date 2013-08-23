package com.project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.DURATION)
@Inheritance(strategy = InheritanceType.JOINED)
public class Duration extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// Constantes para referencias externas a atributos con hibernate.
	public static final String D_PERIODS 	= "periods";
	
	private Integer periods;
	
	private Integer weeks;

	private Integer sessionsPerWeek;
	
	@ManyToMany
	private List<DayOfWeek> days;
	
	// expresado en horas
	private Integer recuperationTime;
	
	// expresado en horas
	private Integer recuperationTimeMuscleGroup;
	
		
	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
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

	public List<DayOfWeek> getDays() {
		return days;
	}

	public void setDays(List<DayOfWeek> days) {
		this.days = days;
	}

	public Integer getRecuperationTime() {
		return recuperationTime;
	}

	public void setRecuperationTime(Integer recuperationTime) {
		this.recuperationTime = recuperationTime;
	}

	public Integer getRecuperationTimeMuscularGroup() {
		return recuperationTimeMuscleGroup;
	}

	public void setRecuperationTimeMuscularGroup(
			Integer recuperationTimeMuscularGroup) {
		this.recuperationTimeMuscleGroup = recuperationTimeMuscularGroup;
	}	
}