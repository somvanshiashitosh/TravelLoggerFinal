package com.diebold.travellogger.bo;

import java.util.List;

import com.diebold.travellogger.daoImpl.ManageProjectDaoImp;
import com.diebold.travellogger.pojo.Project;

public class ManageProjectBoImp implements ManageProjectBo{

	@Override
	public List<Project> getProject() {
		return new ManageProjectDaoImp().getProjectList();
	}

}
