package com.diebold.travellogger.beans;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.diebold.travellogger.util.SessionUtils;

@ManagedBean
@SessionScoped
public class HeaderBean {
	private String name;
	
	
	@PostConstruct
	public void init(){
	try{
			
		name=SessionUtils.getLoggedInName();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String logoutAction(){
		//Performing logout operations
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
			session.invalidate();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Logged Out","Please Enter Credentials"));		
		}
		catch(Exception e){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Some error occurred","Please Enter Credentials"));
		}
		return "login";
	}

}
