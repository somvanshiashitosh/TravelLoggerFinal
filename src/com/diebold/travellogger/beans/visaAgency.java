package com.diebold.travellogger.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="agency")
@RequestScoped
public class visaAgency {
	
	private String agency;
	private List<String> agencyList;
	
	@PostConstruct
	public void init(){
		agencyList = new ArrayList<String>();
		agencyList.add("abc");
		agencyList.add("qwer");
		agencyList.add("fdgdgd");
		agencyList.add("asdsfd");
		
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public List<String> getAgencyList() {
		return agencyList;
	}

	public void setAgencyList(List<String> agencyList) {
		this.agencyList = agencyList;
	}


	
}
