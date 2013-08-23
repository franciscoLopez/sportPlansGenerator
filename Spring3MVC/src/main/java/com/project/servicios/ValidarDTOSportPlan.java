package com.project.servicios;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.dao.DAOAcceso;
import com.project.dto.DTOSportPlan;

public class ValidarDTOSportPlan extends DAOAcceso implements Validator{

	public boolean supports(Class<?> clazz) {
		return DTOSportPlan.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		DTOSportPlan dto = (DTOSportPlan)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "etiqueta.error.name.is.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "objetive", "etiqueta.error.objetive");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "physicalQuality", "etiqueta.error.physicalQuality");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sessionsPerWeek", "etiqueta.error.sessionsPerWeek");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "daysOfWeek", "etiqueta.error.daysOfWeek");
		Integer size = 0;		
		for(String s : dto.getDaysOfWeek()){
			if(s != null && !s.equals("") ){
				size++;
			}
		}
		if(size != dto.getSessionsPerWeek()){
			errors.rejectValue("daysOfWeek", "etiqueta.error.daysOfWeek");
		}
	}

}
