package com.project.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.criterion.Order;

@MappedSuperclass
public class BaseEntity implements Serializable, Cloneable{
	
	public static final String A_ID = "id";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	public BaseEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long val) {
		this.id = val;
	}

	public boolean isNew() {
		return id == null;
	}

	public int hashCode() {
		if (id != null)
			return id.hashCode();
		else
			return 0;
	}

	public boolean equals(Object obj) {
		try {
			if (((BaseEntity) obj).getId().equals(this.id))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar: " + ex);
		}
		return obj;
	}

	public Order getOrden() {
		return null;
	}
}
