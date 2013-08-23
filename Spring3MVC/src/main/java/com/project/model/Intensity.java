package com.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.INTENSITY)
@Inheritance(strategy = InheritanceType.JOINED)
public class Intensity extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Constantes para referencias externas a atributos con hibernate.
	public static final String I_MIN_INTENSITY 			= "minIntensity";
	public static final String I_MAX_INTENSITY			= "maxIntensity";
	public static final String I_DESCRIPTOR				= "descriptor";
	
	public static final String I_DESC_S_EASY			= "super easy";
	public static final String I_DESC_EASY				= "easy";
	public static final String I_DESC_NORMAL			= "normal";
	public static final String I_DESC_TIRED				= "tired";
	public static final String I_DESC_S_TIRED			= "so tired";
	public static final String I_DESC_HARD				= "hard";
	public static final String I_DESC_S_HARD			= "super hard";
	
	
	// Minimo numero de RM (Repeticiones Maximas)
	private Integer minIntensity;
	
	// Maximo numero de RM (Repeticiones Maximas)
	private Integer maxIntensity;
	
	private String descriptor;


	public Integer getMinIntensity() {
		return minIntensity;
	}


	public void setMinIntensity(Integer minIntensity) {
		this.minIntensity = minIntensity;
	}


	public Integer getMaxIntensity() {
		return maxIntensity;
	}


	public void setMaxIntensity(Integer maxIntensity) {
		this.maxIntensity = maxIntensity;
	}


	public String getDescriptor() {
		return descriptor;
	}


	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}
	
}