package com.diebold.travellogger.validations;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("aplphaNumericValidator")
public class AlphaNumericString implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {

		boolean check = true;
		String name = (String) arg2;
		name = name.trim();
		if (name.length() > 0) {
			char ch[] = name.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if (ch[i] < 48 || (ch[i] > 57 && ch[i] < 65) || (ch[i] > 90 && ch[i] < 97) || ch[i] > 122) {
					check = false;
					break;
				}
			}
			if (!check) {
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Only letters and numbers are allowed.", null));
			}
		} else {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Only Space Not Allowed"));
		}
	}

}
