package com.project.dto;


import java.util.Date;

import com.project.model.CustomerLevel;

public class DTOProfile {

	private Long idCustomer;
	
	private Date dateBirth;
	
	private Float weight;
	
	private Integer height;
	
	private String laboralActivity;
	
	private String customerLevel;
	
	private String[] customerLevelDefault = {CustomerLevel.C_L_ACLIMATACION,CustomerLevel.C_L_PRINCIPIANTE,CustomerLevel.C_L_INTERMEDIO,CustomerLevel.C_L_AVANZADO};

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getLaboralActivity() {
		return laboralActivity;
	}

	public void setLaboralActivity(String laboralActivity) {
		this.laboralActivity = laboralActivity;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public String[] getCustomerLevelDefault() {
		return customerLevelDefault;
	}

	public void setCustomerLevelDefault(String[] customerLevelDefault) {
		this.customerLevelDefault = customerLevelDefault;
	}
}
