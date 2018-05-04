package com.diebold.travellogger.beans;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import com.diebold.travellogger.bo.ManageAgencyBoImp;
import com.diebold.travellogger.pojo.travelAgency;

@ManagedBean(name="ManageAgency")
@SessionScoped
public class ManageAgencyBean {
	private int Id;
	private String AgencyName;
	private String AgentName;
	private String Email;
	private String cc;
	private String contact;
	private List<travelAgency> list;
	
	@PostConstruct
	public void init(){
		list = new ManageAgencyBoImp().getAgency();
		System.out.println(list);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getAgencyName() {
		return AgencyName;
	}

	public void setAgencyName(String agencyName) {
		AgencyName = agencyName;
	}

	public String getAgentName() {
		return AgentName;
	}

	public void setAgentName(String agentName) {
		AgentName = agentName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<travelAgency> getList() {
		return list;
	}

	public void setList(List<travelAgency> list) {
		this.list = list;
	}
	
	
}
