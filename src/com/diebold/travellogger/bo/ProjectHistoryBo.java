package com.diebold.travellogger.bo;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import com.diebold.travellogger.pojo.Project;

public interface ProjectHistoryBo {
	public List<Project> getHistory();
	public boolean toggleStatus(AjaxBehaviorEvent event);
	public boolean closeProject(int id);
}
