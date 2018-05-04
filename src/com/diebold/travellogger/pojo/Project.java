package com.diebold.travellogger.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	private int Id;
	private String projectName;
	private String projectCode;
	private String projectCountry;
	private String projectHead;
	private String PHEmail;
	private String PHContact;
	private String PHCC = "";
	private String locationHead;
	private String LHEmail;
	private String LHContact;
	private String LHCC = "";
	private String PSHead;
	private String PSEmail;
	private String PSContact;
	private String PSCC = "";
	private String projectStatus = "InProgress";
	private String EAHead = "";
	private String EAEmail = "";
	private String EAContact = "";
	private String EACC = "";
	private String managerName;
	private String managerContact;
	private String managerEmail;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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
	public String getProjectCountry() {
		return projectCountry;
	}
	public void setProjectCountry(String projectCountry) {
		this.projectCountry = projectCountry;
	}
	public String getProjectHead() {
		return projectHead;
	}
	public void setProjectHead(String projectHead) {
		this.projectHead = projectHead;
	}
	public String getPHEmail() {
		return PHEmail;
	}
	public void setPHEmail(String pHEmail) {
		PHEmail = pHEmail;
	}
	public String getPHContact() {
		return PHContact;
	}
	public void setPHContact(String pHContact) {
		PHContact = pHContact;
	}
	public String getLocationHead() {
		return locationHead;
	}
	public void setLocationHead(String locationHead) {
		this.locationHead = locationHead;
	}
	public String getLHEmail() {
		return LHEmail;
	}
	public void setLHEmail(String lHEmail) {
		LHEmail = lHEmail;
	}
	public String getLHContact() {
		return LHContact;
	}
	public void setLHContact(String lHContact) {
		LHContact = lHContact;
	}
	public String getPSHead() {
		return PSHead;
	}
	public void setPSHead(String pSHead) {
		PSHead = pSHead;
	}
	public String getPSEmail() {
		return PSEmail;
	}
	public void setPSEmail(String pSEmail) {
		PSEmail = pSEmail;
	}
	public String getPSContact() {
		return PSContact;
	}
	public void setPSContact(String pSContact) {
		PSContact = pSContact;
	}
	
	
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getPHCC() {
		return PHCC;
	}
	public void setPHCC(String pHCC) {
		PHCC = pHCC;
	}
	public String getLHCC() {
		return LHCC;
	}
	public void setLHCC(String lHCC) {
		LHCC = lHCC;
	}
	public String getPSCC() {
		return PSCC;
	}
	public void setPSCC(String pSCC) {
		PSCC = pSCC;
	}
	public String getEAHead() {
		return EAHead;
	}
	public void setEAHead(String eAHead) {
		EAHead = eAHead;
	}
	public String getEAEmail() {
		return EAEmail;
	}
	public void setEAEmail(String eAEmail) {
		EAEmail = eAEmail;
	}
	public String getEAContact() {
		return EAContact;
	}
	public void setEAContact(String eAContact) {
		EAContact = eAContact;
	}
	public String getEACC() {
		return EACC;
	}
	public void setEACC(String eACC) {
		EACC = eACC;
	}	
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerContact() {
		return managerContact;
	}
	public void setManagerContact(String managerContact) {
		this.managerContact = managerContact;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	@Override
	public String toString() {
		return "AddProject [Id=" + Id + ", projectName=" + projectName + ", projectCode=" + projectCode
				+ ", projectCountry=" + projectCountry + ", projectHead=" + projectHead + ", PHEmail=" + PHEmail
				+ ", PHContact=" + PHContact + ", PHCC=" + PHCC + ", locationHead=" + locationHead + ", LHEmail="
				+ LHEmail + ", LHContact=" + LHContact + ", LHCC=" + LHCC + ", PSHead=" + PSHead + ", PSEmail="
				+ PSEmail + ", PSContact=" + PSContact + ", PSCC=" + PSCC + ", projectStatus=" + projectStatus
				+ ", EAHead=" + EAHead + ", EAEmail=" + EAEmail + ", EAContact=" + EAContact + ", EACC=" + EACC + "]";
	}
	
	
}
