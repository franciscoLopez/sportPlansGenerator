package com.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.OBJETIVE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Objetive extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Constantes para referencias externas a atributos con hibernate.
	public static final String O_OBJETIVE = "objetive";
	
	// CONSTANTES
	public static final String O_MANTENIMIENTO					= "Mantenimiento";
	public static final String O_PERDIDA_PESO 					= "Pérdida de peso";
	public static final String O_ADAPTACION 					= "Adaptación";	
	public static final String O_HIPERTROFIA					= "Hipertrofia";
	public static final String O_SALUD 							= "Salud";
	public static final String O_AUMENTO_DE_LA_FUERZA			= "Aumento de la fuerza";
	
	
	
	// Constantes para definir nombres de columnas
	private static final String C_OBJETIVE = "objetive";
	
	@Column(name = C_OBJETIVE)
	private String objetive;

	public String getObjetive() {
		return objetive;
	}

	public void setObjetive(String objetive) {
		this.objetive = objetive;
	}

}
