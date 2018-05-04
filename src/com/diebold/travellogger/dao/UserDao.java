package com.diebold.travellogger.dao;

import java.util.Date;
import java.util.List;

import com.diebold.travellogger.beans.RegisterBean;
import com.diebold.travellogger.pojo.login;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.pojo.uservisa;

public interface UserDao {

	
	public user getUserByOracleId(Integer oracleid);
	public boolean addUser(user user,login login, List<uservisa> visaList);
	public Date expiryDate(String country);
	public int getUserId();
	public boolean getUserByName(String name);
}
