package com.diebold.travellogger.pojo;

import java.util.Date;

public class Forex {
	private String travellerName;
	private String country;
	private String currency;
	private String projectName;
	private Date travelStartDate;
	private Date travelEndDate;
	private String referenceCurrency;
	private String applicableCurrency;
	private String rate;
	private Date conversionRateDate;
	private String miscellaneousCharges;
	private String cardNumber;
	private String forexCash;
	private String forexCard;
	private String total;
	
	public String getTravellerName() {
		return travellerName;
	}
	public void setTravellerName(String travellerName) {
		this.travellerName = travellerName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getTravelStartDate() {
		return travelStartDate;
	}
	public void setTravelStartDate(Date travelStartDate) {
		this.travelStartDate = travelStartDate;
	}
	public Date getTravelEndDate() {
		return travelEndDate;
	}
	public void setTravelEndDate(Date travelEndDate) {
		this.travelEndDate = travelEndDate;
	}
	public String getReferenceCurrency() {
		return referenceCurrency;
	}
	public void setReferenceCurrency(String referenceCurrency) {
		this.referenceCurrency = referenceCurrency;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public Date getConversionRateDate() {
		return conversionRateDate;
	}
	public void setConversionRateDate(Date conversionRateDate) {
		this.conversionRateDate = conversionRateDate;
	}
	public String getMiscellaneousCharges() {
		return miscellaneousCharges;
	}
	public void setMiscellaneousCharges(String miscellaneousCharges) {
		this.miscellaneousCharges = miscellaneousCharges;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getForexCash() {
		return forexCash;
	}
	public void setForexCash(String forexCash) {
		this.forexCash = forexCash;
	}
	public String getForexCard() {
		return forexCard;
	}
	public void setForexCard(String forexCard) {
		this.forexCard = forexCard;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getApplicableCurrency() {
		return applicableCurrency;
	}
	public void setApplicableCurrency(String applicableCurrency) {
		this.applicableCurrency = applicableCurrency;
	}
	
	@Override
	public String toString() {
		return "Forex [travellerName=" + travellerName + ", country=" + country + ", currency=" + currency
				+ ", projectName=" + projectName + ", travelStartDate=" + travelStartDate + ", travelEndDate="
				+ travelEndDate + ", referenceCurrency=" + referenceCurrency + ", applicableCurrency="
				+ applicableCurrency + ", rate=" + rate + ", conversionRateDate=" + conversionRateDate
				+ ", miscellaneousCharges=" + miscellaneousCharges + ", cardNumber=" + cardNumber + ", forexCash="
				+ forexCash + ", forexCard=" + forexCard + ", total=" + total + "]";
	}
}
