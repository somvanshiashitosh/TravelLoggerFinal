	package com.diebold.travellogger.beans;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.diebold.travellogger.bo.LoginBo;
import com.diebold.travellogger.bo.LoginBoImpl;
import com.diebold.travellogger.util.SecurityUtil;

@ManagedBean
@SessionScoped
public class loginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer oracleID;
	private String password;
	private int loginAttempts;
	private Date lastLogin;
	private LoginBo loginBo;

	@PostConstruct
	public void init() {
		loginBo = new LoginBoImpl();
	}

	public Integer getOracleID() {
		return this.oracleID;
	}

	public void setOracleID(Integer un) {
		this.oracleID = un;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String ps) {
		this.password = ps;
	}

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "LoginBean [oracleID=" + oracleID + ", password=" + password + ", loginAttempts=" + loginAttempts
				+ ", lastLogin=" + lastLogin + ", loginBo=" + loginBo + "]";
	}

	public String loginAction() {
		FacesContext context = FacesContext.getCurrentInstance();
		String state = "Success";
		String encPassword=null;
		try {
			encPassword = SecurityUtil.encrypt(this.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int check = loginBo.ValidateUser(this.oracleID, encPassword);
		if (check == 0) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Not a Valid User", "Please signUp"));
			state = "Fail";
		} else if (check == 1) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Incorrect password", "Please ReEnter"));
			state = "Fail";
		} else if (check == 2) {
			System.out.println("redirected to landing");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "LoggedIn Successfully", ""));

		} else {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account has been locked", "Try after sometime"));
		}

		return state;
	}

	/*
	 * public String forgotAction(){
	 * 
	 * return "forgotpassword"; }
	 */

}
