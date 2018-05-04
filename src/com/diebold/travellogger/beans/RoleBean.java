package com.diebold.travellogger.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.diebold.travellogger.bo.UserBo;
import com.diebold.travellogger.bo.UserBoImpl;
import com.diebold.travellogger.pojo.role;
import com.diebold.travellogger.pojo.user;

@ManagedBean(name="roleBean")
@ViewScoped

public class RoleBean {
	
	private Integer oracleId;
	private String role;
	private String userName;
	private List<String> roles;
	private UserBo ubo;
	
	public RoleBean() {
		
	}
	
	@PostConstruct
	public void init(){
		roles = new ArrayList<String>();
		roles.add("Admin");
		roles.add("Manager");
		roles.add("User");
		
		ubo = new UserBoImpl();
	}
	
	public Integer getOracleId() {
		return oracleId;
	}
	public void setOracleId(Integer oracleId) {
		this.oracleId = oracleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String clickSubmit(){
		FacesContext context = FacesContext.getCurrentInstance();
		String state = "Success";
		user us = ubo.getUserByOracleId(this.getOracleId());
		if(us != null){
			role role = new role();
			role.setOracleId(this.getOracleId());
			role.setRoleName(this.getRole());
			boolean check=ubo.addRole(role);
			if(check)
			{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Role Assigned!",""));
			}
			else
			{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Role Already Exists For This Oracle Id",""));	
			}
		}else{
			state="Fail";
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failed! User May Not Be Registered",""));
		}
		return state;
	}
	
	public String getName(){
        //System.out.println(this.getOracleId());
        FacesContext context = FacesContext.getCurrentInstance();
        String name = ubo.getUserNameById(this.getOracleId());
        String rl = ubo.getRole(this.getOracleId());
        
        if(name != ""){
               this.setUserName(name);
               if(rl!=null){
                     this.setRole(rl);
                     context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Role already exists","")); 
               }else if(rl == null){
                     this.setRole(rl);
                     context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Role not assigned",""));
               }                   
        }else{
               this.setUserName(name);
               context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Oracle id doesn't exist",""));
        }            
        return name;
  }

}
