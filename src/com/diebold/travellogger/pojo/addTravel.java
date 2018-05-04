package com.diebold.travellogger.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="travelLog")
public class addTravel implements Serializable{
	private static final long serialVersionUID = 1874587501233135286L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="TravellerName")
	private String travellerName;
	@Column(name="CustomerName")
	private String customerName;
	@Column(name="ProjectName")
	private String projectName;
	@Column(name="ProjectCode")
	private String projectCode;
	@Column(name="Designation")
	private String designation;
	@Column(name="Destination")
	private String travelDestination;
	@Column(name="TravelStartDate")
	private Date travelStartDate;
	@Column(name="TravelEndDate")
	private Date travelEndDate;
	@Column(name="VisaStatus")
	private String visaStatus;
	@Column(name="ProjectLevelRequestInitiationDate")
	private Date projectInitiationDate;
	@Column(name="ProjectLevelTravelConfirmationDate")
	private Date projectConfirmationDate;
	@Column(name="CostCoveredBy")
	private String costCovered;
	@Column(name="WorkScope")
	private String scopeWork;
	@Column(name="EngagementAgreementApprovalStatus")
	private String engagementAgreement;
	@Column(name="ProjectLevelApprovalStatus")
	private String projectLevel;
	@Column(name="LocationHeadApprovalStatus")
	private String locationHead;
	@Column(name="PSLeaderApprovalStatus")
	private String psHead;
	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
