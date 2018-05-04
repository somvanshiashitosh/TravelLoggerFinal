package com.diebold.travellogger.dao;

import java.util.List;
import com.diebold.travellogger.pojo.Project;

public interface ProjectDao {
	
	public boolean addProject(Project project);
	public List<Project> getProjectList();
	public String getProjectCode(String projectName);
}
