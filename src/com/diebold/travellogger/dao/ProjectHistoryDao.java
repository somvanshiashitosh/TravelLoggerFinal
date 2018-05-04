package com.diebold.travellogger.dao;

import java.util.List;

import com.diebold.travellogger.pojo.Project;

public interface ProjectHistoryDao {
	public List<Project> getHistory();
	public boolean toggleStatus(int id);
}
