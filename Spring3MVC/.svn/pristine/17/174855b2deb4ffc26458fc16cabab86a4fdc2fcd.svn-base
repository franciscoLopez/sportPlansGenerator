package com.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.PHYSICAL_QUALITY)
@Inheritance(strategy = InheritanceType.JOINED)
public class PhysicalQuality extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Constantes para referencias externas a atributos con hibernate.
	public static final String PQ_QUALITY = "quality";
	
	// CONSTANTES
	public static final String PQ_FUERZA	 	= "Fuerza";
	public static final String PQ_RESISTENCIA	= "Resistencia";
	public static final String PQ_FLEXIBILIDAD	= "Flexibilidad";
	
	@Column(name = PQ_QUALITY)
	private String quality;

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}
}
