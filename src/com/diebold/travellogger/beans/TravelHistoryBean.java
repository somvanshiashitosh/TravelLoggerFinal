package com.diebold.travellogger.beans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.diebold.travellogger.bo.TravelHistoryBoImp;
import com.diebold.travellogger.pojo.addTravel;

@ManagedBean(name="travelHistoryBean")
@RequestScoped
public class TravelHistoryBean {
	private int id;
	private String travellerName;
	private String customerName;
	private String projectName;
	private String projectCode;
	private String designation;
	private String travelDestination;
	private Date travelStartDate;
	private Date travelEndDate;
	private String visaStatus;
	private Date projectInitiationDate;
	private Date projectConfirmationDate;
	private String costCovered;
	private String scopeWork;
	private String engagementAgreement;
	private String projectLevel;
	private String locationHead;
	private String psHead;
	private List<addTravel> travels;
	
	@PostConstruct
	public void init(){
		travels = new TravelHistoryBoImp().getTravelHistory();		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTravellerName() {
		return travellerName;
	}
	public void setTravellerName(String travellerName) {
		this.travellerName = travellerName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getTravelDestination() {
		return travelDestination;
	}
	public void setTravelDestination(String travelDestination) {
		this.travelDestination = travelDestination;
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
	public String getVisaStatus() {
		return visaStatus;
	}
	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
	}
	public Date getProjectInitiationDate() {
		return projectInitiationDate;
	}
	public void setProjectInitiationDate(Date projectInitiationDate) {
		this.projectInitiationDate = projectInitiationDate;
	}
	public Date getProjectConfirmationDate() {
		return projectConfirmationDate;
	}
	public void setProjectConfirmationDate(Date projectConfirmationDate) {
		this.projectConfirmationDate = projectConfirmationDate;
	}
	public String getCostCovered() {
		return costCovered;
	}
	public void setCostCovered(String costCovered) {
		this.costCovered = costCovered;
	}
	public String getScopeWork() {
		return scopeWork;
	}
	public void setScopeWork(String scopeWork) {
		this.scopeWork = scopeWork;
	}
	public String getEngagementAgreement() {
		return engagementAgreement;
	}
	public void setEngagementAgreement(String engagementAgreement) {
		this.engagementAgreement = engagementAgreement;
	}
	public String getProjectLevel() {
		return projectLevel;
	}
	public void setProjectLevel(String projectLevel) {
		this.projectLevel = projectLevel;
	}
	public String getLocationHead() {
		return locationHead;
	}
	public void setLocationHead(String locationHead) {
		this.locationHead = locationHead;
	}
	public String getPsHead() {
		return psHead;
	}
	public void setPsHead(String psHead) {
		this.psHead = psHead;
	}
	public List<addTravel> getTravels() {
		return travels;
	}
	public void setTravels(List<addTravel> travels) {
		this.travels = travels;
	}
	
	
}
