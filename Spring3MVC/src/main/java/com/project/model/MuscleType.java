package com.project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.MUSCLE_TYPE)
@Inheritance(strategy = InheritanceType.JOINED)
public class MuscleType extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// Constantes para referencias externas a atributos con hibernate.
	public static final String A_TYPE = "type";
	public static final String A_MUSCLE = "muscle";
	public static final String A_PRINCIPAL_EXERCISES = "principalExercises";
	public static final String A_SECUNDARY_EXERCISES = "secundaryExercises";

	// Constantes para definir nombres de columnas
	private static final String C_TYPE = "type";
	private static final String C_MUSCLE = "muscle";

	@Column(name = C_TYPE, nullable = false)
	private String type;

	@ManyToOne
	@JoinColumn(name = C_MUSCLE, nullable = false)
	private Muscle muscle;

	@OneToMany(mappedBy = Exercise.A_PRINCIPAL_MUSCLE)
	// @JoinColumn(name = A_PRINCIPAL_EXERCISES)
	private List<Exercise> principalExercises;

	@ManyToMany(mappedBy = Exercise.A_SECUNDARY_MUSCLE)
	private List<Exercise> secundaryExercises;

	@Transient
	public static String [] abdTypes = {MuscleTypes.A_OBLICUOS,MuscleTypes.A_RECTO_ANTERIOR};
	@Transient
	public static String [] hoTypes = {MuscleTypes.H_ANTERIOR,MuscleTypes.H_POSTERIOR,MuscleTypes.H_MEDIO};
	@Transient
	public static String [] peTypes = {MuscleTypes.P_SUPERIOR,MuscleTypes.P_MEDIO,MuscleTypes.P_INFERIOR};
	@Transient
	public static String [] piTypes = {MuscleTypes.PI_CUADRICEPS,MuscleTypes.PI_ISQUITIBIALES,MuscleTypes.PI_GLUTEOS,MuscleTypes.PI_GEMELOS,MuscleTypes.PI_SOLEO,MuscleTypes.PI_ABDUCTORES};
	@Transient
	public static String [] triTypes = {MuscleTypes.T_VASTO_EXTERNO,MuscleTypes.T_VASTO_INTERNO};
	@Transient
	public static String [] esTypes = {MuscleTypes.E_DELTOIDES,
									   MuscleTypes.E_PECTORALES,
									   MuscleTypes.E_SOLEO,
									   MuscleTypes.E_ISQUIOTIBIALES,
									   MuscleTypes.E_ISQUIOTIBIALES_EN_BANCO,
									   MuscleTypes.E_BICEPS,
									   MuscleTypes.E_TRICEPS,
									   MuscleTypes.E_GLUTEOS,
									   MuscleTypes.E_GLUTEOS_EN_PIE,
									   MuscleTypes.E_GEMELOS,
									   MuscleTypes.E_CUADRICEPS};
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Muscle getMuscle() {
		return muscle;
	}

	public void setMuscle(Muscle muscle) {
		this.muscle = muscle;
	}

	public List<Exercise> getPrincipalExercises() {
		return principalExercises;
	}

	public void setPrincipalExercises(List<Exercise> principalExercises) {
		this.principalExercises = principalExercises;
	}

	public List<Exercise> getSecundaryExercises() {
		return secundaryExercises;
	}

	public void setSecundaryExercises(List<Exercise> secundaryExercises) {
		this.secundaryExercises = secundaryExercises;
	}

}
