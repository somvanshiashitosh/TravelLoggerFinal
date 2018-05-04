package com.diebold.travellogger.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.diebold.travellogger.bo.UserVisaBo;
import com.diebold.travellogger.bo.UserVisaBoImpl;
import com.diebold.travellogger.dao.CountryDao;
import com.diebold.travellogger.daoImpl.CountryDaoImpl;
import com.diebold.travellogger.pojo.country;
import com.diebold.travellogger.pojo.uservisa;
import com.diebold.travellogger.util.SessionUtils;

@ManagedBean(name = "addvisaBean")
@ViewScoped
public class AddVisaBean {

	private uservisa uservisa;
	private UserVisaBo uservisaBO;
	private List<country> countryList;
	private CountryDao cDao;

	@PostConstruct
	public void init() {
		uservisa = new uservisa();
		uservisaBO = new UserVisaBoImpl();
		cDao = new CountryDaoImpl();
		countryList = cDao.getCountry();
	}

	public uservisa getUservisa() {
		return uservisa;
	}

	public List<country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<country> countryList) {
		this.countryList = countryList;
	}

	public void setUservisa(uservisa uservisa) {
		this.uservisa = uservisa;
	}

	public String addVisa() {
		FacesContext context = FacesContext.getCurrentInstance();
		uservisa uservisa = new uservisa();
		uservisa.setCountry(this.getUservisa().getCountry());
		uservisa.setOracleId(SessionUtils.getOracleId());
		uservisa.setIssueDate(this.getUservisa().getIssueDate());
		uservisa.setExpiryDate(this.getUservisa().getExpiryDate());
		uservisa.setEditable(true);
		if (uservisaBO.addUserVisa(uservisa)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation successful.",
					"You Can Add More VISA Details"));
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some Error Occured",
					"Please Check The Details"));
		}
		this.getUservisa().setCountry("");
		return "success";
	}
}
