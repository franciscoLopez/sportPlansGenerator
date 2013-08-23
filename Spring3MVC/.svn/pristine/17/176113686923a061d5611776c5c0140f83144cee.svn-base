package com.project.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.IMAGE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Image extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Constantes para referencias externas a atributos con hibernate.
	public static final String A_SRC = "src";
	public static final String A_F_UPLOADED = "f_uploaded";
	public static final String A_F_MODIFIED = "f_modified";
	
	// Constantes para definir nombres de columnas
	private static final String C_SRC = "src";
	private static final String C_F_UPLOADED = "f_uploaded";
	private static final String C_F_MODIFIED = "f_modified";

	@Column(name = C_SRC, nullable = false)
	private String src;
	
	@Column(name = C_F_UPLOADED, nullable = false)
	private Calendar f_uploaded;
	
	@Column(name = C_F_MODIFIED, nullable = false)
	private Calendar f_modified;

	public Calendar getF_uploaded() {
		return f_uploaded;
	}

	public void setF_uploaded(Calendar f_uploaded) {
		this.f_uploaded = f_uploaded;
	}

	public Calendar getF_modified() {
		return f_modified;
	}

	public void setF_modified(Calendar f_modified) {
		this.f_modified = f_modified;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
}