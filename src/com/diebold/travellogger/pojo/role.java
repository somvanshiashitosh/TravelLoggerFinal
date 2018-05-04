package com.diebold.travellogger.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3805882257129921821L;
	@Id
	private Integer oracleId;
	private String roleName;
	
	public Integer getOracleId() {
		return oracleId;
	}
	public void setOracleId(Integer oracleId) {
		this.oracleId = oracleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
