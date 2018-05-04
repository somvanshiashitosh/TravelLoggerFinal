package com.diebold.travellogger.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.diebold.travellogger.bo.ForexBoImp;
import com.diebold.travellogger.pojo.Charges;
import com.diebold.travellogger.pojo.Forex;
import com.diebold.travellogger.pojo.Mail;

@ManagedBean(name="forex")
@ViewScoped
public class ForexBean {
	private Forex forex;
	private Charges hotelCharges;
	private Charges travelCharges;
	private Charges perdiumCharges;
	private Mail mail;
	private List<String> projectList;
	private List<String> currencyList;
	private ForexBoImp Bo;
	
	@PostConstruct
	public void init(){
		forex = new Forex();
		hotelCharges = new Charges();
		travelCharges = new Charges();
		perdiumCharges = new Charges();
		Bo = new ForexBoImp();
		mail = new Mail();
		projectList = new ForexBoImp().getProjectName();
		currencyList = new ArrayList<String>();
		currencyList.add("USD");
		currencyList.add("EURO");
	}

	public List<String> getCurrencyList() {
		return currencyList;
	}
	public void setCurrencyList(List<String> currencyList) {
		this.currencyList = currencyList;
	}
	public List<String> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<String> projectList) {
		this.projectList = projectList;
	}
	public Charges getHotelCharges() {
		return hotelCharges;
	}
	public void setHotelCharges(Charges hotelCharges) {
		this.hotelCharges = hotelCharges;
	}
	public Charges getTravelCharges() {
		return travelCharges;
	}
	public void setTravelCharges(Charges travelCharges) {
		this.travelCharges = travelCharges;
	}
	public Charges getPerdiumCharges() {
		return perdiumCharges;
	}
	public void setPerdiumCharges(Charges perdiumCharges) {
		this.perdiumCharges = perdiumCharges;
	}
	public Forex getForex() {
		return forex;
	}
	public void setForex(Forex forex) {
		this.forex = forex;
	}	
	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}	
	
	public void PopulateEmail(){
		RequestContext requestContext = RequestContext.getCurrentInstance();
		mail = Bo.getMail(this);
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
	
	@Override
	public String toString() {
		return "ForexBean [forex=" + forex + ", hotelCharges=" + hotelCharges + ", travelCharges=" + travelCharges
				+ ", perdiumCharges=" + perdiumCharges + "]";
	}
}
