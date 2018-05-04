package com.diebold.travellogger.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.diebold.travellogger.bo.UserBo;
import com.diebold.travellogger.bo.UserBoImpl;
import com.diebold.travellogger.pojo.UIComponents;
import com.diebold.travellogger.pojo.login;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.pojo.uservisa;
import com.diebold.travellogger.util.FileUtils;
import com.diebold.travellogger.util.PrePopulateData;
import com.diebold.travellogger.util.SecurityUtil;
import com.diebold.travellogger.util.dataFormat;

@ManagedBean
@ViewScoped
public class RegisterBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private user user;
	private login login;
	private uservisa uservisa;
	private PrePopulateData dropdownData;

	private UserBo userBo;

	private boolean isValidInfo;
	private boolean checkId;

	private List<uservisa> visaList;

	private String validDates = "right";
	private String validJoiningDate = "false";

	private UIComponents uicomp;
	String filename;
	String type;
	private UploadedFile pass;

	@PostConstruct
	public void init() {
		user = new user();
		login = new login();
		uservisa = new uservisa();
		dropdownData = new PrePopulateData();
		uicomp = new UIComponents();
		visaList = new ArrayList<uservisa>();
		userBo = new UserBoImpl();
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

	public login getLogin() {
		return login;
	}

	public void setLogin(login login) {
		this.login = login;
	}

	public PrePopulateData getDropdownData() {
		return dropdownData;
	}

	public void setDropdownData(PrePopulateData dropdownData) {
		this.dropdownData = dropdownData;
	}

	public boolean ischeckId() {
		return checkId;
	}

	public void setcheckId(boolean checkId) {
		this.checkId = checkId;
	}

	public UploadedFile getPass() {
		return pass;
	}

	public void setPass(UploadedFile pass) {
		this.pass = pass;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isValidInfo() {
		return isValidInfo;
	}

	public void setValidInfo(boolean isValidInfo) {
		this.isValidInfo = isValidInfo;
	}

	public uservisa getUservisa() {
		return uservisa;
	}

	public void setUservisa(uservisa uservisa) {
		this.uservisa = uservisa;
	}

	public List<uservisa> getVisaList() {
		return visaList;
	}

	public void setVisaList(List<uservisa> visaList) {
		this.visaList = visaList;
	}

	public String isValidDates() {
		return validDates;
	}

	public void setValidDates(String validDates) {
		this.validDates = validDates;
	}

	public String isValidJoiningDate() {
		return validJoiningDate;
	}

	public void setValidJoiningDate(String validJoiningDate) {
		this.validJoiningDate = validJoiningDate;
	}

	public UIComponents getUicomp() {
		return uicomp;
	}

	public void setUicomp(UIComponents uicomp) {
		this.uicomp = uicomp;
	}

	public void checkOracleIdInDatabase() {
		user userLocal = userBo.getUserByOracleId(user.getOracleId());
		if (userLocal != null) {
			this.setcheckId(false);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(uicomp.getOracleidUI().getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "User Already Exists"));
		} else {
			this.setcheckId(true);
		}
	}

	public String uploadPassportPdf(FileUploadEvent e) {
		this.setPass(e.getFile());
		type = this.getPass().getContentType();
		FacesContext context = FacesContext.getCurrentInstance();
		String filePath = null;
		if (pass != null) {
			try {
				if (this.ischeckId() == false) {
					throw new Exception("User Already Exists");
				}
				filename = FileUtils.getFilenameWithOracleId(type, user.getOracleId());
				if (filename != null) {
					filePath = FileUtils.getFilePath("link");
					FileUtils.clearFilesWithSameName(filePath, type, user.getOracleId().toString());
					if (!FileUtils.saveFile(filename, filePath, this.pass)) {
						throw new Exception("unable to save file");
					}
					user.setPassportPdf(filename);
					context.addMessage(this.getUicomp().getPassportFileUI().getClientId(context),
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Uploaded Successfully", ""));
				} else {
					throw new Exception("Please Check File Name...Rename It With Your Name");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				context.addMessage(this.getUicomp().getPassportFileUI().getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_FATAL, e1.getMessage(), ""));
			}
		} else {
			context.addMessage(this.getUicomp().getPassportFileUI().getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Some Error Occurred", "Please Upload Again"));
		}
		return "success";
	}

	public void addVisa() {
		System.out.println(uservisa);
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println(user);
		if (user.getOracleId() != null && uservisa.getCountry() != null && uservisa.getIssueDate() != null
				&& uservisa.getExpiryDate() != null) {
			if (this.ischeckId()) {
				uservisa uservisaLocal = new uservisa();
				uservisaLocal.setOracleId(user.getOracleId());
				uservisaLocal.setCountry(uservisa.getCountry());
				uservisaLocal.setIssueDate(uservisa.getIssueDate());
				uservisaLocal.setExpiryDate(uservisa.getExpiryDate());
				uservisaLocal.setEditable(true);
				if (visaList.add(uservisaLocal)) {
					context.addMessage(this.getUicomp().getVisaStatus().getClientId(context),
							new FacesMessage(FacesMessage.SEVERITY_INFO, "",
									"Successfully Added Visa For Country" + "-" + uservisaLocal.getCountry()));
					uservisa.setCountry(null);
				} else {
					context.addMessage(this.getUicomp().getVisaStatus().getClientId(context),
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "Some Error Occurred", "Please Add Again"));
				}
			} else {
				context.addMessage(this.getUicomp().getVisaStatus().getClientId(context),
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "OracleId Already Exists"));
			}
		} else {
			context.addMessage(this.getUicomp().getVisaStatus().getClientId(context),
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Please Select All Required Fields"));
		}
	}

	private void createLogin() {
		try {
			login.setOracleID(user.getOracleId());
			login.setPassword(SecurityUtil.encrypt(login.getPassword()));
			login.setLoginAttempts(0);
			login.setLastLogin(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		// return login;
	}

	private boolean confirmOracleIdInVisaAndLogin(login login) {
		Iterator iterator = visaList.iterator();
		while (iterator.hasNext()) {
			uservisa uservisa = (uservisa) iterator.next();
			if (!login.getOracleID().equals(uservisa.getOracleId())) {
				System.out.println("in if");
				visaList.clear();
				return false;
			}
		}
		return true;
	}

	// called when Submit button is pressed
	public String submitRegAction() {
		FacesContext context = FacesContext.getCurrentInstance();

		formatData();
		createLogin();

		if (validDates.equals("right") && validJoiningDate.equals("right")) {
			if (this.confirmOracleIdInVisaAndLogin(login)) {
				if (userBo.addUser(user, login, visaList)) {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration successful.",
							"Enter credentials to login"));
					return "login";
				} else {
					this.setValidInfo(false);
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operation failed!",
							"Previous operation could not be performed due to error in saving to Database.Possible reasons for faliure: Oracle ID already exists or Database exception."));
					return "fail";
				}
			} else {
				this.setValidInfo(false);
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL,
								"Operation failed!(Different OracleId while adding visa and registring complete form)",
								"Please Add Visa Details Again"));
				return "fail";

			}
		}

		else {
			if (validDates.equals("wrong")) {
				this.setValidInfo(false);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operation failed!",
						"Issued Date should be preceded by Expiry Date...Please select both dates again"));
				return "fail";
			} else {
				if (validJoiningDate.equals("wrong")) {
					this.setValidInfo(false);
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operation failed!",
							"Birth date should be preceded by Joining date..Please select both dates again"));
					return "fail";
				} else {
					return null;
				}
			}
		}
	}

	public void formatData() {
		user.setFirstName(dataFormat.toUpperFirstInitial(user.getFirstName()));
		user.setMiddleName(dataFormat.toUpperFirstInitial(user.getMiddleName()));
		user.setLastName(dataFormat.toUpperFirstInitial(user.getLastName()));
		user.setPlaceOfIssue(dataFormat.toUpperFirstInitial(user.getPlaceOfIssue()));
		user.setFirstNomineeName(dataFormat.toUpperFirstInitial(user.getFirstNomineeName()));
		// this.setPassportLink("needs to be done");
		if (user.getPassportPdf() == null) {
			user.setPassportPdf("need to be done");
		}
		user.setProfilePicture("need to be done");
	}

	public void expectedJoiningDateValidation(String id3) {
		FacesContext context = FacesContext.getCurrentInstance();
		Date date = new Date();
		if (user.getDateOfBirth() != null) {
			if (!date.after(user.getDateOfBirth())) {
				context.addMessage(uicomp.getDobUI().getClientId(context), new FacesMessage(FacesMessage.SEVERITY_FATAL,
						"", "Birth Date Should Be Preceded By Current Date"));
				this.setValidJoiningDate("wrong");
			} else {
				if (user.getDateOfBirth() != null && user.getDateOfJoining() != null) {
					if (user.getDateOfJoining().getTime() - user.getDateOfBirth().getTime() <= 0) {

						if (id3.equals("dobinput-msg")) {
							context.addMessage(uicomp.getDobUI().getClientId(context), new FacesMessage(
									FacesMessage.SEVERITY_FATAL, "", "Birth date should be preceded by Joining date"));
						} else {
							context.addMessage(uicomp.getDojUI().getClientId(context), new FacesMessage(
									FacesMessage.SEVERITY_FATAL, "", "Birth date should be preceded by Joining date"));
						}
						this.setValidJoiningDate("wrong");

					} else {
						this.setValidJoiningDate("right");
					}
				}
			}
		}
	}

	public String moveToLogin() {
		if (user.getPassportPdf() != null) {
			String filepath = null;
			try {
				filepath = FileUtils.getFilePath("link");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileUtils.clearFilesWithSameName(filepath, type, filename);
		}
		return "login";
	}
}
