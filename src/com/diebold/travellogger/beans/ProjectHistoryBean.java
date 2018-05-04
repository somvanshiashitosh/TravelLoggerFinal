package com.diebold.travellogger.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import com.diebold.travellogger.bo.ProjectHistoryBoImp;
import com.diebold.travellogger.pojo.Project;

@ManagedBean(name="")
@RequestScoped
public class ProjectHistoryBean {
	private int Id;
	private String projectName;
	private String projectCode;
	private String projectCountry;
	private String projectHead;
	private String PHEmail;
	private String PHContact;
	private String locationHead;
	private String LHEmail;
	private String LHContact;
	private String PSHead;
	private String PSEmail;
	private String PSContact;
	private List<Project> list;
	
	@PostConstruct
	public void init(){
		list = new ProjectHistoryBoImp().getHistory();
	}
	
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
	public List<Project> getList() {
		return list;
	}
	public void setList(List<Project> list) {
		this.list = list;
	}
	
	public void toggleStatus(AjaxBehaviorEvent event){
		if(new ProjectHistoryBoImp().toggleStatus(event))
			RequestContext.getCurrentInstance().execute("location.reload();");
	}
}
