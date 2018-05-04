package com.diebold.travellogger.validations;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("addressValidator")
public class addressValidation implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		boolean check = false;
		String name = (String) value;
		name = name.trim();

		if (name.length() > 0) {
			check = Pattern.compile("^[#.0-9a-zA-Z\\s,-/'\"]+$").matcher(name).matches();
			if (!check) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"(,\".-/')Only this Special Symbols Are Allowed", null));
			}
		} else {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Only Space Not Allowed"));
		}
	}
}
