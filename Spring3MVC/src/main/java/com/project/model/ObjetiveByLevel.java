package com.project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.OBJETIVE_BY_LEVEL)
@Inheritance(strategy = InheritanceType.JOINED)
public class ObjetiveByLevel extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "levelId")
	private CustomerLevel level;
	
	@ManyToMany
	@JoinTable(name="objetivesLevel",
	joinColumns={@JoinColumn(name="objetive_level_id")}, inverseJoinColumns={@JoinColumn(name="objetive_id")})
	private List<Objetive> objetives;

	public CustomerLevel getLevel() {
		return level;
	}

	public void setLevel(CustomerLevel level) {
		this.level = level;
	}

	public List<Objetive> getObjetives() {
		return objetives;
	}

	public void setObjetives(List<Objetive> objetives) {
		this.objetives = objetives;
	}
}
