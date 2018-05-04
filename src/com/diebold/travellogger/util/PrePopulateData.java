package com.diebold.travellogger.util;

import java.util.ArrayList;
import java.util.List;

import com.diebold.travellogger.dao.CountryDao;
import com.diebold.travellogger.dao.DesignationListDao;
import com.diebold.travellogger.dao.foodDao;
import com.diebold.travellogger.daoImpl.CountryDaoImpl;
import com.diebold.travellogger.daoImpl.DesignationListImp;
import com.diebold.travellogger.daoImpl.foodDaoImpl;
import com.diebold.travellogger.pojo.country;
import com.diebold.travellogger.pojo.foodprefrences;

public class PrePopulateData {
	private List<String> designationList;
	private List<foodprefrences> foodList;
	private List<country> countryList;

	public List<String> getDesignationList() {
		return designationList;
	}

	public void setDesignationList(List<String> designationList) {
		this.designationList = designationList;
	}

	public List<foodprefrences> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<foodprefrences> foodList) {
		this.foodList = foodList;
	}

	public List<country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<country> countryList) {
		this.countryList = countryList;
	}

	public PrePopulateData()
	{
		designationList = new ArrayList<String>();
		DesignationListDao dDao = new DesignationListImp();
		designationList = dDao.getDesignation();
		
		foodDao fDao = new foodDaoImpl();
		foodList = fDao.getDetails();
		
		CountryDao cdao=new CountryDaoImpl();
		countryList = new ArrayList<country>();
		countryList = cdao.getCountry();
	}
	
}
