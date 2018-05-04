package com.diebold.travellogger.beans;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.diebold.travellogger.bo.UserBo;
import com.diebold.travellogger.bo.UserBoImpl;
import com.diebold.travellogger.daoImpl.LoginDaoImpl;
import com.diebold.travellogger.mail.EmailMessages;
import com.diebold.travellogger.mail.SendMailHTMLSupportBean;
import com.diebold.travellogger.pojo.login;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.util.SecurityUtil;

//import com.diebold.bo.ResourceBO;
//import com.diebold.bo.ResourceBOImpl;
//import com.diebold.pojo.Resource;

@ManagedBean
@RequestScoped
public class ForgotPasswordBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer oracleID;
	private UserBo ubo;

	@PostConstruct
	public void init() {
		ubo = new UserBoImpl();

	}

	public Integer getOracleID() {
		return this.oracleID;
	}

	public void setOracleID(Integer un) {
		this.oracleID = un;
	}

	public String clickOkay() {
		FacesContext context = FacesContext.getCurrentInstance();
		String state = "Fail";
		user us = ubo.getUserByOracleId(oracleID);
		if (us != null) {
			// Adds message to page of successful login
			login login = new LoginDaoImpl().getLoginDetailsByOracleId(us.getOracleId());
			String message = EmailMessages.getForgotPasswordMail();
			message = Pattern.compile("#Name#").matcher(message)
					.replaceFirst(us.getFirstName() + " " + us.getLastName());
			try {
				message = Pattern.compile("#password#").matcher(message).replaceFirst(SecurityUtil.decrypt(login.getPassword()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			new SendMailHTMLSupportBean().send("abc@abc.com", us.getEmailId(), "", "", "Password Recovery", message,
					"localhost");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation successful.",
					" Password has been send to registered EmailId."));
			state = "login";
		}

		else {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation failed!", "Oracle ID doesnt exist"));
			state = "Fail";
		}

		return state;
	}

	/*
	 * dummy function to be replaced by condition
	 */
	public boolean getStatus() {
		boolean status = true;
		return status;
	}
}
