package com.diebold.travellogger.pojo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="travelagency")
public class travelAgency {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="AgencyName")
	private String name;
	@Column(name="AgentName")
	private String agentName;
	@Column(name="Email")
	private String email;
	@Column(name="Contact")
	private String contact;
	@Column(name="CCList")
	private String ccList;
	public travelAgency() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCcList() {
		return ccList;
	}
	public void setCcList(String ccList) {
		this.ccList = ccList;
	}
	@Override
	public String toString() {
		return "TravelAgency [id=" + id + ", name=" + name + ", agentName=" + agentName + ", email=" + email
				+ ", contact=" + contact + ", ccList=" + ccList + "]";
	}
	
	
}
