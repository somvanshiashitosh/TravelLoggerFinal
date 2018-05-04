package com.diebold.travellogger.beans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.diebold.travellogger.bo.TravelInsuranceBoImp;
import com.diebold.travellogger.pojo.Mail;
import com.diebold.travellogger.pojo.user;

@ManagedBean(name="travelInsurance")
@ViewScoped
public class TravelInsuranceBean {
	private Date travelStartDate;
	private Date travelEndDate;
	private String nomineeName;
	private String mobileNumber;
	private Date dateOfBirth;
	private Mail mail;
	private String cc;
	private String location;
	private String relationship;
	private TravelInsuranceBoImp Bo;
	
	
	@PostConstruct
	public void init(){
		Bo = new TravelInsuranceBoImp();
		user data = Bo.getInsuranceData();
		mail = new Mail();
		this.setNomineeName(data.getFirstNomineeName());
		this.setDateOfBirth(data.getDateOfBirth());
		this.setMobileNumber(data.getContactNo());
		this.setRelationship(data.getFirstNomineeRelation());
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
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}	
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
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
	
	public void populateMail(){
		RequestContext requestContext = RequestContext.getCurrentInstance();
		this.mail.setDailogHeader("Send Mail for Travel Insurance");
		this.mail.setSubject("Travel Insurance Required for "+this.getLocation()+" Travel");
		this.mail.setCc(this.getCc());
		this.mail.setTo("Prasit@db.com");
		this.mail.setBody(Bo.getBody(this));
		requestContext.execute("PF('sendMail').show();");
	}
	
}
