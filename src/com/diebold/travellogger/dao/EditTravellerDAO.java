package com.diebold.travellogger.dao;

import com.diebold.travellogger.pojo.user;

public interface EditTravellerDAO {
	public user getTravellerById(Integer id);
	public boolean update(user u);
}
