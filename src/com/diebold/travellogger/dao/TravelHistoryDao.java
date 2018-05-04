package com.diebold.travellogger.dao;

import java.util.List;

import com.diebold.travellogger.pojo.addTravel;

public interface TravelHistoryDao {
	public List<addTravel> getTravelHistory();
}
