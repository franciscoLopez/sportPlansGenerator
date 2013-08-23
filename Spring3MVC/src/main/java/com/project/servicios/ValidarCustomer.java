package com.project.servicios;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.dao.DAOAcceso;
import com.project.model.Customer;
import com.project.model.SportTrainer;

public class ValidarCustomer extends DAOAcceso implements Validator {

	public boolean supports(Class<?> clazz) {
		return Customer.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		Customer customer = (Customer)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "etiqueta.error.username.is.required", "Error en username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "etiqueta.error.password.is.required", "Error en password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repetirPassword", "etiqueta.error.repPassword.is.required", "Error en password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "etiqueta.error.name.is.required", "Error en nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surName", "etiqueta.error.surname.is.required", "Error en apellidos");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "etiqueta.error.email.is.required", "Error en email");
		String patronEmail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(!customer.getEmail().matches(patronEmail)){
			errors.rejectValue(SportTrainer.A_EMAIL,"etiqueta.error.email.is.required");
		}
	}

}
