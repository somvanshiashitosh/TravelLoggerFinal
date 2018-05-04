package com.diebold.travellogger.bo;

import java.util.List;

import com.diebold.travellogger.pojo.Project;

public interface ProjectBO {
	
	public boolean addProject(Project project);
	public List<Project> getProject();
	public String getProjectCode(String projectName);

}
