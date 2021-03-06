package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.DAOAcceso;
import com.project.servicios.ValidarCustomer;
import com.project.servicios.ValidarDTOExercise;
import com.project.servicios.ValidarDTOProfile;
import com.project.servicios.ValidarDTOSportPlan;
import com.project.servicios.ValidarInfoCustomer;
import com.project.servicios.ValidarInfoSportCentre;
import com.project.servicios.ValidarInfoSportTrainer;
import com.project.servicios.ValidarPassword;
import com.project.servicios.ValidarSportCentre;
import com.project.servicios.ValidarSportTrainer;

public class GenericController extends DAOAcceso {
	
	@Autowired
	protected ValidarSportCentre	validarSportCentre;
	
	@Autowired
	protected ValidarSportTrainer	validarSportTrainer;
	
	@Autowired
	protected ValidarInfoSportTrainer	validarInfoSportTrainer;
	
	@Autowired
	protected ValidarCustomer	validarCustomer;
	
	@Autowired
	protected ValidarInfoCustomer	validarInfoCustomer;
	
	@Autowired
	protected ValidarInfoSportCentre	validarInfoSportCentre;
	
	@Autowired
	protected ValidarPassword	validarPassword;
	
	@Autowired
	protected ValidarDTOExercise	validarDTOExercise;
	
	@Autowired
	protected ValidarDTOProfile	validarDTOProfile;
	
	@Autowired
	protected ValidarDTOSportPlan	validarDTOSportPlan;
}
