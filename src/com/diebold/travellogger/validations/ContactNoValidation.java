package com.diebold.travellogger.validations;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("contactNoValidation")
public class ContactNoValidation implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		
		String contact=(String)arg2;
		char ch[]=contact.toCharArray();
		boolean check=true;
		if(ch.length>10 || ch.length<10)
		{
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "It should have 10 digits.", null));
		}
		else
		{
			for(int i=0; i<ch.length; i++)
			{
				if(ch[i]<48 || ch[i]>57)
				{
					check=false;
					break;
				}
			}
			if(ch[0]==55 || ch[0]==56 || ch[0]==57)
			{
			}
			else
			{
				check=false;
			}
			if(!check)
			{
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                "Invalid mobile no.", null));
			}
		}
	}

}
