package com.diebold.travellogger.dao;

import java.util.List;

import com.diebold.travellogger.pojo.uservisa;

public interface UserVisaDao {
	
	public boolean addVisa(List<uservisa> list)throws Throwable;
	public boolean addVisa(uservisa uservisa);
	public List<uservisa> getVisaDetails(Integer id);
	public boolean update(uservisa uv);
	public boolean delete(uservisa uv);
	/*public boolean updateEditable(int oracleid);*/

}
