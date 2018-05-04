package com.diebold.travellogger.bo;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.util.HSSFColor.GOLD;

import com.diebold.travellogger.daoImpl.AddTravelLogDaoImp;
import com.diebold.travellogger.mail.EmailMessages;
import com.diebold.travellogger.mail.SendMailUtility;
import com.diebold.travellogger.pojo.Mail;
import com.diebold.travellogger.pojo.addTravel;
import com.diebold.travellogger.validations.PassportDate;

public class AddTravelLogBoImp implements AddTravelLogBo{
	private String managerName;
	private String managerEmail;
	private String country;
	private boolean visaDateValidation;
	private String applyVisa;
	
	
	
	public boolean isVisaDateValidation() {
		return visaDateValidation;
	}

	public void setVisaDateValidation(boolean visaDateValidation) {
		this.visaDateValidation = visaDateValidation;
	}

	public String getApplyVisa() {
		return applyVisa;
	}

	public void setApplyVisa(String applyVisa) {
		this.applyVisa = applyVisa;
	}

	@Override
	public List<String> getCountry() {
		return new AddTravelLogDaoImp().getCountry();
	}

	@Override
	public List<String> getProjectName() {
		return new AddTravelLogDaoImp().getProjectName();
	}

	@Override
	public List<String> getApprovalStatus() {
		return new AddTravelLogDaoImp().getApprovalStatus();
	}

	@Override
	public List<String> getCostCoveredBy() {
		return new AddTravelLogDaoImp().getCostCoveredBy();
	}

	@Override
	public List<String> getTravellerName(String name) {
		return new AddTravelLogDaoImp().getTravellerName(name);
	}

	@Override
	public boolean validateUser(String name) {
		return new AddTravelLogDaoImp().validateUser(name);
	}

	@Override
	public String getProjectCode(String projectName) {
		return new AddTravelLogDaoImp().getProjectCode(projectName);
	}

	@Override
	public Date[] expiryDate(String country) {
		return new AddTravelLogDaoImp().expiryDate(country);
	}

	@Override
	public List<String> getAgency() {
		return new AddTravelLogDaoImp().getAgency();
	}

	@Override
	public String getVisaAgencyDetails(String agencyName) {
		return new AddTravelLogDaoImp().getVisaAgencyDetails(agencyName);
	}

	@Override
	public Mail getMailDetails(Mail mail, String approvalName, String travellerName,String startdate, String endDate,String country,String scopeOfWork, String projectCode) {
		if(approvalName == "EA"){
			return this.getEaMail(mail, travellerName, startdate, endDate, country, scopeOfWork, projectCode);
		}
		
		if(approvalName == "PA"){
			return this.getPaMail(mail, travellerName, startdate, endDate, country, scopeOfWork, projectCode);
		}
		
		if(approvalName == "LA"){
			return this.getLaMail(mail, travellerName, startdate, endDate, country, scopeOfWork, projectCode);
		}
		
		if(approvalName == "PS"){
			return this.getPsMail(mail, travellerName, startdate, endDate, country, scopeOfWork, projectCode);
		}
			
		return null;
	}
	
	public Mail getEaMail(Mail mail,String travellerName,String startdate, String endDate,String country,String scopeOfWork, String projectCode){
		String[] x = new AddTravelLogDaoImp().getEADetails(projectCode).split(";");
		String link = "";
		this.managerEmail = x[6];
		this.managerName = x[5];
		String message = EmailMessages.getProjectLevelApprovalEmail();
		message = this.getMessageStringReplaced(message, x[0], "#ProjectManagementHeadName#", travellerName, startdate, endDate, managerName, country, link, scopeOfWork);
		
		mail.setDailogHeader("Send Mail For Engagement Agreement Approval");
		mail.setTo((x[1]).replaceAll("//s", ""));
		mail.setCc((x[2]+","+managerEmail).replaceAll("//s", ""));
		mail.setBcc("".replaceAll("//s", ""));
		mail.setSubject("Engageent Agreement Approval For Project - "+x[3]);
		mail.setBody(message);
		return mail;
	}
	
	public Mail getPaMail(Mail mail,String travellerName,String startdate, String endDate,String country,String scopeOfWork, String projectCode){
		String[] x = new AddTravelLogDaoImp().getPADetails(projectCode).split(";");
		String link = "";
		this.managerEmail = x[6];
		this.managerName = x[5];
		String message = EmailMessages.getProjectLevelApprovalEmail();
		message = this.getMessageStringReplaced(message, x[0], "#ProjectManagementHeadName#", travellerName, startdate, endDate, managerName, country, link, scopeOfWork);
		
		mail.setDailogHeader("Send Mail For Project Level Approval");
		mail.setTo(x[1].replaceAll("//s", ""));
		mail.setCc((x[2]+","+managerEmail).replaceAll("//s", ""));
		mail.setBcc(("").replaceAll("//s", ""));
		mail.setSubject("Project Level Approval For Project - "+x[3]);
		mail.setBody(message);
		return mail;
	}

	public Mail getLaMail(Mail mail,String travellerName,String startdate, String endDate,String country,String scopeOfWork, String projectCode){
		String[] x = new AddTravelLogDaoImp().getLADetails(projectCode).split(";");
		String link = "";
		this.managerEmail = x[6];
		this.managerName = x[5];
		String message = EmailMessages.getLocationLevelApprovalEmail();
		message = this.getMessageStringReplaced(message, x[0], "#LocationHeadName#", travellerName, startdate, endDate, managerName, country, link, scopeOfWork);
		
		mail.setDailogHeader("Send Mail For Location Level Approval");
		mail.setTo(x[1]);
		mail.setCc(x[2]+","+managerEmail);
		mail.setBcc("");
		mail.setSubject("Location Level Approval For Project - "+x[3]);
		mail.setBody(message);
		return mail;
	}
	
	public Mail getPsMail(Mail mail,String travellerName,String startdate, String endDate,String country,String scopeOfWork, String projectCode){
		String[] x = new AddTravelLogDaoImp().getPsDetails(projectCode).split(";");
		String link = "";
		this.managerEmail = x[6];
		this.managerName = x[5];
		String message = EmailMessages.getPsApprovalEmail();
		message = this.getMessageStringReplaced(message, x[0], "#PSHeadName#", travellerName, startdate, endDate, managerName, country, link, scopeOfWork);
		
		mail.setDailogHeader("Send Mail For PS Head Approval");
		mail.setTo(x[1]);
		mail.setCc(x[2]+","+managerEmail);
		mail.setBcc("");
		mail.setSubject("Ps Head Approval For Project - "+x[3]);
		mail.setBody(message);
		return mail;
	}

	public String getMessageStringReplaced(String message, String Name, String headName, String travellerName, String startdate, String endDate, String managerName, String country, String link, String scopeOfWork){
		message = Pattern.compile(headName).matcher(message).replaceAll(Name);
		travellerName = travellerName.substring(0, travellerName.length()-9);
		message = Pattern.compile("#ColleagueName#").matcher(message).replaceAll(travellerName);
		message = Pattern.compile("#TravelStartDate#").matcher(message).replaceAll(startdate);
		message = Pattern.compile("#TravelEndDate#").matcher(message).replaceAll(endDate);
		message = Pattern.compile("#ManagerName#").matcher(message).replaceAll(managerName);
		message = Pattern.compile("#TravelDestination#").matcher(message).replaceAll(country);
		message = Pattern.compile("#link#").matcher(message).replaceAll(link);
		message = Pattern.compile("#ScopeOfWork#").matcher(message).replaceAll(scopeOfWork);
		return message;
	}

	@Override
	public boolean insert(addTravel object) {
		return new AddTravelLogDaoImp().insert(object);
	}

	@Override
	public addTravel validateVisa(addTravel travel) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession Session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Date passExp=(Date) Session.getAttribute("passportExpiry");
		Date[] expire = this.expiryDate(travel.getTravelDestination());
		this.setApplyVisa("");
		travel.setVisaStatus("");
		
		if(passExp == null){
			this.setApplyVisa("Traveller doesn't have Passport");
            visaDateValidation = false;
            return travel;
		}
		
		if((passExp.getTime() - travel.getTravelEndDate().getTime()) >0)
		{	
			
        if (expire != null) {
             int validateDate = PassportDate.validateDate(travel.getTravelStartDate(), travel.getTravelEndDate(), expire[0], expire[1]);
             
             if (validateDate == 0) {
            	 travel.setVisaStatus("Ready"); 
            	 visaDateValidation = true;
             } else if (validateDate == 1) {            	 
                 this.setApplyVisa("Visa Needs To Be Applied, Click Here");
                 visaDateValidation = true;
             } else if (validateDate == 2) {
            	 travel.setVisaStatus("Please Check Entered Dates");
                 visaDateValidation = false;
             }
        } else {             
             if(travel.getTravelEndDate().getTime() - travel.getTravelStartDate().getTime() <= 0){
            	 travel.setVisaStatus("Please Check Entered Dates");
                 visaDateValidation = false;
             }else{
                 this.setApplyVisa("Visa Needs To Be Applied, Click Here");
                 visaDateValidation = true;
             }
        }
		}else{
			this.setApplyVisa("Passport will expire before Entered Date");
            visaDateValidation = false;
		}
		
        return travel;
	}

	@Override
	public void sendRegistrationEmail(String mail) {
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

	@Override
	public addTravel sendVisaEmail(String agency, addTravel travel) {
		HttpSession Session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		if(agency != ""){
			String[] Details = this.getVisaAgencyDetails(agency).split(";");			
			new SendMailUtility().applyForVisa(travel.getTravellerName(), travel.getTravelDestination(), travel.getTravelStartDate(), travel.getTravelEndDate(), Details[0], (String) Session.getAttribute("travellerEmail"), (String) Session.getAttribute("managerName"), Details[1], (String) Session.getAttribute("managerEmail"));
			FacesContext.getCurrentInstance().addMessage("dlg2Message", new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail has been Send", " "));
			travel.setVisaStatus("Applied For Visa"); 
			setApplyVisa("");
			visaDateValidation = true;
		}else {
			FacesContext.getCurrentInstance().addMessage("dlg2Message", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Please select Email", " "));
			visaDateValidation = false;
			this.setApplyVisa("Visa Needs To Be Applied, Click Here");
			travel.setVisaStatus("");
		}		
		return travel;
	}

	@Override
	public void submitForm(boolean validation, addTravel travel, String applyVisa) {
		HttpSession Session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		if (validation) {
			travel.setTravellerName(travel.getTravellerName().substring(0, travel.getTravellerName().length()-9));
			if(applyVisa=="Visa Needs To Be Applied, Click Here"){
				travel.setVisaStatus("Visa Needs To Be Applied");
			}			
			if (this.insert(travel)) {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation Successful.",
						"Travel Log has been added.<a href='addTravel.xhtml'> Click here to add more"));				
				Session.removeAttribute("designation");
			} else {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operation failed!",
						"Previous operation could not be performed due to some error, please try again."));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operation failed!", "Please check entered dates Or Passport may have expired"));
		}
	}
}
