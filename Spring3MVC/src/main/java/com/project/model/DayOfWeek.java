package com.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.project.util.Tabla;
@Entity
@Table(name = Tabla.DAY_OF_WEEK)
@Inheritance(strategy = InheritanceType.JOINED)
public class DayOfWeek extends BaseEntity implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	// Constantes para referencias externas a atributos con hibernate.
	public static final String DAY 	   		= "day";
	
	public static final String DAY_MONDAY 		= "Lunes";
	public static final String DAY_TUESDAY 		= "Martes";
	public static final String DAY_WEDNESDAY 	= "Miercoles";
	public static final String DAY_THURSDAY		= "Jueves";
	public static final String DAY_FRIDAY		= "Viernes";
	public static final String DAY_SATURDAY		= "Sábado";
	public static final String DAY_SUNDAY 		= "Domingo";
	
	
	private String day;

	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}	
}