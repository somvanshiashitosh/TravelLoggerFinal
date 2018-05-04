package com.diebold.travellogger.beans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.diebold.travellogger.bo.ManageTravelBoImp;
import com.diebold.travellogger.daoImpl.TravelAgencyDAOImpl;
import com.diebold.travellogger.mail.SendMailHTMLSupportBean;
import com.diebold.travellogger.pojo.addTravel;

@ManagedBean(name="DashBoard")
@ViewScoped
public class ManageTravelBean {
	private int id;
	private String travellerName;
	private String travellerEmail;
	private String customerName;
	private String projectName;
	private String projectCode;
	private String designation;
	private String travelDestination;
	private Date travelStartDate;
	private Date travelEndDate;
	private String visaStatus;
	private Date projectInitiationDate;
	private Date projectConfirmationDate;
	private String costCovered;
	private String scopeWork;
	private String engagementAgreement;
	private String projectLevel;
	private String locationHead;
	private String psHead;
	private List<addTravel> travels;
	private String engagementAgreementEmail;
	private String EACC;	
	private String EABCC;
	private String EASubject;
	private String EABody;
	private String projectLevelEmail;
	private String PlSubject;
	private String PLCC;
	private String PLBCC;
	private String PlBody;
	private String locationHeadEmail;
	private String LhSubject;
	private String LHCC;
	private String LHBCC;
	private String LhBody;
	private String psHeadEmail;
	private String PsSubject;
	private String PSCC;
	private String PSBCC;
	private String PsBody;
	private String hiddenID;
	private String agency;
	private List<String> agencyList;
	private String managerName;
	private String managerEmail;
	
	
	@PostConstruct
	public void init(){
		travels = new ManageTravelBoImp().getAllTravels();
		agencyList = new TravelAgencyDAOImpl().getAgencyName();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTravelDestination() {
		return travelDestination;
	}
	public void setTravelDestination(String travelDestination) {
		this.travelDestination = travelDestination;
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
	public String getVisaStatus() {
		return visaStatus;
	}
	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
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
	public String getCostCovered() {
		return costCovered;
	}
	public void setCostCovered(String costCovered) {
		this.costCovered = costCovered;
	}
	public String getScopeWork() {
		return scopeWork;
	}
	public void setScopeWork(String scopeWork) {
		this.scopeWork = scopeWork;
	}
	public String getEngagementAgreement() {
		return engagementAgreement;
	}
	public void setEngagementAgreement(String engagementAgreement) {
		this.engagementAgreement = engagementAgreement;
	}
	public String getProjectLevel() {
		return projectLevel;
	}
	public void setProjectLevel(String projectLevel) {
		this.projectLevel = projectLevel;
	}
	public String getLocationHead() {
		return locationHead;
	}
	public void setLocationHead(String locationHead) {
		this.locationHead = locationHead;
	}
	public String getPsHead() {
		return psHead;
	}
	public void setPsHead(String psHead) {
		this.psHead = psHead;
	}
	public List<addTravel> getTravels() {
		return travels;
	}
	public void setTravels(List<addTravel> travels) {
		this.travels = travels;
	}
		
	public String getEngagementAgreementEmail() {
		return engagementAgreementEmail;
	}

	public void setEngagementAgreementEmail(String engagementAgreementEmail) {
		this.engagementAgreementEmail = engagementAgreementEmail;
	}	
	public String getProjectLevelEmail() {
		return projectLevelEmail;
	}
	public void setProjectLevelEmail(String projectLevelEmail) {
		this.projectLevelEmail = projectLevelEmail;
	}
	public String getLocationHeadEmail() {
		return locationHeadEmail;
	}

	public void setLocationHeadEmail(String locationHeadEmail) {
		this.locationHeadEmail = locationHeadEmail;
	}
	public String getPsHeadEmail() {
		return psHeadEmail;
	}

	public void setPsHeadEmail(String psHeadEmail) {
		this.psHeadEmail = psHeadEmail;
	}
	public String getPLCC() {
		return PLCC;
	}
	public void setPLCC(String pLCC) {
		PLCC = pLCC;
	}
	public String getLHCC() {
		return LHCC;
	}
	public void setLHCC(String lHCC) {
		LHCC = lHCC;
	}
	public String getPSCC() {
		return PSCC;
	}
	public void setPSCC(String pSCC) {
		PSCC = pSCC;
	}	
	public String getEACC() {
		return EACC;
	}
	public void setEACC(String eACC) {
		EACC = eACC;
	}
	public String getHiddenID() {
		return hiddenID;
	}
	public void setHiddenID(String hiddenID) {
		this.hiddenID = hiddenID;
	}
	public String getPLBCC() {
		return PLBCC;
	}
	public void setPLBCC(String pLBCC) {
		PLBCC = pLBCC;
	}
	public String getLHBCC() {
		return LHBCC;
	}
	public void setLHBCC(String lHBCC) {
		LHBCC = lHBCC;
	}
	public String getPSBCC() {
		return PSBCC;
	}
	public void setPSBCC(String pSBCC) {
		PSBCC = pSBCC;
	}
	public String getPlSubject() {
		return PlSubject;
	}
	public void setPlSubject(String plSubject) {
		PlSubject = plSubject;
	}
	public String getLhSubject() {
		return LhSubject;
	}
	public void setLhSubject(String lhSubject) {
		LhSubject = lhSubject;
	}
	public String getPsSubject() {
		return PsSubject;
	}
	public void setPsSubject(String psSubject) {
		PsSubject = psSubject;
	}
	public String getPlBody() {
		return PlBody;
	}
	public void setPlBody(String plBody) {
		PlBody = plBody;
	}
	public String getLhBody() {
		return LhBody;
	}
	public void setLhBody(String lhBody) {
		LhBody = lhBody;
	}
	public String getPsBody() {
		return PsBody;
	}
	public void setPsBody(String psBody) {
		PsBody = psBody;
	}
	public String getTravellerEmail() {
		return travellerEmail;
	}
	public void setTravellerEmail(String travellerEmail) {
		this.travellerEmail = travellerEmail;
	}
	public String getEABCC() {
		return EABCC;
	}
	public void setEABCC(String eABCC) {
		EABCC = eABCC;
	}
	public String getEASubject() {
		return EASubject;
	}
	public void setEASubject(String eASubject) {
		EASubject = eASubject;
	}
	public String getEABody() {
		return EABody;
	}
	public void setEABody(String eABody) {
		EABody = eABody;
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

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public void populateEaApprovalDailog(){
		if(!this.getHiddenID().equals("Invalid")){
			ManageTravelBean eaApprovalData = new ManageTravelBoImp().getEADetails(Integer.parseInt(hiddenID));
			
			this.setEngagementAgreement(eaApprovalData.getEngagementAgreement());
			this.setProjectName(eaApprovalData.getProjectName());
			this.setTravelDestination(eaApprovalData.getTravelDestination());
			this.setEngagementAgreementEmail(eaApprovalData.getEngagementAgreementEmail());
			this.setTravellerEmail(eaApprovalData.getTravellerEmail());
			this.setEACC(eaApprovalData.getEACC().replaceAll(" ", ""));
			this.setEASubject(eaApprovalData.getEASubject());
			this.setEABody(eaApprovalData.getEABody());			
			
			RequestContext.getCurrentInstance().execute("PF('dlg').show();");
		}
	}
	
	
	public void sendEaApprovalMail(){
		new SendMailHTMLSupportBean().send(travellerEmail, getEngagementAgreementEmail(), getEACC(), getEABCC(),getEASubject(), getEABody(), "localhost");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail has been Send", " "));
		new ManageTravelBoImp().EngagementLink(Integer.parseInt(this.getHiddenID()));
		init();
	}
	
	public void populateProjectApprovalDailog(){
		if(!this.getHiddenID().equals("Invalid")){
			ManageTravelBean projectApprovalData = new ManageTravelBoImp().getPADetails(Integer.parseInt(hiddenID));
			
			this.setProjectLevel(projectApprovalData.getProjectLevel());
			this.setProjectName(projectApprovalData.getProjectName());
			this.setTravelDestination(projectApprovalData.getTravelDestination());
			this.setProjectLevelEmail(projectApprovalData.getProjectLevelEmail());
			this.setTravellerEmail(projectApprovalData.getTravellerEmail());
			this.setPLCC((projectApprovalData.getPLCC()+','+projectApprovalData.getManagerEmail()).replaceAll(" ", ""));
			this.setPlSubject(projectApprovalData.getPlSubject());
			this.setPlBody(projectApprovalData.getPlBody());
			
			RequestContext.getCurrentInstance().execute("PF('dlg2').show();");
		}
	}
	
	public void sendProjectApprovalEmail(){
		new SendMailHTMLSupportBean().send(travellerEmail, getProjectLevelEmail(), getPLCC(), getPLBCC(),getPlSubject(), getPlBody(), "localhost");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail has been Send", " "));
		new ManageTravelBoImp().projectApprovalLink(Integer.parseInt(this.getHiddenID()));
		init();
	}
	
	public void populateLocationApprovalDailog(){
		if(!this.getHiddenID().equals("Invalid")){
			ManageTravelBean locationApprovalData = new ManageTravelBoImp().getLADetails(Integer.parseInt(hiddenID));
						
			this.setLocationHead(locationApprovalData.getLocationHead());
			this.setProjectName(locationApprovalData.getProjectName());
			this.setTravelDestination(locationApprovalData.getTravelDestination());
			this.setLocationHeadEmail(locationApprovalData.getLocationHeadEmail());
			this.setTravellerEmail(locationApprovalData.getTravellerEmail());
			this.setLHCC((locationApprovalData.getLHCC()+','+locationApprovalData.getManagerEmail()).replaceAll(" ", ""));
			this.setLhSubject(locationApprovalData.getLhSubject());
			this.setLhBody(locationApprovalData.getLhBody());
			
			RequestContext.getCurrentInstance().execute("PF('dlg3').show();");
		}
	}
	
	public void sendLocationApprovalEmail(){
		new SendMailHTMLSupportBean().send(travellerEmail, getLocationHeadEmail(), getLHBCC(), getLHBCC(),getLhSubject(), getLhBody(), "localhost");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail has been Send", " "));
		new ManageTravelBoImp().locationApprovalLink(Integer.parseInt(this.getHiddenID()));
		init();
	}
	
	public void populatePsApprovalDailog(){
		if(!this.getHiddenID().equals("Invalid")){
			ManageTravelBean psApprovalData = new ManageTravelBoImp().getPSDetails(Integer.parseInt(hiddenID));			
			
			this.setPsHead(psApprovalData.getPsHead());
			this.setProjectName(psApprovalData.getProjectName());
			this.setTravelDestination(psApprovalData.getTravelDestination());
			this.setTravellerEmail(psApprovalData.getTravellerEmail());
			this.setPsHeadEmail(psApprovalData.getPsHeadEmail());
			this.setPSCC((psApprovalData.getPSCC()+','+psApprovalData.getManagerEmail()).replaceAll(" ", ""));
			this.setPsSubject(psApprovalData.getPsSubject());
			this.setPsBody(psApprovalData.getPsBody());
			
			RequestContext.getCurrentInstance().execute("PF('dlg4').show();");
			
		}
	}

	public void sendPsApprovalEmail(){
		new ManageTravelBoImp().psApprovalLink(Integer.parseInt(this.getHiddenID()));
		new SendMailHTMLSupportBean().send(travellerEmail, this.getPsHeadEmail(), this.getPSCC(), this.getPSBCC(), this.getPsSubject(), this.getPsBody(), "localhost");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail has been Send", " "));
		init();
	}
	
	public void updatePsApprovalLink(){
		System.out.println(this.getHiddenID());
		new ManageTravelBoImp().psApprovalLink(Integer.parseInt(this.getHiddenID()));
	}
	
	
	public void sendAgencyMail(){
		if(new ManageTravelBoImp().sendAgencyMail(this.getAgency(), Integer.parseInt(this.getHiddenID()))){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail has been Send", " "));
			init();
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fail To Send Mail", " "));
		}		
	}
	
	public void populateAgengyDailog(){
		RequestContext.getCurrentInstance().execute("PF('sendMailDailog').show();");
	}
	
	public String formateDate(Date d){
		return d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()-100);
	}
	
}
