package com.diebold.travellogger.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="designation")
public class Designation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	private String Designation;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	@Override
	public String toString() {
		return "Designation [Id=" + Id + ", Designation=" + Designation + "]";
	}
	
}
