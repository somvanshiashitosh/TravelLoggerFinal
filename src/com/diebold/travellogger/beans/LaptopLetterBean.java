package com.diebold.travellogger.beans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.diebold.travellogger.bo.LaptopLetterBoImp;
import com.diebold.travellogger.pojo.Mail;
import com.diebold.travellogger.pojo.user;

@ManagedBean(name="laptop")
@ViewScoped
public class LaptopLetterBean {
	private Mail mail;
	private String empId;
	private String designation;
	private Date doj;
	private String SerialNumber;
	private Date travelStartDate;
	private Date travelEndDate;
	private String mobileNumber;
	private String location;
	private LaptopLetterBoImp Bo;
	
	@PostConstruct
	public void init(){
		mail = new Mail();
		Bo = new LaptopLetterBoImp();
		user data = Bo.getDetails();
		this.setDesignation(data.getDesignation());
		this.setDoj(data.getDateOfBirth());
		this.setEmpId(data.getOracleId().toString());
		this.setMobileNumber(data.getContactNo());
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}
	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getSerialNumber() {
		return SerialNumber;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}

	public Date getTravelStartDate() {
		return travelStartDate;
	}

	public void setTravelStartDate(Date travelStartDate) {
		this.travelStartDate = travelStartDate;
	}

	public Date getTravelEndDate() {
		return travelEndDate;
	}

	public void setTravelEndDate(Date travelEndDate) {
		this.travelEndDate = travelEndDate;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public void populateDailog(){
		RequestContext requestContext = RequestContext.getCurrentInstance();
		mail.setDailogHeader("Send Laptop Letter Mail");
		mail.setSubject("Laptop letter Required for "+this.getLocation()+" Travel");
		mail.setTo("prasit@db.com");
		mail.setBody(Bo.getBody(this));
		requestContext.execute("PF('sendMail').show();");
	}
	
	public void sendMail(){
		if(Bo.sendMail(mail)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail has been Send", "" ));
			RequestContext requestContext = RequestContext.getCurrentInstance();  
			requestContext.execute("window.location = 'http://localhost:8080/TravelLogger/landingPage.xhtml';");
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error while Sending Mail", "" ));
		}
	}
	
}
