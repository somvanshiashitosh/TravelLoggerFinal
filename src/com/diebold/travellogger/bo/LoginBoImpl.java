package com.diebold.travellogger.bo;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.diebold.travellogger.dao.LoginDao;
import com.diebold.travellogger.dao.UserDao;
import com.diebold.travellogger.dao.roleDao;
import com.diebold.travellogger.daoImpl.LoginDaoImpl;
import com.diebold.travellogger.daoImpl.UserDaoImpl;
import com.diebold.travellogger.daoImpl.roleDaoImpl;
import com.diebold.travellogger.pojo.login;
import com.diebold.travellogger.pojo.user;

public class LoginBoImpl implements LoginBo, Serializable {

	LoginDao logindao;

	public LoginBoImpl() {
		logindao = new LoginDaoImpl();
	}

	@Override
	public int ValidateUser(Integer oracleId, String password) {
		// TODO Auto-generated method stub
		login login = logindao.getLoginDetailsByOracleId(oracleId);
		if (login != null) {
			if (login.getPassword().equals(password)) {
				UserDao userdao = new UserDaoImpl();
				user user = (user) userdao.getUserByOracleId(oracleId);
				FacesContext context = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
				session.setAttribute("oracleID", user.getOracleId());
				session.setAttribute("loggedInName", user.getFirstName());
				roleDao roleDao = new roleDaoImpl();
				List<String> roleList = roleDao.getRoleByOracleId(user.getOracleId());
				if (!roleList.isEmpty()) {
					String roleName = roleList.get(0);
					if (roleName.equalsIgnoreCase("manager")) {
						this.setSideBar("true", "true", "false", "false");
					} else if (roleName.equalsIgnoreCase("admin")) {
						this.setSideBar("true", "true", "true", "true");
					} else if (roleName.equalsIgnoreCase("user")) {
						this.setSideBar("false", "false", "false", "false");
					}
				} else {
					this.setSideBar("false", "false", "false", "false");
				}
				return 2;
			} else {
				/*
				 * if(login.getLoginAttempts()<=2) {
				 * login.setLoginAttempts(login.getLoginAttempts()+1);
				 * System.out.println(userdao.updateLoginAttempts(login));
				 * return 1; } else { return 3; }
				 */
				return 1;
			}
		} else {
			return 0;
		}
	}

	private void setSideBar(String travel, String project, String agency, String role) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("travelBoolean", travel);
		session.setAttribute("projectBoolean", project);
		session.setAttribute("agencyBoolean", agency);
		session.setAttribute("roleBoolean", role);
	}

	@Override
	public boolean saveCredentials(login login) throws Throwable {
		// TODO Auto-generated method stub
		return logindao.saveCredentials(login);
	}

	@Override
	public boolean deleteCredentials(login login) {
		// TODO Auto-generated method stub
		return logindao.deleteCredentials(login);
	}
}
