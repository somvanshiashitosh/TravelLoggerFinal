package com.diebold.travellogger.dao;

import java.util.List;

public interface ForexDao {
	public List<String> getProjectName();
	public String getManagerName(String projectName);
	public String getUserEmail();
}
