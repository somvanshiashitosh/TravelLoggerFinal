package com.diebold.travellogger.bo;

import java.util.Date;
import java.util.List;

import com.diebold.travellogger.daoImpl.addTravelDaoImp;
import com.diebold.travellogger.pojo.addTravel;

public class AddTravelBoImp implements AddTravelBo{
	
	public AddTravelBoImp(){
		
	}
	
	@Override
	public boolean insertManageTavel(addTravel data) {
		if(new addTravelDaoImp().insertManageTravelLog(data))
			return true;
		return false;
	}

	@Override
	public boolean validateUser(String name) {
		if(new addTravelDaoImp().validateUser(name))
			return true;
		return false;
	}

	@Override
	public List<String> getTravellerName(String name) {
		return new addTravelDaoImp().getTravellerName(name);
	}

	@Override
	public Date expiryDate(String country) {
		return new addTravelDaoImp().expiryDate(country);
	}

	@Override
	public String getVisaAgencyEmail(String agencyName) {
		return new addTravelDaoImp().getVisaAgencyEmail(agencyName);
	}

	
}
