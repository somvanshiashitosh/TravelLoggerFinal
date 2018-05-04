package com.diebold.travellogger.validations;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("onlyNumbers")
public class OnlyNumber implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		Integer contact = (Integer) value;
		String s = contact.toString();
		char ch[] = s.toCharArray();
		boolean check = true;
		if (ch.length < 5 || ch.length > 6) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "It Should Have 5-6 Digits."));
		}

	}

}
