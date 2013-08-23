package com.project.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.EXERCISE_BLOCK)
@Inheritance(strategy = InheritanceType.JOINED)
public class ExerciseBlock extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Constantes para referencias externas a atributos con hibernate.
	public static final String EB_EXERCISE 			= "exercise";
	public static final String EB_NUM_SERIES		= "numSeries";
	public static final String EB_INTENSITY			= "intensity";
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Exercise exercise;
	
	private Integer numSeries;

	public Integer getNumSeries() {
		return numSeries;
	}

	public void setNumSeries(Integer numSeries) {
		this.numSeries = numSeries;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
}