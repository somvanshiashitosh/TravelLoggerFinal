package com.diebold.travellogger.dao;

import java.util.List;

import com.diebold.travellogger.pojo.role;

public interface roleDao {
	
	public List<String> getRoleByOracleId(Integer id);
	public boolean addRole(role role);
	public String getRole(int id);
}
