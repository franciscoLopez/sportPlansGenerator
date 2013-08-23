package com.project.enumerados;

import java.io.Serializable;

public enum TipoRol implements Serializable{

	administrador,gestorAvanzado,gestor; 

	public String toString(){
		switch(this){
		case administrador:
			return "Administrator";
		case gestorAvanzado:
			return "Gestor Avanzado";
		case gestor:
			return "Gestor";
		}
		return null;
	}
	
	public String getRol() {
        return "ROLE_" + getTextoGrupo();
    }
	
	public Integer getNumOrden(){
		return this.ordinal(); 
	}
	
	public String getTexto(){
		switch(this){
		case administrador:
			return "Administrator";
		case gestorAvanzado:
			return "Gestor Avanzado";
		case gestor:
			return "Gestor";
		}
		return null;
	}
	
	public String getTextoGrupo(){
		switch(this){
		case administrador:
			return "Administrador";
		case gestorAvanzado:
			return "Gestor Avanzado";
		case gestor:
			return "Gestor";
		}
		return null;
	}
}
