/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diebold.travellogger.mail;

import java.util.Date;
import java.util.regex.Pattern;

public class SendMailUtility {

	private String CREATEUSER_URL = "http://localhost:8080/TravelLogger/addTravel.xhtml";
	SimpleStringCipher encrypter;

	public SendMailUtility() {
		encrypter = new SimpleStringCipher();
	}

	public String createLinks(String url, String emailofreceiver) {
		String link = url;
		try {
			link += "?token=" + encrypter.encrypt(emailofreceiver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return link;
	}

	public void userCreatedMail(String email, String userId) {
		SendMailHTMLSupportBean mail = new SendMailHTMLSupportBean();
		String message = EmailMessages.getUserCreatedMessage();
		message = Pattern.compile("#userId#").matcher(message).replaceFirst(userId);
		System.out.println(message);
		mail.send("vsonar908@gmail.com", email, "", "",
				"Welcome to Aakash2 - User Created", message, "localhost");
	}

	public void sendDispatchMail(String emailofreceiver) {
		SendMailHTMLSupportBean mail = new SendMailHTMLSupportBean();
		String link = createLinks(CREATEUSER_URL, emailofreceiver);
		String message = EmailMessages.getDispatchMessage();
		message = Pattern.compile("#link#").matcher(message).replaceFirst(link);
		System.out.println(message);
		mail.send("vsonar908@example.com", emailofreceiver, "", "",
				"Registeration For New User", message, "localhost");
	}
	
	public void applyForVisa(String travellerName,String country, Date travelStartDate, Date travellerEndDate, String to, String from, String managerName, String AgentName, String managerEmail){
		SendMailHTMLSupportBean mail = new SendMailHTMLSupportBean();
		String message = EmailMessages.getApplyForVisaMessage();
		String name = travellerName.substring(0, travellerName.length()-3);
		String startDate = travelStartDate.getDate()+"/"+(travelStartDate.getMonth()+1)+"/"+(travelStartDate.getYear()-100);
		String endDate = travellerEndDate.getDate()+"/"+(travellerEndDate.getMonth()+1)+"/"+(travellerEndDate.getYear()-100);
		message = Pattern.compile("#ColleagueName#").matcher(message).replaceAll(name);
		message = Pattern.compile("#TravelDestination#").matcher(message).replaceFirst(country);
		message = Pattern.compile("#TravelStartDate#").matcher(message).replaceFirst(startDate);
		message = Pattern.compile("#TravelEndDate#").matcher(message).replaceFirst(endDate);
		message = Pattern.compile("#TravelAgentName#").matcher(message).replaceFirst(AgentName);
		message = Pattern.compile("#ManagerName#").matcher(message).replaceFirst(managerName);
		System.out.println(message);
		mail.send(from, to, managerEmail, "","Apply for Visa", message, "localhost");
	}
	
	public void applyForVisaFromManage(String travellerName,String country, String travelStartDate, String travellerEndDate, String to, String from, String managerEmail, String AgentName, String managerEamil){
		SendMailHTMLSupportBean mail = new SendMailHTMLSupportBean();
		String message = EmailMessages.getApplyForVisaMessage();		
		message = Pattern.compile("#ColleagueName#").matcher(message).replaceAll(travellerName);
		message = Pattern.compile("#TravelDestination#").matcher(message).replaceFirst(country);
		message = Pattern.compile("#TravelStartDate#").matcher(message).replaceFirst(travelStartDate);
		message = Pattern.compile("#TravelEndDate#").matcher(message).replaceFirst(travellerEndDate);
		message = Pattern.compile("#TravelAgentName#").matcher(message).replaceFirst(AgentName);
		message = Pattern.compile("#ManagerName#").matcher(message).replaceFirst(managerEmail);
		System.out.println(message);
		mail.send(from, to, managerEamil, "","Apply for Visa", message, "localhost");
	}

	/*
	 * public static void main(String[] args) throws Exception{ SendMailUtility
	 * utility = new SendMailUtility(); Database db = new Database();
	 * utility.sendDispatchMail("ravi@iitb.ac.in"); SimpleStringCipher cipher =
	 * new SimpleStringCipher();
	 * System.out.println(cipher.encrypt("ravi@iitb.ac.in")); }
	 */
}
