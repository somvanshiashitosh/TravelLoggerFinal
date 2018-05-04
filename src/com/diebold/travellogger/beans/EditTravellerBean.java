package com.diebold.travellogger.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.diebold.travellogger.bo.TravelBO;
import com.diebold.travellogger.bo.TravelBOImpl;
import com.diebold.travellogger.dao.DesignationListDao;
import com.diebold.travellogger.dao.EditTravellerDAO;
import com.diebold.travellogger.dao.foodDao;
import com.diebold.travellogger.daoImpl.DesignationListImp;
import com.diebold.travellogger.daoImpl.EditTravellerDAOImpl;
import com.diebold.travellogger.daoImpl.foodDaoImpl;
import com.diebold.travellogger.pojo.fileDownload;
import com.diebold.travellogger.pojo.foodprefrences;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.util.FileUtils;
import com.diebold.travellogger.util.PrePopulateData;
import com.diebold.travellogger.util.SessionUtils;
import com.diebold.travellogger.util.dataFormat;

import antlr.ParserSharedInputState;

@ManagedBean(name = "editTraveller")
@ViewScoped
public class EditTravellerBean {
	EditTravellerDAO etdao;
	TravelBO tbo;
	private user user;
	private String passportLinkToDailog;
	private UploadedFile passportCopy;
	private PrePopulateData dropdowndata;

	public EditTravellerBean() {
		user = new user();
		dropdowndata = new PrePopulateData();

		etdao = new EditTravellerDAOImpl();
		tbo = new TravelBOImpl();
	}

	@PostConstruct
	public void init() {

		Integer oracleId = SessionUtils.getOracleId();
		user = tbo.getTravellerById(oracleId);
		if (user != null) {
			if (user.getPassportPdf() != null) {
				passportLinkToDailog = "/resources/pdf/" + user.getPassportPdf();
			}
		}
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

	public PrePopulateData getDropdowndata() {
		return dropdowndata;
	}

	public void setDropdowndata(PrePopulateData dropdowndata) {
		this.dropdowndata = dropdowndata;
	}

	public EditTravellerDAO getEtdao() {
		return etdao;
	}

	public void setEtdao(EditTravellerDAO etdao) {
		this.etdao = etdao;
	}

	public TravelBO getTbo() {
		return tbo;
	}

	public void setTbo(TravelBO tbo) {
		this.tbo = tbo;
	}

	public UploadedFile getPassportCopy() {
		return passportCopy;
	}

	public void setPassportCopy(UploadedFile passportCopy) {
		this.passportCopy = passportCopy;
	}

	public String getPassportLinkToDailog() {
		return passportLinkToDailog;
	}

	public void setPassportLinkToDailog(String passportLinkToDailog) {
		this.passportLinkToDailog = passportLinkToDailog;
	}

	public void toEnableDailog() {
		RequestContext.getCurrentInstance().execute("PF('pc').show();");
	}

	public String uploadPassportPdf(FileUploadEvent e) {
		this.setPassportCopy(e.getFile());
		String type = passportCopy.getContentType();
		String filepath = null;
		if (null != passportCopy) {
			String filename = null;
			try {
				filename = FileUtils.getFilenameWithOracleId(type, user.getOracleId());
				filepath = FileUtils.getFilePath("link");
				FileUtils.clearFilesWithSameName(filepath, type, user.getOracleId().toString());
				if (!FileUtils.saveFile(filename, filepath, this.passportCopy)) {
					throw new Exception("unable to save file");
				}
				user.setPassportPdf(filename);
				passportLinkToDailog = "/resources/pdf/" + filename;
				FacesContext.getCurrentInstance().addMessage("passportFile-msg",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Uploaded Successfully", ""));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				FacesContext.getCurrentInstance().addMessage("passportFile-msg",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some Error Occurred", "Please Upload Again"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("passportFile-msg",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some Error Occurred", "Please Upload Again"));
		}
		return "success";
	}

	public String submitRegAction() {
		FacesContext context = FacesContext.getCurrentInstance();
		String message = "Failed";
		formatData();
		if (etdao.update(user)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation successful.",
					"Prfile has been edited.<a href='landingPage.xhtml'>Click here to go to home page</a>"));
			message = "Success";
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operation failed!",
					"Profile could not be edited, please try again."));
		}
		return message;
	}

	public void formatData() {
		user.setFirstName(dataFormat.toUpperFirstInitial(user.getFirstName()));
		user.setMiddleName(dataFormat.toUpperFirstInitial(user.getMiddleName()));
		user.setLastName(dataFormat.toUpperFirstInitial(user.getLastName()));
		user.setPlaceOfIssue(dataFormat.toUpperFirstInitial(user.getPlaceOfIssue()));
		user.setFirstNomineeName(dataFormat.toUpperFirstInitial(user.getFirstNomineeName()));
		user.setProfilePicture("Need to be update");
	}
}
