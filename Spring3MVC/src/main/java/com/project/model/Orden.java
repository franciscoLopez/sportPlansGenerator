package com.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.ORDEN)
@Inheritance(strategy = InheritanceType.JOINED)
public class Orden extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String O_CIRCUITO = "Circuito";
	public static final String O_SERIES = "Series";
	
	private String routineOrder;

	public String getRoutineOrder() {
		return routineOrder;
	}

	public void setRoutineOrder(String routineOrder) {
		this.routineOrder = routineOrder;
	}
}
