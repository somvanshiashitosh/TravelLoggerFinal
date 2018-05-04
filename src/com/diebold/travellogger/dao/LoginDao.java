package com.diebold.travellogger.dao;

import com.diebold.travellogger.pojo.login;

public interface LoginDao {
	
	public boolean saveCredentials(login login) throws Throwable;
	public boolean deleteCredentials(login login);
	public login getLoginDetailsByOracleId(Integer oracleid);
	public boolean updateLoginAttempts(login login);

}
