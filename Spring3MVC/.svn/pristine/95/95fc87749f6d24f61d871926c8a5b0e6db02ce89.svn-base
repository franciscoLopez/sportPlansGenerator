package com.project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.MUSCLE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Muscle extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Constantes para referencias externas a atributos con hibernate.
	public static final String A_MUSCLE = "muscle";
	public static final String A_MUSCLE_TYPE = "muscleType";
	
	// Constantes para definir nombres de columnas
	private static final String C_MUSCLE = "muscle";
	
	// Constantes
	public static final String	M_ABDOMINALES	= "Abdominales";
	public static final String	M_ANTEBRAZOS	= "Antebrazos";
	public static final String	M_BICEPS		= "Biceps";
	public static final String	M_ESPALDA		= "Espalda";
	public static final String	M_ESTIRAMIENTOS	= "Estiramientos";
	public static final String	M_EXPLOSIVOS	= "Explosivos";
	public static final String	M_DELTOIDES		= "Deltoides";
	public static final String	M_LUMBARES		= "Lumbares";
	public static final String	M_PECHO			= "Pectoral";
	public static final String	M_PIERNAS		= "Piernas";
	public static final String	M_TRICEPS		= "Triceps";
	public static final String	M_TRAPECIO		= "Trapecio";

	@Column(name = C_MUSCLE)
	private String muscle;
	
	@OneToMany(mappedBy = Muscle.C_MUSCLE)
	private List<MuscleType> muscleType;
	
	@Transient
	public static String[] muscles = {M_ABDOMINALES,M_ANTEBRAZOS,M_BICEPS,M_ESPALDA,M_EXPLOSIVOS,M_DELTOIDES,M_LUMBARES,M_PECHO,M_PIERNAS,M_TRICEPS,M_TRAPECIO};

	public String getMuscle() {
		return muscle;
	}

	public void setMuscle(String muscle) {
		this.muscle = muscle;
	}

	public List<MuscleType> getMuscleType() {
		return muscleType;
	}

	public void setMuscleType(List<MuscleType> muscleType) {
		this.muscleType = muscleType;
	}

	public String[] getMuscles() {
		return muscles;
	}

	public void setMuscles(String[] muscles) {
		this.muscles = muscles;
	}
}
