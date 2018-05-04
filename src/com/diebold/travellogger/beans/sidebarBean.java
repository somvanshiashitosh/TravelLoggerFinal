package com.diebold.travellogger.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.diebold.travellogger.util.SessionUtils;

@ManagedBean
@SessionScoped
public class sidebarBean {
	
	private String travelCheck;
	private String projectCheck;
	private String travelAgencyCheck;
	private String roleCheck;
	
	public sidebarBean()
	{
		travelCheck=SessionUtils.getTravelBoolean();
		projectCheck=SessionUtils.getProjectBoolean();
		travelAgencyCheck=SessionUtils.getAgencyBoolean();
		roleCheck=SessionUtils.getAgencyBoolean();
	}

	public String getTravelCheck() {
		return travelCheck;
	}

	public void setTravelCheck(String travelCheck) {
		this.travelCheck = travelCheck;
	}

	public String getProjectCheck() {
		return projectCheck;
	}

	public void setProjectCheck(String projectCheck) {
		this.projectCheck = projectCheck;
	}

	public String getTravelAgencyCheck() {
		return travelAgencyCheck;
	}

	public void setTravelAgencyCheck(String travelAgencyCheck) {
		this.travelAgencyCheck = travelAgencyCheck;
	}

	public String getRoleCheck() {
		return roleCheck;
	}

	public void setRoleCheck(String roleCheck) {
		this.roleCheck = roleCheck;
	}
	
}
