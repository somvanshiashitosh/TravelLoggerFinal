package com.diebold.travellogger.bo;

import java.util.Date;
import java.util.regex.Pattern;

import com.diebold.travellogger.beans.TravelInsuranceBean;
import com.diebold.travellogger.daoImpl.TravelInsuranceDaoImp;
import com.diebold.travellogger.mail.SendMailHTMLSupportBean;
import com.diebold.travellogger.pojo.Mail;
import com.diebold.travellogger.pojo.user;

public class TravelInsuranceBoImp implements TravelInsuranceBo{
	user user;
	
	public static StringBuffer mailBody = new StringBuffer("Hi Prasit,<br/><br/>"
			+ "Kindly provide me travel insurance for my travel to #Location# dated #StartDate# to #EndDate#.<br/><br/>"
			+ "Required information is given below :<br/><br/>"
			+ "Name of the traveler as per passport : #Name#<br/>"
			+ "Nominee Name : #NomineeName#<br/>Relation to Traveler : #RelationShip#<br/>Designation : #Designation#<br/>"
			+ "Employee ID : #EmployeeID#<br/>Mobile Number : #MobileNumber#<br/>"
			+ "Thanks in advance!! :)<br/><br/>Regards,<br/>On #ResourceName# behalf<br/>Travel Desk");
	
	public String getMailBody(){
		return mailBody.toString();
	}

	@Override
	public user getInsuranceData() {
		user = new TravelInsuranceDaoImp().getInsuranceData();
		return user;
	}

	@Override
	public boolean sendMail(Mail mail) {
		new SendMailHTMLSupportBean().send(user.getEmailId(), mail.getTo(), mail.getCc(), mail.getBcc(), mail.getSubject(), mail.getBody(), "localhost");
		return true;
	}
	
	public String formatMail(String message, String location, String startDate, String endDate, String name, String nomineeName, String relatioship, String designation, String empId, String mobileNumber, String resourceName){
		message = Pattern.compile("#Location#").matcher(message).replaceAll(location);
		message = Pattern.compile("#StartDate#").matcher(message).replaceFirst(startDate);
		message = Pattern.compile("#EndDate#").matcher(message).replaceFirst(endDate);
		message = Pattern.compile("#Name#").matcher(message).replaceFirst(name);
		message = Pattern.compile("#NomineeName#").matcher(message).replaceFirst(nomineeName);
		message = Pattern.compile("#RelationShip#").matcher(message).replaceFirst(relatioship);
		message = Pattern.compile("#Designation#").matcher(message).replaceFirst(designation);
		message = Pattern.compile("#EmployeeID#").matcher(message).replaceFirst(empId);
		message = Pattern.compile("#MobileNumber#").matcher(message).replaceFirst(mobileNumber);
		message = Pattern.compile("#ResourceName#").matcher(message).replaceFirst(resourceName);
		return message;
	}

	@Override
	public String getBody(TravelInsuranceBean object) {
		return formatMail(getMailBody(), object.getLocation(), formateDate(object.getTravelStartDate()), formateDate(object.getTravelEndDate()), user.getFirstName()+" "+user.getMiddleName()+" "+user.getLastName(), object.getNomineeName(), object.getRelationship(), user.getDesignation(), user.getOracleId().toString(), object.getMobileNumber(), user.getFirstName()+" "+user.getLastName());
	}
	
	public String formateDate(Date d){
		return d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()-100);
	}
}
