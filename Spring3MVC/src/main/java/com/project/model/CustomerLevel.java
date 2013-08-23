package com.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.CUSTOMER_LEVEL)
@Inheritance(strategy = InheritanceType.JOINED)
public class CustomerLevel extends BaseEntity implements Serializable{
	
	// LEVELS
	public static final String C_L_ACLIMATACION 	= "Aclimataci�n";
	public static final String C_L_PRINCIPIANTE 	= "Principiante";
	public static final String C_L_INTERMEDIO 		= "Intermedio";
	public static final String C_L_AVANZADO		 	= "Avanzado";
	
	public static final String A_LEVEL 				= "level";

	private static final long serialVersionUID = 1L;
	
	@Column(name="level",nullable = false)
	private String level;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
