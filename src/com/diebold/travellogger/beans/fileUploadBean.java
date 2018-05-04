package com.diebold.travellogger.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import com.diebold.travellogger.bo.UserVisaBo;
import com.diebold.travellogger.bo.UserVisaBoImpl;
import com.diebold.travellogger.dao.CountryDao;
import com.diebold.travellogger.daoImpl.CountryDaoImpl;
import com.diebold.travellogger.pojo.country;
import com.diebold.travellogger.pojo.uservisa;
import com.diebold.travellogger.util.SessionUtils;

@ManagedBean(name = "fileUploadBean")
@ViewScoped
public class fileUploadBean {
	private uservisa uservisa;
	private List<country> countryList;
	private UserVisaBo uservisaBO;
	private CountryDao cDao;
	private UploadedFile file;
	private String link;

	@PostConstruct
	public void init() {
		uservisa = new uservisa();
		uservisaBO = new UserVisaBoImpl();
		cDao = new CountryDaoImpl();
		countryList = cDao.getCountry();
		this.setLink("C:\\Users\\somvaa\\Desktop\\passportPDF\\downloaded_optimus.jpg");
	}

	public uservisa getUservisa() {
		return uservisa;
	}

	public void setUservisa(uservisa uservisa) {
		this.uservisa = uservisa;
	}

	public List<country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<country> countryList) {
		this.countryList = countryList;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String addVisa() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation successful.", "You Can Add More VISA Details"));
		return "success";
	}

	public void toEnableDailog() {
		
		RequestContext.getCurrentInstance().execute("PF('pc').show();");
	}

	public String addFile() {
		System.out.println("hieee");
		System.out.println(this.getFile().getFileName());
		String filename = this.getFile().getFileName();
		String filePath = "C:\\Users\\somvaa\\Desktop\\passportPDF";
		InputStream input = null;
		OutputStream output = null;
		try {
			input = file.getInputstream();
			output = new FileOutputStream(new File(filePath, filename));
			IOUtils.copy(input, output);
			this.setLink("C:\\Users\\somvaa\\Desktop\\passportPDF\\"+filename);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
		return "success";
	}

}
