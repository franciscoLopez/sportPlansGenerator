package com.project.servicios;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.dao.DAOAcceso;
import com.project.dto.DTOExercise;
import com.project.dto.DTOExerciseModify;

public class ValidarDTOExercise extends DAOAcceso implements Validator{

	public boolean supports(Class<?> clazz) {
		return DTOExercise.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		DTOExerciseModify dto = (DTOExerciseModify)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "etiqueta.error.name.is.required", "Error en nombre");
	}

}
