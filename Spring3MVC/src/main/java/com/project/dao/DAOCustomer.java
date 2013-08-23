package com.project.dao;

import com.project.dto.DTOProfile;
import com.project.model.Customer;
import com.project.model.CustomerLevel;
import com.project.model.SportCentre;
import com.project.model.SportPlan;

public interface DAOCustomer {

	public void addCustomer(Customer customer,Long idUser);

	public Customer getCustomer(Long userId);

	public void modifyCustomer(Customer infoCustomer);

	public boolean deleteCustomer(Long userId);

	public void addCustomerBySportTrainer(Customer customer, Long id);

	public void modifyProfile(DTOProfile dtoprofile);

	public DTOProfile getDTOProfile(Long userId);

	public boolean hasUndefinedSportPlans(Long userId);

	public Integer getNumSportPlansGenerated(Long id);

	public CustomerLevel getCustomerLevel(Long id);

	public SportCentre getOwner(Long id);

	public SportPlan getSportPlan(Long sportPlanId);
}
