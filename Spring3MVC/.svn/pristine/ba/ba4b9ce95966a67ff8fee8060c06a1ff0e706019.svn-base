package com.project.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.model.Equipment;
import com.project.model.Exercise;
import com.project.model.ExerciseType;
import com.project.model.MuscleType;

public class DTOExerciseModify {
	
	private String name;
	
	private Long id;
	
	private DTOMuscleAndType principalMuscle;
	
	private List<DTOMuscleAndType> secundaryMuscles;
	
	private String src;
	
	private String exerciseType;
	
	private String[] exerciseTypeDefault = {ExerciseType.ET_POLIARTICULAR,
										   ExerciseType.ET_MONOARTICULAR};
	
	private String equipment;
	
	private String[] equipmentDefault = {Equipment.E_BOSU,
										 Equipment.E_FITBALL,
										 Equipment.E_GOMAS,
										 Equipment.E_MAQUINAS,
										 Equipment.E_PESO_LIBRE,
										 Equipment.E_POLEAS,
										 Equipment.E_SIN_MATERIAL,
										 Equipment.E_BALON_MEDICINAL,
										 Equipment.E_BANCO};
	
	private Long ownerId;
	
	private String comment;
	
	public DTOExerciseModify getDTO(Exercise e){
		DTOExerciseModify dto = new DTOExerciseModify();
		if(e.getId() != null){
			dto.setId(e.getId());
		}
		if(e.getName() != null && !e.getName().equals("")){
			dto.setName(e.getName());
		}
		if(e.getImage().getSrc() != null && !e.getImage().getSrc().equals("")){
			dto.setSrc(e.getImage().getSrc());
		}
		if(e.getPrincipalMuscleType() != null && !e.getPrincipalMuscleType().getType().equals("")){
			DTOMuscleAndType dtoMAT = new DTOMuscleAndType();
			dtoMAT.setMuscle(e.getPrincipalMuscleType().getMuscle().getMuscle());
			dtoMAT.setType(e.getPrincipalMuscleType().getType());			
			dto.setPrincipalMuscle(dtoMAT);
		}
		if(e.getSecundaryMuscleType()!= null && e.getSecundaryMuscleType().size() > 0){
			List<DTOMuscleAndType> sec = new ArrayList<DTOMuscleAndType>();
			for(MuscleType m : e.getSecundaryMuscleType()){
				DTOMuscleAndType dtoMAT = new DTOMuscleAndType();
				dtoMAT.setMuscle(m.getMuscle().getMuscle());
				dtoMAT.setType(m.getType());
				sec.add(dtoMAT);
			}
			dto.setSecundaryMuscles(sec);
		}
		if(e.getExerciseType() != null && !e.getExerciseType().getExerciseType().equals("")){
			dto.setExerciseType(e.getExerciseType().getExerciseType());
		}
		if(e.getEquipment() != null && !e.getEquipment().getEquipment().equals("")){
			dto.setEquipment(e.getEquipment().getEquipment());
		}
		if(e.getComment() != null && !e.getComment().equals("")){
			dto.setComment(e.getComment());
		}
		return dto;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DTOMuscleAndType getPrincipalMuscle() {
		return principalMuscle;
	}

	public void setPrincipalMuscle(DTOMuscleAndType principalMuscle) {
		this.principalMuscle = principalMuscle;
	}

	public List<DTOMuscleAndType> getSecundaryMuscles() {
		return secundaryMuscles;
	}

	public void setSecundaryMuscles(List<DTOMuscleAndType> secundaryMuscles) {
		this.secundaryMuscles = secundaryMuscles;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}

	public String[] getExerciseTypeDefault() {
		return exerciseTypeDefault;
	}

	public void setExerciseTypeDefault(String[] exerciseTypeDefault) {
		this.exerciseTypeDefault = exerciseTypeDefault;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String[] getEquipmentDefault() {
		return equipmentDefault;
	}

	public void setEquipmentDefault(String[] equipmentDefault) {
		this.equipmentDefault = equipmentDefault;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}	
}
