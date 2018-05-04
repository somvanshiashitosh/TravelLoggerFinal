package com.diebold.travellogger.bo;

import java.util.Date;
import java.util.regex.Pattern;
import com.diebold.travellogger.beans.LaptopLetterBean;
import com.diebold.travellogger.daoImpl.LaptopLetterDaoImp;
import com.diebold.travellogger.mail.SendMailHTMLSupportBean;
import com.diebold.travellogger.pojo.Mail;
import com.diebold.travellogger.pojo.user;

public class LaptopLetterBoImp implements LaptopLetterBo{
	private user user;

	public static StringBuffer mailBody = new StringBuffer("Hi Prasit,<br/><br/>"
			+ "Kindly provide me travel insurance for my travel to #Location# dated #StartDate# to #EndDate#.<br/><br/>"
			+ "Required information is given below :<br/><br/>"
			+ "Name of the traveler as per passport : #Name#<br/>" 
			+ "Designation : #Designation#<br/>"
			+ "Employee ID : #EmployeeID#<br/>"
			+ "Mobile Number : #MobileNumber#<br/>"
			+ "Date of Joining : #DOJ#<br/>"
			+ "Laptop Serial Number : #LaptopSerialNumber#<br/><br/>"
			+ "Thanks in advance!! :)<br/><br/>Regards,<br/>On #ResourceName# behalf<br/>Travel Desk");
	
	public String getMailBody(){
		return mailBody.toString();
	}
	
	public String formatMail(String message, String location, String startDate, String endDate, String name, String designation, String empId, String mobileNumber,String DOJ, String serialNumber, String resourceName){
		message = Pattern.compile("#Location#").matcher(message).replaceAll(location);
		message = Pattern.compile("#StartDate#").matcher(message).replaceFirst(startDate);
		message = Pattern.compile("#EndDate#").matcher(message).replaceFirst(endDate);
		message = Pattern.compile("#Name#").matcher(message).replaceFirst(name);
		message = Pattern.compile("#Designation#").matcher(message).replaceFirst(designation);
		message = Pattern.compile("#EmployeeID#").matcher(message).replaceFirst(empId);
		message = Pattern.compile("#MobileNumber#").matcher(message).replaceFirst(mobileNumber);
		message = Pattern.compile("#DOJ#").matcher(message).replaceFirst(DOJ);
		message = Pattern.compile("#LaptopSerialNumber#").matcher(message).replaceFirst(serialNumber);
		message = Pattern.compile("#ResourceName#").matcher(message).replaceFirst(resourceName);
		return message;
	}
	
	public String formateDate(Date d){
		return d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()-100);
	}
	
	
	@Override
	public user getDetails() {
		user = new LaptopLetterDaoImp().getDetails();
		return user;
	}

	@Override
	public String getBody(LaptopLetterBean object) {
		return formatMail(getMailBody(), object.getLocation(), formateDate(object.getTravelStartDate()), formateDate(object.getTravelEndDate()), user.getFirstName()+" "+user.getMiddleName()+" "+user.getLastName(), object.getDesignation(), object.getEmpId().toString(), object.getMobileNumber(), formateDate(object.getDoj()), object.getSerialNumber(), user.getFirstName()+" "+user.getLastName());
	}
	
	public boolean sendMail(Mail mail){
		new SendMailHTMLSupportBean().send(user.getEmailId(), mail.getTo(), mail.getCc(), mail.getBcc(), mail.getSubject(), mail.getBody(), "localhost");
		return true;
	}

}
