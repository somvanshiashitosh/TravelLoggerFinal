package com.diebold.travellogger.dao;

import java.util.Date;
import java.util.List;

import com.diebold.travellogger.pojo.addTravel;

public interface addTravelDao {
	public boolean validateUser(String name);
	public boolean insertManageTravelLog(addTravel data);
	public List<String> getTravellerName(String name);
	public Date expiryDate(String country);
	public String getVisaAgencyEmail(String agencyName);
}
