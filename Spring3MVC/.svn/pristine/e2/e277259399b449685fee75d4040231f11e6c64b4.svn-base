package com.project.servicios;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.dao.DAOAcceso;
import com.project.dto.DTOProfile;

public class ValidarDTOProfile extends DAOAcceso implements Validator{

	public boolean supports(Class<?> clazz) {
		return DTOProfile.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		DTOProfile dto = (DTOProfile)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "etiqueta.error.weight", "Error en el peso");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "height", "etiqueta.error.height", "Error en la altura");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "laboralActivity", "etiqueta.error.laboralActivity", "Error en la actividad laboral");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerLevel", "etiqueta.error.customerLevel", "Error en el nivel");
	}

}
