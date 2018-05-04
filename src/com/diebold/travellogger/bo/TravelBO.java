package com.diebold.travellogger.bo;

import java.util.List;

import com.diebold.travellogger.pojo.travelAgency;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.pojo.uservisa;

public interface TravelBO {
	
	public boolean addTravelAgency(travelAgency ta);
	
	public List<travelAgency> getTravelAgencies();
	
	public user getTravellerById(Integer id);
	
}
