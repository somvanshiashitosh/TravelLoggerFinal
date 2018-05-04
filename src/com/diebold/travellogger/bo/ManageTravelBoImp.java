package com.diebold.travellogger.bo;

import java.util.List;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.diebold.travellogger.beans.ManageTravelBean;
import com.diebold.travellogger.daoImpl.ManageTravelImp;
import com.diebold.travellogger.mail.EmailMessages;
import com.diebold.travellogger.mail.SendMailUtility;
import com.diebold.travellogger.pojo.addTravel;

public class ManageTravelBoImp implements ManageTravelBo{
	private String PSCC;
	private String LHCC;
	private String PLCC;
	
	public String getPSCC() {
		return PSCC;
	}

	public void setPSCC(String pSCC) {
		PSCC = pSCC;
	}

	public String getLHCC() {
		return LHCC;
	}

	public void setLHCC(String lHCC) {
		LHCC = lHCC;
	}

	public String getPLCC() {
		return PLCC;
	}

	public void setPLCC(String pLCC) {
		PLCC = pLCC;
	}

	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	
	
	@Override
	public List<addTravel> getAllTravels() {
		return new ManageTravelImp().getAllTravels();
	}

	@Override
	public void EngagementLink(int id) {
		new ManageTravelImp().EngagementLink(id, "sent for approval");
	}

	@Override
	public void projectApprovalLink(int id) {		
		new ManageTravelImp().projectApprovalLink(id, "sent for approval");
	}

	@Override
	public void locationApprovalLink(int id) {
		new ManageTravelImp().locationApprovalLink(id, "sent for approval");
	}

	@Override
	public void psApprovalLink(int id) {		
		new ManageTravelImp().psApprovalLink(id, "sent for approval");
	}

	@Override
	public ManageTravelBean getEADetails(int id) {
		ManageTravelBean dataObject = new ManageTravelBean();
		String eaDetails = new ManageTravelImp().getEADetails(id);
		String[] eaDetailsArray = eaDetails.split(";");
		String userdetails = this.getTravellerDetails(id);
		String[] userDetailsArray = userdetails.split(";");
		String message = EmailMessages.getProjectLevelApprovalEmail();
		
		dataObject.setEngagementAgreement(eaDetailsArray[0]);
		dataObject.setEngagementAgreementEmail(eaDetailsArray[1]);
		dataObject.setEACC(eaDetailsArray[2]);
		dataObject.setProjectName(eaDetailsArray[3]);
		dataObject.setTravelDestination(eaDetailsArray[4]);
		dataObject.setEASubject("Engageent Agreement Approval For Project - "+ dataObject.getProjectName());
		dataObject.setTravellerEmail(userDetailsArray[3]);
		//message = this.getMessageStringReplaced(message, dataObject.getEngagementAgreement(), userDetailsArray[0], userDetailsArray[1], userDetailsArray[2], dataObject.getProjectName(), dataObject.getTravelDestination());
		dataObject.setEABody(message);
		
		return dataObject;
	}

	@Override
	public ManageTravelBean getPADetails(int id) {
		ManageTravelBean dataObject = new ManageTravelBean();
		String projectDetails = new ManageTravelImp().getPADetails(id);
		String[] projectDetailsArray = projectDetails.split(";");
		String userdetails = this.getTravellerDetails(id);
		String[] userDetailsArray = userdetails.split(";");
		String message = EmailMessages.getProjectLevelApprovalEmail();
		String link = "http://localhost:8080/TravelLogger/addTravel.xhtml";
		
		dataObject.setProjectLevel(projectDetailsArray[0]);
		dataObject.setProjectLevelEmail(projectDetailsArray[1]);
		dataObject.setPLCC(projectDetailsArray[2]);
		dataObject.setProjectName(projectDetailsArray[3]);
		dataObject.setTravelDestination(projectDetailsArray[4]);
		dataObject.setPlSubject("Project Level Approval For Project - "+dataObject.getProjectName());
		dataObject.setTravellerEmail(userDetailsArray[4]);
		dataObject.setManagerName(projectDetailsArray[5]);
		dataObject.setManagerEmail(projectDetailsArray[6]);
		message = this.getMessageStringReplaced(message, projectDetailsArray[0], "#ProjectManagementHeadName#", userDetailsArray[0], userDetailsArray[1], userDetailsArray[2], projectDetailsArray[5], projectDetailsArray[4], link, userDetailsArray[3]);
		dataObject.setPlBody(message);	
		
		return dataObject;
	}

	@Override
	public ManageTravelBean getLADetails(int id) {
		ManageTravelBean dataObject = new ManageTravelBean();
		String locationDetails = new ManageTravelImp().getLADetails(id);		
		String locationDetailsArray[] = locationDetails.split(";");
		String userdetails = new ManageTravelBoImp().getTravellerDetails(id);
		String[] userDetailsArray = userdetails.split(";");
		String message = EmailMessages.getLocationLevelApprovalEmail();
		String link = "http://localhost:8080/TravelLogger/addTravel.xhtml";
		
		dataObject.setLocationHeadEmail(locationDetailsArray[1]);
		dataObject.setLHCC(locationDetailsArray[2]);
		dataObject.setLocationHead(locationDetailsArray[0]);
		dataObject.setProjectName(locationDetailsArray[3]);
		dataObject.setTravelDestination(locationDetailsArray[4]);
		dataObject.setLhSubject("Location Head Approval For Project - "+dataObject.getProjectName());
		dataObject.setTravellerEmail(userDetailsArray[3]);
		dataObject.setManagerName(locationDetailsArray[5]);
		dataObject.setManagerEmail(locationDetailsArray[6]);
		message = this.getMessageStringReplaced(message, locationDetailsArray[0], "#LocationHeadName#", userDetailsArray[0], userDetailsArray[1], userDetailsArray[2], locationDetailsArray[5], locationDetailsArray[4], link, userDetailsArray[3]);
		dataObject.setLhBody(message);
		
		return dataObject;
	}

	@Override
	public ManageTravelBean getPSDetails(int id) {  
		ManageTravelBean dataObject = new ManageTravelBean();
		String Psdetails = new ManageTravelImp().getPSDetails(id);
		String[] PsDetailsArray = Psdetails.split(";");
		String userdetails = new ManageTravelBoImp().getTravellerDetails(id);
		String[] userDetailsArray = userdetails.split(";");
		String message = EmailMessages.getPsApprovalEmail();
		String link = "http://localhost:8080/TravelLogger/addTravel.xhtml";
		
		dataObject.setPsHeadEmail(PsDetailsArray[1]);
		dataObject.setPSCC(PsDetailsArray[2]);
		dataObject.setPsHead(PsDetailsArray[0]);
		dataObject.setProjectName(PsDetailsArray[3]);
		dataObject.setTravelDestination(PsDetailsArray[4]);
		dataObject.setPsSubject("PS Head Approval For Project - "+dataObject.getProjectName());		
		dataObject.setTravellerEmail(userDetailsArray[3]);
		dataObject.setManagerName(PsDetailsArray[5]);
		dataObject.setManagerEmail(PsDetailsArray[6]);
		message = this.getMessageStringReplaced(message, PsDetailsArray[0], "#PSHeadName#", userDetailsArray[0], userDetailsArray[1], userDetailsArray[2], PsDetailsArray[5], PsDetailsArray[4], link, userDetailsArray[3]);
		dataObject.setPsBody(message);
		
		return dataObject;
	}

	@Override
	public String getTravellerDetails(int id) {
		return new ManageTravelImp().getTravellerDetails(id);
	}
	
	public String getMessageStringReplaced(String message, String Name, String headName, String travellerName, String startdate, String endDate, String managerName, String country, String link, String scopeOfWork){
		message = Pattern.compile(headName).matcher(message).replaceAll(Name);
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
	public boolean sendAgencyMail(String agency, int id) {
		String[] Details = new AddTravelBoImp().getVisaAgencyEmail(agency).split(";");
		String travellerDetail = new ManageTravelImp().getTravellerDetails(id);
		String[] travellerDetailArray = travellerDetail.split(";");
		String[] countryAndManagerDetails = new ManageTravelImp().getEADetails(id).split(";");
		new SendMailUtility().applyForVisaFromManage(travellerDetailArray[0], countryAndManagerDetails[4], travellerDetailArray[1], travellerDetailArray[2], Details[0], travellerDetailArray[3], countryAndManagerDetails[5], Details[1], countryAndManagerDetails[6]);
		new ManageTravelImp().updateVisaStatus(id, "Applied For Visa");		
		return true;
	}
	

}
