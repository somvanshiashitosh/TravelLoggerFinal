package com.diebold.travellogger.bo;

import java.util.List;
import com.diebold.travellogger.pojo.*;

public interface UserVisaBo {
	
	public boolean addUserVisa(List<uservisa> list)throws Throwable;
	public boolean addUserVisa(uservisa uservisa);
	public List<uservisa> getVisaDetails(Integer id);
	public boolean update(uservisa uv);
	public boolean delete(uservisa uv);

}
