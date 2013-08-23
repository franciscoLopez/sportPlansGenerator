package com.project.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.project.util.Tabla;

@Entity
@Table(name = Tabla.SPORT_CENTRE)
@Inheritance(strategy = InheritanceType.JOINED)
public class SportCentre extends User {

	private static final long serialVersionUID = 1L;

	// Constantes para referencias externas a atributos con hibernate.
	public static final String A_NAME = "name";


	private String name;

	@OneToOne
	@JoinColumn(name = "contactInfo_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	private ContactInfo contactInfo;

	@OneToMany(mappedBy = "sportCenter")
	private List<SportTrainer> sportTrainers;

	@OneToMany(mappedBy = "customerSportCenter")
	private List<Customer> customers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public void addSportTrainer(SportTrainer sportTrainer) {
		this.getSportTrainers().add(sportTrainer);
	}

	public List<SportTrainer> getSportTrainers() {
		return sportTrainers;
	}

	public void setSportTrainers(List<SportTrainer> sportTrainers) {
		this.sportTrainers = sportTrainers;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public void addCustomer(Customer customer) {
		if (!this.getCustomers().contains(customer)) {
			this.getCustomers().add(customer);
		}
	}

	public void modifySportTrainer(ContactInfo contactInfo2) {
		if(contactInfo2 != null){
			if(this.getContactInfo() != null){
				if(contactInfo2.getMobileNumber() != null && !contactInfo2.getMobileNumber().equals("")){
					this.getContactInfo().setMobileNumber(contactInfo2.getMobileNumber());
				}
				if(contactInfo2.getPhoneNumber() != null && !contactInfo2.getPhoneNumber().equals("")){
					this.getContactInfo().setPhoneNumber(contactInfo2.getPhoneNumber());
				}
				if(this.getContactInfo().getAddress() != null){
					if(contactInfo2.getAddress().getCity() != null && !contactInfo2.getAddress().getCity().equals("")){
						this.getContactInfo().getAddress().setCity(contactInfo2.getAddress().getCity());
					}
					if(contactInfo2.getAddress().getCountry() != null && !contactInfo2.getAddress().getCountry().equals("")){
						this.getContactInfo().getAddress().setCountry(contactInfo2.getAddress().getCountry());
					}
					if(contactInfo2.getAddress().getPostalCode() != null && !contactInfo2.getAddress().getPostalCode().equals("")){
						this.getContactInfo().getAddress().setPostalCode(contactInfo2.getAddress().getPostalCode());
					}
					if(contactInfo2.getAddress().getAddress() != null && !contactInfo2.getAddress().getAddress().equals("")){
						this.getContactInfo().getAddress().setAddress(contactInfo2.getAddress().getAddress());
					}
				}else{
					this.getContactInfo().setAddress(contactInfo2.getAddress());
				}
				
			}else{
				this.setContactInfo(contactInfo2);
			}
			
		}		
	}
}
