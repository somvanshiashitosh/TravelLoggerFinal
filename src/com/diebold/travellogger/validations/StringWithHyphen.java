package com.diebold.travellogger.validations;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("nameWithHyphenValidator")
public class StringWithHyphen implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		boolean check = true;
		String name = (String) value;
		name = name.trim();
		if (name.length() > 0) {
			char ch[] = name.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if ((ch[i] > 64 && ch[i] < 91) || (ch[i] > 96 && ch[i] < 123) || ch[i] == 45) {
				} else {
					check = false;
					break;
				}
			}
			if (!check) {
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Only letters are allowed.", null));
			}
		} else {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Only Space Not Allowed"));
		}
	}

}
