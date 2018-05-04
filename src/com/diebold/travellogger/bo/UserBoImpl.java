package com.diebold.travellogger.bo;

import java.util.List;

import com.diebold.travellogger.beans.RegisterBean;
import com.diebold.travellogger.dao.UserDao;
import com.diebold.travellogger.dao.roleDao;
import com.diebold.travellogger.daoImpl.UserDaoImpl;
import com.diebold.travellogger.daoImpl.roleDaoImpl;
import com.diebold.travellogger.pojo.login;
import com.diebold.travellogger.pojo.role;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.pojo.uservisa;

public class UserBoImpl implements UserBo {

	private UserDao uDao;
	private roleDao rDao;

	public UserBoImpl() {
		uDao = new UserDaoImpl();
		rDao = new roleDaoImpl();
	}

	@Override
	public boolean addUser(user user, login login, List<uservisa> visaList) {
		// TODO Auto-generated method stub
		return uDao.addUser(user, login, visaList);
	}

	@Override
	public user getUserByOracleId(Integer OracleId) {
		// TODO Auto-generated method stub
		return uDao.getUserByOracleId(OracleId);
	}

	@Override
	public boolean addRole(role role) {
		return rDao.addRole(role);
	}

	@Override
	public String getUserNameById(Integer id) {
		// TODO Auto-generated method stub
		user s = uDao.getUserByOracleId(id);
		if (s != null) {
			return s.getFirstName() + " " + s.getLastName();
		} else {
			return "";
		}

	}
	
	public String getRole(int id){
        return rDao.getRole(id);
  }

}
