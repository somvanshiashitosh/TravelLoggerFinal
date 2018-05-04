package com.diebold.travellogger.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import com.diebold.travellogger.bo.AddTravelLogBoImp;
import com.diebold.travellogger.mail.SendMailHTMLSupportBean;
import com.diebold.travellogger.pojo.Mail;
import com.diebold.travellogger.pojo.addTravel;

@ManagedBean(name="AddTravelLogBean")
@ViewScoped
public class AddTravelLogBean {
	private addTravel travel;
	private Mail mail;
	private String linkForRegisteration;
	private String applyVisa;
	private String newRegisterationEmail;
	private String agency;
	private boolean visaDateValidation;
	private List<String> countryList; 
	private List<String> projectNameList;
	private List<String> approvalStatusList;
	private List<String> costCoveredByList;
	private List<String> agencyList;
	private AddTravelLogBoImp Bo;
	private boolean validUser;
	private boolean enableRegistartion;
	
	HttpSession Session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	public AddTravelLogBean() {
		travel = new addTravel();
		mail = new Mail();
		Bo = new AddTravelLogBoImp();
		validUser = false;
	}

	@PostConstruct
	public void init(){		
		countryList = Bo.getCountry();
		projectNameList = Bo.getProjectName();
		approvalStatusList = Bo.getApprovalStatus();
		costCoveredByList = Bo.getCostCoveredBy();
		agencyList = Bo.getAgency();		
		travel.setProjectLevel("to be prepared");
		travel.setLocationHead("to be prepared");
		travel.setPsHead("to be prepared");		
	}	
	

	public List<String> searchUser(String query) {
		enableRegistartion = false;
		List<String> list = Bo.getTravellerName(query);
		
		if(list.isEmpty()){
			linkForRegisteration = "User not registered, Click here to register";
			enableRegistartion = true;
			validUser = false;
		}else{
			linkForRegisteration = "";
			validUser = true;
			travel.setDesignation("");
		}
		
		return list;
	}
	
	public addTravel getTravel() {
		return travel;
	}
	public void setTravel(addTravel travel) {
		this.travel = travel;
	}

	public List<String> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	public List<String> getProjectNameList() {
		return projectNameList;
	}

	public void setProjectNameList(List<String> projectNameList) {
		this.projectNameList = projectNameList;
	}

	public List<String> getApprovalStatusList() {
		return approvalStatusList;
	}

	public void setApprovalStatusList(List<String> approvalStatusList) {
		this.approvalStatusList = approvalStatusList;
	}

	public List<String> getCostCoveredByList() {
		return costCoveredByList;
	}

	public void setCostCoveredByList(List<String> costCoveredByList) {
		this.costCoveredByList = costCoveredByList;
	}
	
	public String getLinkForRegisteration() {
		return linkForRegisteration;
	}

	public void setLinkForRegisteration(String linkForRegisteration) {
		this.linkForRegisteration = linkForRegisteration;
	}

	public String getApplyVisa() {
		return applyVisa;
	}

	public void setApplyVisa(String applyVisa) {
		this.applyVisa = applyVisa;
	}

	public String getNewRegisterationEmail() {
		return newRegisterationEmail;
	}

	public void setNewRegisterationEmail(String newRegisterationEmail) {
		this.newRegisterationEmail = newRegisterationEmail;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public List<String> getAgencyList() {
		return agencyList;
	}

	public void setAgencyList(List<String> agencyList) {
		this.agencyList = agencyList;
	}
	
	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public boolean isValidUser() {
		return validUser;
	}

	public void setValidUser(boolean validUser) {
		this.validUser = validUser;
	}
	public boolean isEnableRegistartion() {
		return enableRegistartion;
	}

	public void setEnableRegistartion(boolean enableRegistartion) {
		this.enableRegistartion = enableRegistartion;
	}

	public void validateUser() {
		if (Bo.validateUser(travel.getTravellerName())) {
			travel.setDesignation((String) Session.getAttribute("designation"));
			this.setLinkForRegisteration("");
			this.setValidUser(true);
		} else {
			this.setLinkForRegisteration("User not registered, Click here to register");			
			travel.setDesignation("");
			this.setValidUser(false);
		}
	}
	
	public void updateProjectcode() {
		travel.setProjectCode(Bo.getProjectCode(travel.getProjectName()));
	}
	
	public void validateVisa(){
		Bo.setApplyVisa("");
		setApplyVisa("");
		travel.setVisaStatus("");  
		travel = Bo.validateVisa(travel);
		this.setApplyVisa(Bo.getApplyVisa());
		visaDateValidation = Bo.isVisaDateValidation();
	}
	
	public void sendRegistrationEmail() {		
		String mail = this.getNewRegisterationEmail().replaceAll("\\s","");
		Bo.sendRegistrationEmail(mail);		
	}
	
	public void sendVisaEmail() {
		this.setApplyVisa("");
		travel = Bo.sendVisaEmail(this.getAgency(), travel);
		this.setApplyVisa(Bo.getApplyVisa());
		visaDateValidation = Bo.isVisaDateValidation();
	}
	
	public void submitForm(){
		Bo.submitForm(visaDateValidation, travel, getApplyVisa());
	}
	
	public void populatePADailog(){
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String startDate = travel.getTravelStartDate().getDate()+"/"+(travel.getTravelStartDate().getMonth()+1)+"/"+(travel.getTravelStartDate().getYear()-100);
		String endDate = travel.getTravelEndDate().getDate()+"/"+(travel.getTravelEndDate().getMonth()+1)+"/"+(travel.getTravelEndDate().getYear()-100);
		mail = Bo.getMailDetails(mail, "PA", travel.getTravellerName(), startDate, endDate, travel.getTravelDestination(), travel.getScopeWork(), travel.getProjectCode());
		requestContext.execute("PF('sendMailApproval').show();");		
	}
	
	public void populateLADailog(){
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String startDate = travel.getTravelStartDate().getDate()+"/"+(travel.getTravelStartDate().getMonth()+1)+"/"+(travel.getTravelStartDate().getYear()-100);
		String endDate = travel.getTravelEndDate().getDate()+"/"+(travel.getTravelEndDate().getMonth()+1)+"/"+(travel.getTravelEndDate().getYear()-100);
		mail = Bo.getMailDetails(mail, "LA", travel.getTravellerName(), startDate, endDate, travel.getTravelDestination(), travel.getScopeWork(), travel.getProjectCode());
		requestContext.execute("PF('sendMailApproval').show();");		
	}
	
	public void populatePSDailog(){
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String startDate = travel.getTravelStartDate().getDate()+"/"+(travel.getTravelStartDate().getMonth()+1)+"/"+(travel.getTravelStartDate().getYear()-100);
		String endDate = travel.getTravelEndDate().getDate()+"/"+(travel.getTravelEndDate().getMonth()+1)+"/"+(travel.getTravelEndDate().getYear()-100);
		mail = Bo.getMailDetails(mail, "PS", travel.getTravellerName(), startDate, endDate, travel.getTravelDestination(), travel.getScopeWork(), travel.getProjectCode());
		requestContext.execute("PF('sendMailApproval').show();");		
	}
	
	public void sendApprovalMail(){
		new SendMailHTMLSupportBean().send((String) Session.getAttribute("travellerEmail"), mail.getTo(), mail.getCc(), mail.getBcc(), mail.getSubject(), mail.getBody(), "localhost");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail has been Send", " "));
		
		if(mail.getDailogHeader()=="Send Mail For Project Level Approval"){
			travel.setProjectLevel("sent for approval");
		}
		
		if(mail.getDailogHeader()=="Send Mail For Location Level Approval"){
			travel.setLocationHead("sent for approval");
		}
		
		if(mail.getDailogHeader()=="Send Mail For PS Head Approval"){
			travel.setPsHead("sent for approval");
		}
	}
}
