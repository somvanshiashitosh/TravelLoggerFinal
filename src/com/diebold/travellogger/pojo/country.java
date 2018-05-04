package com.diebold.travellogger.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class country implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int IdCountry;
	private String CountryName;
	private String CountryIsoCode;
	
	public int getIdCountry() {
		return IdCountry;
	}
	public void setIdCountry(int idCountry) {
		IdCountry = idCountry;
	}
	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	public String getCountryIsoCode() {
		return CountryIsoCode;
	}
	public void setCountryIsoCode(String countryIsoCode) {
		CountryIsoCode = countryIsoCode;
	}
}
