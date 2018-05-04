package com.diebold.travellogger.bo;

import java.util.List;

import com.diebold.travellogger.dao.ProjectDao;
import com.diebold.travellogger.daoImpl.ProjectDaoImpl;
import com.diebold.travellogger.pojo.Project;

public class ProjectBOImpl implements ProjectBO {

	ProjectDao pdao;
	
	public ProjectBOImpl()
	{
		pdao= new ProjectDaoImpl();
		
	}
	
	@Override
	public boolean addProject(Project project) {
		// TODO Auto-generated method stub
		return pdao.addProject(project);
	}
	
	@Override
	public List<Project> getProject() {
		return new ProjectDaoImpl().getProjectList();
	}

	@Override
	public String getProjectCode(String projectName) {
		return new ProjectDaoImpl().getProjectCode(projectName);
	}


}
