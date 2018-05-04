package com.diebold.travellogger.bo;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.diebold.travellogger.bo.ForexBo;
import com.diebold.travellogger.beans.ForexBean;
import com.diebold.travellogger.daoImpl.ForexDaoImp;
import com.diebold.travellogger.mail.SendMailHTMLSupportBean;
import com.diebold.travellogger.pojo.Mail;

public class ForexBoImp implements ForexBo{

	public static StringBuffer mailBody = new StringBuffer("Hi Sanket,<br/><br/>"
			+ "Please provide me forex for my #TravellingCountry# Travel. Passport copy is attached for your reference. Details are given below :<br/><br/>"
			+ "Name of the traveler as per Passport : #TravellerName#<br/>"
			+ "Date of Travel : #StartDate# to #EndDate#<br/>"
			+ "Card Number = #CardNumber#<br/>"
			+ "Forex in Cash = #CashAmount# #Currency#<br/>"
			+ "Forex in Card = #CardAmount# #Currency#<br/><br/>"
			+ "<b>Forex Requirement Details :<br/>"
			+ "Hotel Charges = #HotelNumberOfDay# * #HotelPerDayCharges# = #HotelTotal# #Currency#<br/>"
			+ "Travel Charges = #TravelNumberOfDay# * #TravelPerDayCharges# = #TravelTotal# #Currency#<br/>"
			+ "Per Diem = #DiemNumberOfDay# * #DiemPerDayCharges# = #DiemTotal# #Currency#<br/>"
			+ "Miscellaneous Charges = #Charges# #Currency#<br/>"
			+ "Total = #Total# #Currency#/-<br/><br/>"
			+ "@#ManagerName#:</b> Kindly provide the approval.<br/><br/>"
			+ "Regards,<br/>Travel Desk");

	public String getMailBody(){
		return mailBody.toString();
	}
	
	@Override
	public List<String> getProjectName() {
		return new ForexDaoImp().getProjectName();
	}

	@Override
	public Mail getMail(ForexBean object) {
		Mail mail = new Mail();
		String managerName = new ForexDaoImp().getManagerName(object.getForex().getProjectName());
		if(object.getForex().getMiscellaneousCharges()==""){
			object.getForex().setMiscellaneousCharges("0");
		}
		
		String message = setOtherDetails(getMailBody(), object.getForex().getCountry(), object.getForex().getTravellerName(), FormatDate(object.getForex().getTravelStartDate()), FormatDate(object.getForex().getTravelEndDate()), (object.getForex().getTotal()), managerName, object.getForex().getMiscellaneousCharges(), object.getForex().getCurrency());
		
		if(object.getForex().getForexCard()==""){
			object.getForex().setForexCard("0");
		}
		if(object.getForex().getCardNumber()==""){
			object.getForex().setCardNumber("Not Available");
		}
		message = setCardDetails(message, (object.getForex().getCardNumber()), (object.getForex().getForexCash()), (object.getForex().getForexCard()));
		message = setDiemDetails(message, (object.getPerdiumCharges().getNumberOfDay()), (object.getPerdiumCharges().getChargePerDay()), (object.getPerdiumCharges().getTotal()));
		message = setHotelDetails(message, (object.getHotelCharges().getNumberOfDay()), (object.getHotelCharges().getChargePerDay()), (object.getHotelCharges().getTotal()));
		message = setTravelDetails(message,(object.getTravelCharges().getNumberOfDay()), (object.getTravelCharges().getChargePerDay()), (object.getTravelCharges().getTotal()));
		mail.setDailogHeader("Send Mail for Forex Requirement");
		mail.setSubject("Forex Requirement for "+object.getForex().getCountry());
		mail.setTo("Sanket@db.com");
		mail.setBody(message);
		return mail;
	}
	
	private String setHotelDetails(String message, String HotelNumberOfDay, String HotelPerDayCharges, String HotelTotal){
		message = Pattern.compile("#HotelNumberOfDay#").matcher(message).replaceFirst(HotelNumberOfDay);
		message = Pattern.compile("#HotelPerDayCharges#").matcher(message).replaceFirst(HotelPerDayCharges);
		message = Pattern.compile("#HotelTotal#").matcher(message).replaceFirst(HotelTotal);
		return message;
	}
	
	private String setTravelDetails(String message, String TravelNumberOfDay, String TravelPerDayCharges, String TravelTotal){
		message = Pattern.compile("#TravelNumberOfDay#").matcher(message).replaceFirst(TravelNumberOfDay);
		message = Pattern.compile("#TravelPerDayCharges#").matcher(message).replaceFirst(TravelPerDayCharges);
		message = Pattern.compile("#TravelTotal#").matcher(message).replaceFirst(TravelTotal);
		return message;
	}
	
	private String setDiemDetails(String message, String DiemNumberOfDay, String DiemPerDayCharges, String DiemTotal){
		message = Pattern.compile("#DiemNumberOfDay#").matcher(message).replaceFirst(DiemNumberOfDay);
		message = Pattern.compile("#DiemPerDayCharges#").matcher(message).replaceFirst(DiemPerDayCharges);
		message = Pattern.compile("#DiemTotal#").matcher(message).replaceFirst(DiemTotal);
		return message;
	}
	
	private String setCardDetails(String message, String CardNumber, String CashAmount, String CardAmount){
		message = Pattern.compile("#CardNumber#").matcher(message).replaceFirst(CardNumber);
		message = Pattern.compile("#CashAmount#").matcher(message).replaceFirst(CashAmount);
		message = Pattern.compile("#CardAmount#").matcher(message).replaceFirst(CardAmount);
		return message;
	}
	
	private String setOtherDetails(String message, String TravellingCountry, String TravellerName, String StartDate, String EndDate, String Total, String ManagerName, String Charges, String Currency){
		message = Pattern.compile("#TravellingCountry#").matcher(message).replaceAll(TravellingCountry);
		message = Pattern.compile("#TravellerName#").matcher(message).replaceFirst(TravellerName);
		message = Pattern.compile("#StartDate#").matcher(message).replaceFirst(StartDate);
		message = Pattern.compile("#EndDate#").matcher(message).replaceFirst(EndDate);
		message = Pattern.compile("#Total#").matcher(message).replaceFirst(Total);
		message = Pattern.compile("#ManagerName#").matcher(message).replaceFirst(ManagerName);
		message = Pattern.compile("#Charges#").matcher(message).replaceFirst(Charges);
		message = Pattern.compile("#Currency#").matcher(message).replaceAll(Currency);
		return message;
	}
	
	private String FormatDate(Date d){
		return d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()-100);
	}

	@Override
	public boolean sendMail(Mail mail) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession Session = (HttpSession) context.getExternalContext().getSession(true);
    	int id = (Integer) Session.getAttribute("oracleID");
    	String fromEmail = new ForexDaoImp().getUserEmail();
		new SendMailHTMLSupportBean().send(fromEmail, mail.getTo(), mail.getCc(), mail.getBcc(), mail.getSubject(), mail.getBody(), "localhost");
		return true;
	}

}
