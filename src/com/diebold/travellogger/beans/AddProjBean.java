package com.diebold.travellogger.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import com.diebold.travellogger.bo.ProjectBOImpl;
import com.diebold.travellogger.daoImpl.CountryDaoImp;
import com.diebold.travellogger.pojo.Project;
import com.diebold.travellogger.pojo.country;


@ManagedBean(name="projectBean")
@RequestScoped
public class AddProjBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Project project;
	private List<country> countryList;
		
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<country> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<country> countryList) {
		this.countryList = countryList;
	}

	@PostConstruct
	public void init(){
		countryList = new CountryDaoImp().getCountry();	
		project = new Project();
	}
	
	public String addProject(){
		if(new ProjectBOImpl().addProject(project))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation successful.", "Project has been Added.<a href='addproject.xhtml'> Click here to add more" ));
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operation failed!", "Previous operation could not be performed due to some error, please try again."));
		}
		return "success";
	}	
}
