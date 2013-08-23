package com.project.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.EXERCISE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Exercise extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// Constantes para referencias externas a atributos con hibernate.
	public static final String RUTA = "images/storage/exercises/";

	// Constantes para referencias externas a atributos con hibernate.
	public static final String A_NAME 					= "name";
	public static final String A_IMAGE 					= "image";
	public static final String A_PRINCIPAL_MUSCLE 		= "principalMuscleType";
	public static final String A_SECUNDARY_MUSCLE 		= "secundaryMuscleType";
	public static final String A_EXERCISE_TYPE			= "exerciseType";
	public static final String A_EQUIPMENT				= "equipment";
	public static final String A_OWNER					= "owner";

	// Constantes para definir nombres de columnas
	private static final String C_NAME = "name";
	private static final String C_IMAGE = "image_id";
	private static final String C_F_CREATED = "f_created";
	private static final String C_F_MODIFIED = "f_modified";
	private static final String C_COMMENT = "comment";

	@Column(name = C_NAME, nullable = false)
	private String name;

	@ManyToOne
	private MuscleType principalMuscleType;
	
	@ManyToMany
	@JoinTable(name="secundary_exercises",
	joinColumns={@JoinColumn(name="exercise_id")}, inverseJoinColumns={@JoinColumn(name="muscle_type_id")})
	private List<MuscleType> secundaryMuscleType;

	@OneToOne
	@JoinColumn(name = C_IMAGE,nullable = true)
	private Image image;
	
	@Column(name = C_F_CREATED, nullable = true)
	private Calendar f_created;
	
	@Column(name = C_F_MODIFIED, nullable = true)
	private Calendar f_modified;
	
	@ManyToOne
	private ExerciseType exerciseType;
	
	@ManyToOne
	private Equipment equipment;
	
	@ManyToOne
	private SportTrainer owner;
	
	@Column(name = C_COMMENT, nullable = true)
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MuscleType getPrincipalMuscleType() {
		return principalMuscleType;
	}

	public void setPrincipalMuscleType(MuscleType principalMuscleType) {
		this.principalMuscleType = principalMuscleType;
	}

	public List<MuscleType> getSecundaryMuscleType() {
		return secundaryMuscleType;
	}

	public void setSecundaryMuscleType(List<MuscleType> secundaryMuscleType) {
		this.secundaryMuscleType = secundaryMuscleType;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Calendar getF_created() {
		return f_created;
	}

	public void setF_created(Calendar f_created) {
		this.f_created = f_created;
	}

	public Calendar getF_modified() {
		return f_modified;
	}

	public void setF_modified(Calendar f_modified) {
		this.f_modified = f_modified;
	}

	public ExerciseType getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(ExerciseType exerciseType) {
		this.exerciseType = exerciseType;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public SportTrainer getOwner() {
		return owner;
	}

	public void setOwner(SportTrainer owner) {
		this.owner = owner;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}	
}
