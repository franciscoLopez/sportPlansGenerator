package com.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.SPEED_EXECUTION)
@Inheritance(strategy = InheritanceType.JOINED)
public class SpeedExecution extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Constantes para referencias externas a atributos con hibernate.
	public static final String SE_SPEED_EXECUTION    	= "speedExecution";

	// CONSTANTES
	public static final String SE_LENTA 		= "Lenta";
	public static final String SE_MODERADA		= "Moderada";
	
	private String speedExecution;

	public String getSpeedExecution() {
		return speedExecution;
	}

	public void setSpeedExecution(String speedExecution) {
		this.speedExecution = speedExecution;
	}
}