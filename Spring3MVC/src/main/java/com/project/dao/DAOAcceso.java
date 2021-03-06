package com.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class DAOAcceso {
	
	@Autowired
	@Qualifier("daoComun")
	protected DAOComun daoComun;

	@Autowired
	protected DAOSecurity daoSecurity;
	
	@Autowired
	protected DAOUser daoUser;
	
	@Autowired
	protected DAOSportCentre daoSportCentre;
	
	@Autowired
	protected DAOSportTrainer daoSportTrainer;
	
	@Autowired
	protected DAOCustomer daoCustomer;
	
	@Autowired
	protected DAOMuscle daoMuscle;
	
	@Autowired
	protected DAOSportPlan daoSportPlan;
}
