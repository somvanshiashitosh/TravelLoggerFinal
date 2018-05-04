package com.diebold.travellogger.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static Integer getOracleId() {
		HttpSession session = getSession();
		if (session != null)
			return (Integer) session.getAttribute("oracleID");
		else
			return null;
	}
	
	public static String getLoggedInName() {
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("loggedInName");
		else
			return null;
	}
	
	public static String getTravelBoolean(){
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("travelBoolean");
		else
			return null;
	}
	
	public static String getProjectBoolean(){
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("projectBoolean");
		else
			return null;
	}

	public static String getAgencyBoolean(){
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("agencyBoolean");
		else
			return null;
	}
	
	public static String getRoleBoolean()
	{
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("roleBoolean");
		else
			return null;
	}
}