package com.diebold.travellogger.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class user {
	
	@Id
	private Integer oracleId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date dateOfBirth;
	private Date dateOfJoining;
	private String designation;
	private String emailId;
	@Column(name="ContactNo")
	private String contactNo;
	private String passportNo;
	private Date issueDate;
	private Date expiryDate;
	private String placeOfIssue;
	private String passportPdf;
	private String profilePicture;
	private String firstNomineeName;
	private String firstNomineeContact;
	private String firstNomineeRelation;
	private String address;
	private String foodChoice;
	
	public Integer getOracleId() {
		return oracleId;
	}
	public void setOracleId(Integer oracleId) {
		this.oracleId = oracleId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
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
	public String getPlaceOfIssue() {
		return placeOfIssue;
	}
	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}
	public String getPassportPdf() {
		return passportPdf;
	}
	public void setPassportPdf(String passportPdf) {
		this.passportPdf = passportPdf;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getFirstNomineeName() {
		return firstNomineeName;
	}
	public void setFirstNomineeName(String firstNomineeName) {
		this.firstNomineeName = firstNomineeName;
	}
	public String getFirstNomineeContact() {
		return firstNomineeContact;
	}
	public void setFirstNomineeContact(String firstNomineeContact) {
		this.firstNomineeContact = firstNomineeContact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFoodChoice() {
		return foodChoice;
	}
	public void setFoodChoice(String foodChoice) {
		this.foodChoice = foodChoice;
	}
	public String getFirstNomineeRelation() {
		return firstNomineeRelation;
	}
	public void setFirstNomineeRelation(String firstNomineeRelation) {
		this.firstNomineeRelation = firstNomineeRelation;
	}
	
}
