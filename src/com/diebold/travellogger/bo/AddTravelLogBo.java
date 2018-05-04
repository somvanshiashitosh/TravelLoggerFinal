package com.diebold.travellogger.bo;

import java.util.Date;
import java.util.List;

import com.diebold.travellogger.pojo.Mail;
import com.diebold.travellogger.pojo.addTravel;

public interface AddTravelLogBo {
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
	public Mail getMailDetails(Mail mail, String approvalName,String travellerName,String startdate, String endDate,String country,String scopeOfWork, String projectCode);
	public boolean insert(addTravel object);
	public addTravel validateVisa(addTravel travel);
	public void sendRegistrationEmail(String mail);
	public addTravel sendVisaEmail(String agency, addTravel travel);
	public void submitForm(boolean validation, addTravel travel, String applyVisa);
}
