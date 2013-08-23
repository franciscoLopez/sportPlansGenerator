package com.project.servicios;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.dao.DAOAcceso;
import com.project.model.User;

public class ValidarPassword extends DAOAcceso implements Validator  {

	
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "etiqueta.error.password.is.required", "Error en password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repetirPassword", "etiqueta.error.password.is.required", "Error en password");
		if(!user.getPassword().equals(user.getRepetirPassword())){
			errors.rejectValue("password", "etiqueta.error.password.is.required");
		}
	}

}
