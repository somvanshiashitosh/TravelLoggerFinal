package com.diebold.travellogger.beans;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.diebold.travellogger.bo.AddTravelBoImp;
import com.diebold.travellogger.bo.ProjectBOImpl;
import com.diebold.travellogger.daoImpl.CostCoveredImp;
import com.diebold.travellogger.daoImpl.CountryDaoImp;
import com.diebold.travellogger.daoImpl.StatusImp;
import com.diebold.travellogger.daoImpl.TravelAgencyDAOImpl;
import com.diebold.travellogger.mail.SendMailUtility;
import com.diebold.travellogger.pojo.CostCovered;
import com.diebold.travellogger.pojo.Project;
import com.diebold.travellogger.pojo.Status;
import com.diebold.travellogger.pojo.addTravel;
import com.diebold.travellogger.pojo.country;
import com.diebold.travellogger.validations.PassportDate;

@ManagedBean
@ViewScoped
public class addTravelBean {
	private int oracleId;
	private String travellerName;
	private String customerName;
	private String projectName;
	private String projectCode;
	private String designation;
	private List<String> designationList;
	private String travelDestinationString;
	private List<country> travelDestination;
	private Date travelStartDate;
	private Date travelEndDate;
	private String visaStatusString;
	private String validVisa;
	private String invalidVisa;
	private Date projectInitiationDate;
	private Date projectConfirmationDate;
	private String costCoveredString;
	private List<CostCovered> costCovered;
	private String scopeWork;
	private String engagementAgreementString;
	private List<Status> engagementAgreement;
	private String projectLevelString;
	private List<Status> projectLevel;
	private String locationHeadString;
	private List<Status> locationHead;
	private String psHeadString;
	private List<Status> psHead;
	private String inValidateUserName;
	private List<Project> projectList;
	private String registerUserEmail;
	private String agency;
	private List<String> agencyList;
	private boolean dateCheckOnSubmit;
	private List<String> nameAutoComplete;
	private boolean visaDateValidation;
	
	HttpSession Session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

	@PostConstruct
	public void init() {
		travelDestination = new CountryDaoImp().getCountry();
		costCovered = new CostCoveredImp().getCostCoveredBy();
		psHead = locationHead = projectLevel = engagementAgreement = new StatusImp().getStatus();
		projectList = new ProjectBOImpl().getProject();
		agencyList = new TravelAgencyDAOImpl().getAgencyName();
	}

	public List<String> completeText(String query) {
		return new AddTravelBoImp().getTravellerName(query);
	}

	public String getTravellerName() {
		return travellerName;
	}

	public void setTravellerName(String travellerName) {
		this.travellerName = travellerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public Date getProjectInitiationDate() {
		return projectInitiationDate;
	}

	public void setProjectInitiationDate(Date projectInitiationDate) {
		this.projectInitiationDate = projectInitiationDate;
	}

	public Date getProjectConfirmationDate() {
		return projectConfirmationDate;
	}

	public void setProjectConfirmationDate(Date projectConfirmationDate) {
		this.projectConfirmationDate = projectConfirmationDate;
	}

	public String getScopeWork() {
		return scopeWork;
	}

	public void setScopeWork(String scopeWork) {
		this.scopeWork = scopeWork;
	}

	public List<country> getTravelDestination() {
		return travelDestination;
	}

	public void setTravelDestination(List<country> travelDestination) {
		this.travelDestination = travelDestination;
	}

	public List<CostCovered> getCostCovered() {
		return costCovered;
	}

	public void setCostCovered(List<CostCovered> costCovered) {
		this.costCovered = costCovered;
	}

	public List<Status> getEngagementAgreement() {
		return engagementAgreement;
	}

	public void setEngagementAgreement(List<Status> engagementAgreement) {
		this.engagementAgreement = engagementAgreement;
	}

	public List<Status> getProjectLevel() {
		return projectLevel;
	}

	public void setProjectLevel(List<Status> projectLevel) {
		this.projectLevel = projectLevel;
	}

	public List<Status> getLocationHead() {
		return locationHead;
	}

	public void setLocationHead(List<Status> locationHead) {
		this.locationHead = locationHead;
	}

	public List<Status> getPsHead() {
		return psHead;
	}

	public void setPsHead(List<Status> psHead) {
		this.psHead = psHead;
	}

	public String getTravelDestinationString() {
		return travelDestinationString;
	}

	public void setTravelDestinationString(String travelDestinationString) {
		this.travelDestinationString = travelDestinationString;
	}

	public String getEngagementAgreementString() {
		return engagementAgreementString;
	}

	public void setEngagementAgreementString(String engagementAgreementString) {
		this.engagementAgreementString = engagementAgreementString;
	}

	public String getProjectLevelString() {
		return projectLevelString;
	}

	public void setProjectLevelString(String projectLevelString) {
		this.projectLevelString = projectLevelString;
	}

	public String getLocationHeadString() {
		return locationHeadString;
	}

	public void setLocationHeadString(String locationHeadString) {
		this.locationHeadString = locationHeadString;
	}

	public String getPsHeadString() {
		return psHeadString;
	}

	public void setPsHeadString(String psHeadString) {
		this.psHeadString = psHeadString;
	}

	public String getVisaStatusString() {
		return visaStatusString;
	}

	public void setVisaStatusString(String visaStatusString) {
		this.visaStatusString = visaStatusString;
	}

	public String getCostCoveredString() {
		return costCoveredString;
	}

	public void setCostCoveredString(String costCoveredString) {
		this.costCoveredString = costCoveredString;
	}

	public String getInValidateUserName() {
		return inValidateUserName;
	}

	public void setInValidateUserName(String inValidateUserName) {
		this.inValidateUserName = inValidateUserName;
	}

	public String getValidVisa() {
		return validVisa;
	}

	public void setValidVisa(String validVisa) {
		this.validVisa = validVisa;
	}

	public String getInvalidVisa() {
		return invalidVisa;
	}

	public void setInvalidVisa(String invalidVisa) {
		this.invalidVisa = invalidVisa;
	}

	public int getOracleId() {
		return oracleId;
	}

	public void setOracleId(int oracleId) {
		this.oracleId = oracleId;
	}

	public List<String> getDesignationList() {
		return designationList;
	}

	public void setDesignationList(List<String> designationList) {
		this.designationList = designationList;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public String getRegisterUserEmail() {
		return registerUserEmail;
	}

	public void setRegisterUserEmail(String registerUserEmail) {
		this.registerUserEmail = registerUserEmail;
	}

	public List<String> getAgencyList() {
		return agencyList;
	}

	public void setAgencyList(List<String> agencyList) {
		this.agencyList = agencyList;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public boolean isDateCheckOnSubmit() {
		return dateCheckOnSubmit;
	}

	public void setDateCheckOnSubmit(boolean dateCheckOnSubmit) {
		this.dateCheckOnSubmit = dateCheckOnSubmit;
	}

	public List<String> getNameAutoComplete() {
		return nameAutoComplete;
	}

	public void setNameAutoComplete(List<String> nameAutoComplete) {
		this.nameAutoComplete = nameAutoComplete;
	}

	public void submit() {
		addTravel data = createPojo();
		
		if (dateCheckOnSubmit && visaDateValidation) {
			if (new AddTravelBoImp().insertManageTavel(data)) {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation Successful.",
						"Travel Log has been added.<a href='addTravel.xhtml'> Click here to add more"));
				Session.removeAttribute("id");
				Session.removeAttribute("designation");
				Session.removeAttribute("travellerEmail");
			} else {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation failed!",
						"Previous operation could not be performed due to some error, please try again."));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation failed!", "Please check inputed dates"));
		}
	}

	public void validateUser() {
		if (new AddTravelBoImp().validateUser(this.getTravellerName())) {
			this.setDesignation((String) Session.getAttribute("designation"));
			this.setInValidateUserName("");
		} else {
			this.setInValidateUserName("User not registered, Click here to register");
			this.setDesignation("");
		}
	}

	public void validatePassport() {
        this.setValidVisa("");
        this.setInvalidVisa("");
        Date expire = new AddTravelBoImp().expiryDate(this.getTravelDestinationString());
        if (expire != null) {
             int validateDate = PassportDate.validateDate(this.getTravelStartDate(), this.getTravelEndDate(), expire, new Date());
             if (validateDate == 0) {
                 this.setVisaStatusString("Ready");
                 this.setValidVisa("Ready");
                 visaDateValidation = true;               
             } else if (validateDate == 1) {
                 this.setInvalidVisa("Visa Needs To Be Applied, Click Here");
                 this.setVisaStatusString("Visa Needs To Be Applied");
                 visaDateValidation = true;
             } else if (validateDate == 2) {
                 this.setValidVisa("Please Check Entered Dates");
                 visaDateValidation = false;
             }
        } else {
             
             if(this.getTravelEndDate().getTime() - this.getTravelStartDate().getTime() <= 0){
                 this.setValidVisa("Please Check Entered Dates");
                 visaDateValidation = false;
             }else{
                 this.setInvalidVisa("Visa Needs To Be Applied, Click Here");
                 this.setVisaStatusString("Visa Needs To Be Applied");
                 visaDateValidation = true;
             }
        }
    }


	public addTravel createPojo() {
		addTravel manageInstance = new addTravel();
		travellerName = travellerName.substring(0, travellerName.length() - 9);
		manageInstance.setTravellerName(travellerName);
		manageInstance.setCustomerName(customerName);
		manageInstance.setProjectName(projectName);
		manageInstance.setProjectCode(projectCode);
		manageInstance.setDesignation(designation);
		manageInstance.setTravelDestination(travelDestinationString);
		manageInstance.setTravelStartDate(travelStartDate);
		manageInstance.setTravelEndDate(travelEndDate);
		manageInstance.setVisaStatus(visaStatusString);
		manageInstance.setProjectInitiationDate(projectInitiationDate);
		manageInstance.setProjectConfirmationDate(projectConfirmationDate);
		manageInstance.setCostCovered(costCoveredString);
		manageInstance.setScopeWork(scopeWork);
		manageInstance.setEngagementAgreement(engagementAgreementString);
		manageInstance.setProjectLevel(projectLevelString);
		manageInstance.setLocationHead(locationHeadString);
		manageInstance.setPsHead(psHeadString);
		return manageInstance;
	}

	public String applyForVisa() {
		return "Success";
	}

	public String registerUser() {
		return "register";
	}

	public void updateProjectcode() {
		String code = new ProjectBOImpl().getProjectCode(this.getProjectName());
		this.setProjectCode(code);
	}

	public void expectedDateValidation() {
		if (this.getProjectConfirmationDate().getTime() - this.getProjectInitiationDate().getTime() <= 0) {
			this.setDateCheckOnSubmit(false);
		} else {
			this.setDateCheckOnSubmit(true);
		}
	}

	public void sendRegistrationEmail() {
		String mail = this.getRegisterUserEmail().replaceAll("\\s","");
		String[] mailArray = mail.split(",");
		int flag = 0;
		
		for(int i=0; i<mailArray.length; i++){
			if(Pattern.compile("[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]",
					Pattern.CASE_INSENSITIVE).matcher(mailArray[i]).matches()){
				flag++;
			}else{
				break;
			}
		}
		if(flag != mailArray.length){
			FacesContext.getCurrentInstance().addMessage("dlgMessage", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invalid Email", " "));			
		}else{
			flag = 0;
			for(int i=0; i<mailArray.length; i++){
				new SendMailUtility().sendDispatchMail(mailArray[i]);
				flag++;
			}			
			if(flag == mailArray.length){
				FacesContext.getCurrentInstance().addMessage("dlgMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail has been Sent", " "));
			}else{
				FacesContext.getCurrentInstance().addMessage("dlgMessage", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error While Sending Email", " "));
			}
		}		
	}

	public void sendVisaEmail() {
		if (this.getAgency() != "") {
			String[] Details = new AddTravelBoImp().getVisaAgencyEmail(this.getAgency()).split(";");
			
			new SendMailUtility().applyForVisa(this.getTravellerName(), this.getTravelDestinationString(), this.getTravelStartDate(), this.getTravelEndDate(), Details[0], (String) Session.getAttribute("travellerEmail"), (String) Session.getAttribute("managerName"), Details[1], (String) Session.getAttribute("managerEmail"));
			FacesContext.getCurrentInstance().addMessage("dlg2Message", new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail has been Send", " "));
			this.setVisaStatusString("Applied For Visa");
			this.setValidVisa("Applied For Visa");
			visaDateValidation = true;
			this.setInvalidVisa("");
		} else {
			FacesContext.getCurrentInstance().addMessage("dlg2Message", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Please select Email", " "));
			visaDateValidation = false;
		}
	}

}
