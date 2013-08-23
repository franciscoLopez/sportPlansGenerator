package com.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.project.util.Tabla;

@Entity
@Table(name=Tabla.AUTHORITIES)
public class Authority implements Serializable,GrantedAuthority{

	private static final long serialVersionUID = 1L;

	// Constantes para referencias externas a atributos con hibernate.
	public static final String	A_USERNAME				= "username";
	public static final String	A_AUTHORITY				= "authority";
	
	// Constantes para definir nombres de columnas
	private static final String	C_USERNAME				= "username";
	private static final String	C_AUTHORITY				= "authority";

	
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;
	
	@Id
	private String authority;
	
	public Authority(String authority) {
		this.authority = authority;
	}
	
	public Authority() {
	}
	
	public User getUser() {
		if (user == null)
			user = new User();
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String toString(){
		return authority;
	}
	
	public int compareTo(Object authority) {
		return this.authority.compareTo(((GrantedAuthority) authority).getAuthority());
	}

	public String getAuthority() {
		return authority;
	}
	
	public boolean equals(Object obj){
		String a = (String) obj;
		return this.authority.equals(a);
	}
	
	
}
