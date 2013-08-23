package com.project.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.project.util.Tabla;

@Entity
@Table(name=Tabla.ADDRESS)
public class Address extends BaseEntity{

	private static final long serialVersionUID = 1L;	

	private String city;
	
	private String country;
	
	private String postalCode;
	
	private String address;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isEmpty(){
		boolean res = true;
		if(!this.getCity().equals("") || !this.getCountry().equals("") || !this.getPostalCode().equals("") || !this.getAddress().equals("")){
			res = false;
		}
		return res;
	}
}
