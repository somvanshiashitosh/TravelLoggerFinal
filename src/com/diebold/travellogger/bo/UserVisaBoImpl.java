package com.diebold.travellogger.bo;

import java.util.Iterator;
import java.util.List;

import com.diebold.travellogger.dao.UserVisaDao;
import com.diebold.travellogger.daoImpl.UserVisaDaoImpl;
import com.diebold.travellogger.pojo.uservisa;

public class UserVisaBoImpl implements UserVisaBo {
	
	private UserVisaDao UVDao;
	
	public UserVisaBoImpl()
	{
		UVDao= new UserVisaDaoImpl();
	}

	@Override
	public boolean addUserVisa(List<uservisa> list)throws Throwable {
		// TODO Auto-generated method stub
		return UVDao.addVisa(list);
	}
	
	@Override
	public List<uservisa> getVisaDetails(Integer id){
		return UVDao.getVisaDetails(id);
	}

	@Override
	public boolean update(uservisa uv) {
		// TODO Auto-generated method stub
		return UVDao.update(uv);
	}

	@Override
	public boolean addUserVisa(uservisa uservisa) {
		// TODO Auto-generated method stub
		return UVDao.addVisa(uservisa);
	}

	@Override
	public boolean delete(uservisa uv) {
		// TODO Auto-generated method stub
		return UVDao.delete(uv);
	}
}
