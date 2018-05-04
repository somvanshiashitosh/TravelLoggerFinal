package com.diebold.travellogger.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class login {
	
	private Integer oracleID;
	private String password;
	private int loginAttempts;
	private Date lastLogin;
	
	
	
	@Id
	public Integer getOracleID() {
		return oracleID;
	}
	public void setOracleID(Integer oracleID) {
		this.oracleID = oracleID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLoginAttempts() {
		return loginAttempts;
	}
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	@Override
	public String toString() {
		return "login [oracleID=" + oracleID + ", password=" + password + ", loginAttempts=" + loginAttempts
				+ ", lastLogin=" + lastLogin + "]";
	}
	

}
