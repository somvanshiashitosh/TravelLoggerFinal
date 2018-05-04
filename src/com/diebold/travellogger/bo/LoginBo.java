package com.diebold.travellogger.bo;

import com.diebold.travellogger.pojo.login;

public interface LoginBo {
	
	public int ValidateUser(Integer oracleId, String password);
	public boolean saveCredentials(login login)throws Throwable;
	public boolean deleteCredentials(login login);
}
