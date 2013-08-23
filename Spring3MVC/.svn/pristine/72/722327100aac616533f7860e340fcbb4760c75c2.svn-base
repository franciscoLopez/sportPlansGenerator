package com.project.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.project.util.Tabla;

@Entity
@Table(name=Tabla.CONTACT_INFO)
public class ContactInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String phoneNumber;
	
	private String mobileNumber;
	
	@OneToOne
	@JoinColumn(name="address_id",nullable = true)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Address address;


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	public boolean isEmpty(){
		boolean res = true;
		if(!getMobileNumber().equals("") || !getPhoneNumber().equals("") || !getAddress().isEmpty()){
			res = false;
		}
		return res;
	}
}
