package com.project.servicios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.dao.DAOAcceso;
import com.project.model.Customer;
import com.project.model.SportTrainer;

public class ValidarInfoCustomer extends DAOAcceso implements Validator {

	public boolean supports(Class<?> clazz) {
		return Customer.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Customer customer = (Customer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"etiqueta.error.name.is.required", "Error en name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surName",
				"etiqueta.error.surname.is.required", "Error en apellidos");
		Pattern pattern = Pattern.compile("\\d{9}");
		if (customer.getContactInfo().getPhoneNumber() != null
				&& !customer.getContactInfo().getPhoneNumber().equals("")) {
			Matcher matcher = pattern.matcher(customer.getContactInfo()
					.getPhoneNumber());
			if (!matcher.matches()) {
				errors.rejectValue(SportTrainer.A_CONTACT_INFO,
						"etiqueta.error.phone.number.is.required");
			}
		}
		if (customer.getContactInfo().getMobileNumber() != null
				&& !customer.getContactInfo().getMobileNumber().equals("")) {
			Matcher matcher2 = pattern.matcher(customer.getContactInfo()
					.getMobileNumber());
			if (!matcher2.matches()) {
				errors.rejectValue(SportTrainer.A_CONTACT_INFO,
						"etiqueta.error.mobile.number.is.required");
			}
		}

		Pattern pattern2 = Pattern.compile("\\d{5}");
		if (customer.getContactInfo().getAddress().getPostalCode() != null
				&& !customer.getContactInfo().getAddress().getPostalCode()
						.equals("")) {
			Matcher matcher3 = pattern2.matcher(customer.getContactInfo()
					.getAddress().getPostalCode());
			if (!matcher3.matches()) {
				errors.rejectValue(SportTrainer.A_CONTACT_INFO,
						"etiqueta.error.postal.code.is.required");
			}
		}

	}

}
