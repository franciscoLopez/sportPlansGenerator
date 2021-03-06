package com.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.EQUIPMENT)
@Inheritance(strategy = InheritanceType.JOINED)
public class Equipment extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// Constantes para referencias externas a atributos con hibernate.
	public static final String A_EQUIPMENT = "equipment";

	// CONSTANTES
	public static final String E_SIN_MATERIAL = "Sin material";
	public static final String E_BOSU = "Bosu";
	public static final String E_FITBALL = "Fitball";
	public static final String E_PESO_LIBRE = "Peso libre";
	public static final String E_MAQUINAS = "Maquinas";
	public static final String E_GOMAS = "Gomas";
	public static final String E_POLEAS = "Poleas";
	public static final String E_BANCO = "Banco";
	public static final String E_BALON_MEDICINAL = "Bal�n medicinal";

	private String equipment;

	public static String[] equi = { E_SIN_MATERIAL, E_BOSU, E_FITBALL,
			E_PESO_LIBRE, E_MAQUINAS, E_GOMAS, E_POLEAS };

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
}
