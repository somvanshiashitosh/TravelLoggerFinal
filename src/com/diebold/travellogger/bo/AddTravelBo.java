package com.diebold.travellogger.bo;

import java.util.Date;
import java.util.List;

import com.diebold.travellogger.pojo.addTravel;

public interface AddTravelBo {
	public boolean insertManageTavel(addTravel data);
	public boolean validateUser(String name);
	public List<String> getTravellerName(String name);
	public Date expiryDate(String country);
	public String getVisaAgencyEmail(String agencyName);
}
