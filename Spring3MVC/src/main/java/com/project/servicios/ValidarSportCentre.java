package com.project.servicios;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.dao.DAOAcceso;
import com.project.model.SportCentre;

public class ValidarSportCentre extends DAOAcceso implements Validator{

	public boolean supports(Class<?> clazz) {
		return SportCentre.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {	
		SportCentre sportCentre = (SportCentre)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "etiqueta.error.username.is.required", "Error en username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "etiqueta.error.password.is.required", "Error en password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repetirPassword", "etiqueta.error.repPassword.is.required", "Error en password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "etiqueta.error.name.is.required", "Error en name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "etiqueta.error.email.is.required", "Error en email");
		String patronEmail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(!sportCentre.getEmail().matches(patronEmail)){
			errors.rejectValue(SportCentre.A_EMAIL,"etiqueta.error.email.is.required");
		}
	}
}
