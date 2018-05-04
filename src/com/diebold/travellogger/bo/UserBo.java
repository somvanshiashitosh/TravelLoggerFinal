package com.diebold.travellogger.bo;

import java.util.List;

import com.diebold.travellogger.beans.RegisterBean;
import com.diebold.travellogger.pojo.login;
import com.diebold.travellogger.pojo.role;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.pojo.uservisa;

public interface UserBo {

	public boolean addUser(user user,login login, List<uservisa> visaList);
	public user getUserByOracleId(Integer OracleId);
	public boolean addRole(role role);
	public String getUserNameById(Integer id);
	public String getRole(int id);
}
