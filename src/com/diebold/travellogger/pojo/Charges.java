package com.diebold.travellogger.pojo;

public class Charges {
	private String numberOfDay;
	private String chargePerDay;
	private String total;
	
	public String getNumberOfDay() {
		return numberOfDay;
	}
	public void setNumberOfDay(String numberOfDay) {
		this.numberOfDay = numberOfDay;
	}
	public String getChargePerDay() {
		return chargePerDay;
	}
	public void setChargePerDay(String chargePerDay) {
		this.chargePerDay = chargePerDay;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "Charges [numberOfDay=" + numberOfDay + ", chargePerDay=" + chargePerDay + ", total=" + total + "]";
	}	
}
