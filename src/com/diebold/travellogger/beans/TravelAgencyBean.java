package com.diebold.travellogger.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.diebold.travellogger.bo.TravelBO;
import com.diebold.travellogger.bo.TravelBOImpl;
import com.diebold.travellogger.pojo.travelAgency;

@ManagedBean(name="travelBean")
@RequestScoped
public class TravelAgencyBean {
	
	TravelBO tbo;
	travelAgency ta;
	
	private String name;
	private String agentName;
	private String email;
	private String contact;
	private String ccList;
	
	public travelAgency getTa() {
		return ta;
	}

	public void setTa(travelAgency ta) {
		this.ta = ta;
	}

	@PostConstruct
	public void init(){
		tbo = new TravelBOImpl();
		ta = new travelAgency();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCcList() {
		return ccList;
	}
	public void setCcList(String ccList) {
		this.ccList = ccList;
	}
	
	private List<travelAgency> tl=null;
	

	public List<travelAgency> getTl() {
		return tl;
	}

	public void setTl(List<travelAgency> tl) {
		this.tl = tl;
	}

	public String clickSubmit(){
		FacesContext context = FacesContext.getCurrentInstance();
		String state = "Fail";
		ta=this.editTravelAgency();
		System.out.println(ta);
		if(tbo.addTravelAgency(ta)){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Operation Successfull","Travel Agency added!"));
			state="Success";
			System.out.println(tl);
		}else{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Operation Failed","Could not complete"));
			state="Fail";
		}
		System.out.println(state);
		return state;
	}
	
	public travelAgency editTravelAgency(){
		ta.setName(this.name);
		ta.setAgentName(this.agentName);
		ta.setEmail(this.email);
		ta.setContact(this.contact);
		ta.setCcList(this.ccList);
		return ta;
	}
}
