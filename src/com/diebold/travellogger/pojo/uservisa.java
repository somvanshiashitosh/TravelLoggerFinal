package com.diebold.travellogger.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class uservisa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "OracleId")
	private Integer oracleId;
	private String country;
	private Date issueDate;
	private Date expiryDate;

	@Column(name = "Editable")
	private boolean editable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getOracleId() {
		return oracleId;
	}

	public void setOracleId(Integer oracleId) {
		this.oracleId = oracleId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public String toString() {
		return "uservisa [id=" + id + ", oracleId=" + oracleId + ", country=" + country + ", issueDate=" + issueDate
				+ ", expiryDate=" + expiryDate + ", editable=" + editable + "]";
	}

}
