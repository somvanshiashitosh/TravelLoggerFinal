package com.diebold.travellogger.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.diebold.travellogger.bo.UserVisaBo;
import com.diebold.travellogger.bo.UserVisaBoImpl;
import com.diebold.travellogger.dao.CountryDao;
import com.diebold.travellogger.daoImpl.CountryDaoImpl;
import com.diebold.travellogger.pojo.country;
import com.diebold.travellogger.pojo.uservisa;
import com.diebold.travellogger.util.SessionUtils;

@ManagedBean(name = "visaBean")
@RequestScoped
public class VisaBean {
	private uservisa uservisa;
	private List<country> countryList;
	private UserVisaBo uservisaBO;
	private List<uservisa> usvisa;
	private CountryDao cDao;
	// private RequestContext reqContext;

	public VisaBean() {
		uservisa = new uservisa();
		uservisaBO = new UserVisaBoImpl();
		cDao = new CountryDaoImpl();
		countryList = cDao.getCountry();
		// reqContext=RequestContext.getCurrentInstance();
		// reqContext.execute("PF('update-confirm').hide()");
	}

	@PostConstruct
	public void init() {
		usvisa = uservisaBO.getVisaDetails(SessionUtils.getOracleId());
	}

	public List<uservisa> getUsvisa() {
		return usvisa;
	}

	public void setUsvisa(List<uservisa> usvisa) {
		this.usvisa = usvisa;
	}

	public List<country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<country> countryList) {
		this.countryList = countryList;
	}

	public uservisa getUservisa() {
		return uservisa;
	}

	public void setUservisa(uservisa uservisa) {
		this.uservisa = uservisa;
	}

	public String submitRegAction() {
		FacesContext context = FacesContext.getCurrentInstance();
		String message = "Failed";
		if (uservisaBO != null) {
			if (uservisaBO.update(uservisa)) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated successfully...", ""));
				message = "Success";
			} else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operation failed!", "Please Add Again"));
			}
		} else {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operation failed!", "Please Add Again"));
		}
		return message;
	}

	public void updateAction(ActionEvent event) {
		UIComponent tmpComponent = event.getComponent();
		while (null != tmpComponent && !(tmpComponent instanceof UIData)) {
			tmpComponent = tmpComponent.getParent();
		}
		if (tmpComponent != null && (tmpComponent instanceof UIData)) {
			Object tmpRowData = ((UIData) tmpComponent).getRowData();
			uservisa = (uservisa) tmpRowData;
			uservisa.setEditable(true);
			submitRegAction();
		}
	}

	public void deleteAction(ActionEvent event) {
		UIComponent tmpComponent = event.getComponent();
		System.out.println(tmpComponent);
		while (null != tmpComponent && !(tmpComponent instanceof UIData)) {
			tmpComponent = tmpComponent.getParent();
		}
		if (tmpComponent != null && (tmpComponent instanceof UIData)) {
			Object tmpRowData = ((UIData) tmpComponent).getRowData();
			uservisa= (uservisa) tmpRowData;
			uservisa.setEditable(true);
			System.out.println(uservisa);
			deleteVisa(uservisa);
		}
	}


	private String deleteVisa(uservisa uservisa) {
		FacesContext context = FacesContext.getCurrentInstance();
		uservisa.setEditable(false);
		if (uservisaBO.delete(uservisa)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted successfully...", ""));
			init();
		} else {

			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operation failed!", "Please Delete Again"));
		}
		return null;
	}
}
