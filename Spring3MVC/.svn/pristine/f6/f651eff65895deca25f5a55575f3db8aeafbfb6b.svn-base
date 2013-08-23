package com.project.servicios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.dao.DAOAcceso;
import com.project.model.SportCentre;
import com.project.model.SportTrainer;

public class ValidarInfoSportCentre extends DAOAcceso implements Validator{

	public boolean supports(Class<?> clazz) {
		return SportCentre.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		SportCentre sportCentre = (SportCentre) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"etiqueta.error.name.is.required", "Error en name");
		Pattern pattern = Pattern.compile("\\d{9}");
		if (sportCentre.getContactInfo().getPhoneNumber() != null && !sportCentre.getContactInfo().getPhoneNumber().equals("")) {
			Matcher matcher = pattern.matcher(sportCentre.getContactInfo()
					.getPhoneNumber());
			if (!matcher.matches()) {
				errors.rejectValue(SportTrainer.A_CONTACT_INFO,
						"etiqueta.error.phone.number.is.required");
			}
		}
		if (sportCentre.getContactInfo().getMobileNumber() != null && !sportCentre.getContactInfo().getMobileNumber().equals("")) {
			Matcher matcher2 = pattern.matcher(sportCentre.getContactInfo()
					.getMobileNumber());
			if (!matcher2.matches()) {
				errors.rejectValue(SportTrainer.A_CONTACT_INFO,
						"etiqueta.error.mobile.number.is.required");
			}
		}

		Pattern pattern2 = Pattern.compile("\\d{5}");
		if (sportCentre.getContactInfo().getAddress().getPostalCode() != null && !sportCentre.getContactInfo().getAddress().getPostalCode().equals("")) {
			Matcher matcher3 = pattern2.matcher(sportCentre.getContactInfo()
					.getAddress().getPostalCode());
			if (!matcher3.matches()) {
				errors.rejectValue(SportTrainer.A_CONTACT_INFO,
						"etiqueta.error.postal.code.is.required");
			}
		}
		
	}

}
