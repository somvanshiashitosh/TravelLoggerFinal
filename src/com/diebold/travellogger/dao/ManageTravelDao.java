package com.diebold.travellogger.dao;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import com.diebold.travellogger.pojo.addTravel;

public interface ManageTravelDao {
	public List<addTravel> getAllTravels();
	public String getEADetails(int id);
	public boolean EngagementLink(int id, String EAmessage);
	public String getPADetails(int id);
	public boolean projectApprovalLink(int id, String EAmessage);
	public String getLADetails(int id);
	public boolean locationApprovalLink(int id, String EAmessage);
	public String getPSDetails(int id);
	public boolean psApprovalLink(int id, String EAmessage);
	public String getTravellerDetails(int id);
}
