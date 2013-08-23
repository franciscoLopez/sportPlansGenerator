package com.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.DURATION_LIMIT)
@Inheritance(strategy = InheritanceType.JOINED)
public class DurationLimit extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	// Constantes para referencias externas a atributos con hibernate.
	public static final String A_LEVEL 			= "level";


	private Integer periods;
	
	private Integer weeks;
	
	@ManyToOne
	private CustomerLevel level;
	
	private Integer offset;	

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public Integer getWeeks() {
		return weeks;
	}

	public void setWeeks(Integer weeks) {
		this.weeks = weeks;
	}

	public CustomerLevel getLevel() {
		return level;
	}

	public void setLevel(CustomerLevel level) {
		this.level = level;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}	
}