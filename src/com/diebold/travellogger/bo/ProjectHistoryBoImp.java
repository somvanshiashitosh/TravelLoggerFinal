package com.diebold.travellogger.bo;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.event.AjaxBehaviorEvent;

import com.diebold.travellogger.daoImpl.ProjectHistoryDaoImp;
import com.diebold.travellogger.pojo.Project;

public class ProjectHistoryBoImp implements ProjectHistoryBo{

	@Override
	public List<Project> getHistory() {
		return new ProjectHistoryDaoImp().getHistory();
	}

	@Override
	public boolean toggleStatus(AjaxBehaviorEvent event) {
		int id=0;
		Project tmpBean = null;
	    UIComponent tmpComponent = event.getComponent();
	    while (null != tmpComponent && !(tmpComponent instanceof UIData)) {
	      tmpComponent = tmpComponent.getParent();
	    }
	    if (tmpComponent != null && (tmpComponent instanceof UIData)) {
	      Object tmpRowData = ((UIData) tmpComponent).getRowData();
	      if (tmpRowData instanceof Project) {
	    	  tmpBean = (Project) tmpRowData;    		  
	    		  id = tmpBean.getId();	    		  
	    		  return new ProjectHistoryDaoImp().toggleStatus(id);
	      }else{
	    	  return false;
	      }
	    }
	    return false;
	}

	@Override
	public boolean closeProject(int id) {
		return new ProjectHistoryDaoImp().toggleStatus(id);
	}

}
