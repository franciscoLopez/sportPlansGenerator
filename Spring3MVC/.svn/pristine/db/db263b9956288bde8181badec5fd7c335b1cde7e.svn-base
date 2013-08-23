package com.project.model;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.SPORT_TRAINER)
@Inheritance(strategy = InheritanceType.JOINED)
public class SportTrainer extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	// Constantes para referencias externas a atributos con hibernate.
	public static final String A_NAME = "name";
	public static final String A_SURNAME = "surName";
	public static final String A_CONTACT_INFO = "contactInfo";

	// Constantes para definir nombres de columnas
	private static final String C_NAME = "name";
	private static final String C_SURNAME = "surname";

	@Column(name = C_NAME, nullable = false)
	private String name;

	@Column(name = C_SURNAME)
	private String surName;

	@OneToOne
	@JoinColumn(name = "contactInfo_id",nullable = true)
	@Cascade(CascadeType.SAVE_UPDATE)
	private ContactInfo contactInfo;

	@ManyToOne
	private SportCentre sportCenter;
	
	@OneToMany(mappedBy = "sportTrainer")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<SportPlan> sportPlan;

	public SportTrainer() {
		super();
	}	

	public List<SportPlan> getSportPlan() {
		return sportPlan;
	}



	public void setSportPlan(List<SportPlan> sportPlan) {
		this.sportPlan = sportPlan;
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

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public SportCentre getSportCenter() {
		return sportCenter;
	}

	public void setSportCenter(SportCentre sportCenter) {
		this.sportCenter = sportCenter;
	}
	
	public void modifySportTrainer(ContactInfo c){
		if(c != null){
			if(this.getContactInfo() != null){
				if(c.getMobileNumber() != null && !c.getMobileNumber().equals("")){
					this.getContactInfo().setMobileNumber(c.getMobileNumber());
				}
				if(c.getPhoneNumber() != null && !c.getPhoneNumber().equals("")){
					this.getContactInfo().setPhoneNumber(c.getPhoneNumber());
				}
				if(this.getContactInfo().getAddress() != null){
					if(c.getAddress().getCity() != null && !c.getAddress().getCity().equals("")){
						this.getContactInfo().getAddress().setCity(c.getAddress().getCity());
					}
					if(c.getAddress().getCountry() != null && !c.getAddress().getCountry().equals("")){
						this.getContactInfo().getAddress().setCountry(c.getAddress().getCountry());
					}
					if(c.getAddress().getPostalCode() != null && !c.getAddress().getPostalCode().equals("")){
						this.getContactInfo().getAddress().setPostalCode(c.getAddress().getPostalCode());
					}
					if(c.getAddress().getAddress() != null && !c.getAddress().getAddress().equals("")){
						this.getContactInfo().getAddress().setAddress(c.getAddress().getAddress());
					}
				}else{
					this.getContactInfo().setAddress(c.getAddress());
				}
				
			}else{
				this.setContactInfo(c);
			}
			
		}
	}
}
