package com.diebold.travellogger.dao;

import java.util.List;

import com.diebold.travellogger.pojo.travelAgency;

public interface TravelAgencyDao {
	public boolean addTravelAgency(travelAgency ta);
	public List<travelAgency> getTravelAgencies();
}
