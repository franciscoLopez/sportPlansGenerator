package com.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.ROUTINE_TYPE)
@Inheritance(strategy = InheritanceType.JOINED)
public class RoutineType extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Constantes para referencias externas a atributos con hibernate.
	public static final String RT_TYPE 						= "type";
	
	// ROUTINE TYPES
	public static final String R_TYPE_GLOBAL				= "Global";
	public static final String R_TYPE_POR_HEMISFERIOS 		= "Por hemisferios";
	public static final String R_TYPE_POR_GRUPO_MUSCULAR 	= "Por grupo muscular";

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
}