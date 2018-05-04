package com.diebold.travellogger.dao;

import java.util.Date;
import java.util.List;

import com.diebold.travellogger.pojo.addTravel;

public interface AddTravelLogDao {
	public List<String> getCountry();
	public List<String> getProjectName();
	public List<String> getApprovalStatus();
	public List<String> getCostCoveredBy();
	public List<String> getTravellerName(String name);
	public boolean validateUser(String name);
	public String getProjectCode(String projectName);
	public Date[] expiryDate(String country);
	public List<String> getAgency();
	public String getVisaAgencyDetails(String agencyName);
	public String getEADetails(String projectCode);
	public String getPADetails(String projectCode);
	public String getLADetails(String projectCode);
	public String getPsDetails(String projectCode);
	public boolean insert(addTravel object);
}
