package com.diebold.travellogger.bo;

import java.util.List;

import com.diebold.travellogger.beans.ManageTravelBean;
import com.diebold.travellogger.pojo.addTravel;

public interface ManageTravelBo {
	public List<addTravel> getAllTravels();
	public ManageTravelBean getEADetails(int id);
	public void EngagementLink(int id);
	public ManageTravelBean getPADetails(int id);
	public void projectApprovalLink(int id);
	public ManageTravelBean getLADetails(int id);
	public void locationApprovalLink(int id);
	public ManageTravelBean getPSDetails(int id);
	public void psApprovalLink(int id);
	public String getTravellerDetails(int id);
	public boolean sendAgencyMail(String agency, int id);
}
