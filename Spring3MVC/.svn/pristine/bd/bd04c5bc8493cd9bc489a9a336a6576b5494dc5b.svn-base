package com.project.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.SESSION)
@Inheritance(strategy = InheritanceType.JOINED)
public class Session extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private DayOfWeek dayOfWeek;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="exercises_session", joinColumns = @JoinColumn(name = "session_ID"),
				inverseJoinColumns = @JoinColumn(name = "exercise_block_ID"))
	private Set<ExerciseBlock> exercises;
	
	@OneToMany
	@JoinTable(name="elongation_session", joinColumns = @JoinColumn(name = "session_ID"),
				inverseJoinColumns = @JoinColumn(name = "exercise_block_ID"))
	private List<ExerciseBlock> elongations;
	
	@ManyToOne
	private Intensity intensity;
	
	private Integer restTime;

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Set<ExerciseBlock> getExercises() {
		return exercises;
	}

	public void setExercises(Set<ExerciseBlock> exercises) {
		this.exercises = exercises;
	}

	public List<ExerciseBlock> getElongations() {
		return elongations;
	}

	public void setElongations(List<ExerciseBlock> elongations) {
		this.elongations = elongations;
	}

	public Intensity getIntensity() {
		return intensity;
	}

	public void setIntensity(Intensity intensity) {
		this.intensity = intensity;
	}

	public Integer getRestTime() {
		return restTime;
	}

	public void setRestTime(Integer restTime) {
		this.restTime = restTime;
	}
}