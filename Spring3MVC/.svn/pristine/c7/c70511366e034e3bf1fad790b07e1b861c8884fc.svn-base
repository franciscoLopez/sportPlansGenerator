package com.project.dto;

import java.text.ParseException;

import com.project.model.SportTrainer;

public class DTOSportTrainer {
	
	private Long id;

	private String name;
	
	private String surName;
	
	private String mail;
	
	private String phoneNumber;
	
	private String mobileNumber;
	
	private String city;
	
	private String country;
	
	private String postalCode;
	
	private String address;
	
	public DTOSportTrainer(){
		super();
	}
	
	public DTOSportTrainer(SportTrainer sp) throws ParseException{
		this.name = sp.getName();		
		this.surName = sp.getSurName();
		this.mail = sp.getEmail();
		if(sp.getContactInfo() != null){
			this.phoneNumber = sp.getContactInfo().getPhoneNumber();
			this.mobileNumber = sp.getContactInfo().getMobileNumber();
			if(sp.getContactInfo().getAddress() != null){
				this.city = sp.getContactInfo().getAddress().getCity();
				this.country = sp.getContactInfo().getAddress().getCountry();
				this.postalCode = sp.getContactInfo().getAddress().getPostalCode();
				this.address = sp.getContactInfo().getAddress().getAddress();
			}
		}
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

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
	
}
