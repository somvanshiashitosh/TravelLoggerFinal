package com.diebold.travellogger.mail;

public class EmailMessages {

	public static StringBuffer userCreatedMessage = new StringBuffer(
			"<h5>This mail is electronically generated. Do not reply to this mail.</h5>" + "<p>Dear User,</p>"
					+ "<p>Your login creadentials are created.Now you can access our website<br/>"
					+ "Your userid is = #userId# </p><br/>" + "<p>Regards<br/>" + "Aakash Support Team</p>");

	public static StringBuffer dispatchMessage = new StringBuffer("<h5>This mail is electronically generated. Do not reply to this mail.</h5>"
					+ "<p>Dear User,<br/><br/>"
					+ "To initiate your travel request, you are required to register as soon as possible.<br/>"
					+ "To get registered, click on the below link and fill the asked information  "
					+ "<a href=\"#link#\" target=\"_blank\"> Click here to register! </a></p>"
					+ "Regards,<br/>"
					+ "Team DieboldNixdorf TravelDesk</p>");	
	
	public static StringBuffer applyForVisaMessage = new StringBuffer("<h5>This mail is electronically generated. Do not reply to this mail.</h5>"
			+ "<p>Hello #TravelAgentName#,<br/><br/>"
			+ "One of our colleague #ColleagueName# needs to travel to #TravelDestination# during #TravelStartDate# - #TravelEndDate#.<br/>"
			+ "Please let us know document required and initiate his Visa process as soon as possible.<br/><br/>"
			+ "Regards,<br/>On behalf of #ManagerName# <br/>Team DieboldNixdorf TravelDesk</p>");
	
	public static StringBuffer projectLevelApprovalEmail = new StringBuffer("<h5>This mail is electronically generated. Do not reply to this mail.</h5>"
			+ "<p>Hello #ProjectManagementHeadName#,<br/><br/>"
			+ "#ColleagueName# needs to travel to #TravelDestination# during #TravelStartDate# - #TravelEndDate#.<br/>"
			+ "During his visit he will be responsible for #ScopeOfWork#<br/>"
			+ "Kindly approve travel by clicking to the <a href=\"#link#\" target=\"_blank\">link</a>, so that we can initiate tickets and hotel reservation.<br/><br/>"
			+ "Regards,<br/>On behalf of #ManagerName#<br/>Team DieboldNixdorf TravelDesk</p>");
	
	public static StringBuffer locationLevelApprovalEmail = new StringBuffer("<h5>This mail is electronically generated. Do not reply to this mail.</h5>"
			+ "Hello #LocationHeadName#,<br/><br/>"
			+ "#ColleagueName# needs to travel to #TravelDestination# during #TravelStartDate# - #TravelEndDate#.<br/>"
			+ "We have got approval from Project Management team.<br/>"
			+ "Kindly approve travel by clicking to the <a href=\"#link#\" target=\"_blank\">link</a><br/><br/>"
			+ "Regards,<br/>On behalf of #ManagerName#<br/>Team DieboldNixdorf TravelDesk");
	
	public static StringBuffer psApprovalEmail = new StringBuffer("<h5>This mail is electronically generated. Do not reply to this mail.</h5>"
			+ "Hello #PSHeadName#,<br/><br/>"
			+ "#ColleagueName# needs to travel to #TravelDestination# during #TravelStartDate# - #TravelEndDate#.<br/>"
			+ "We have got approval from Project Management team.<br/>"
			+ "Kindly approve travel by clicking to the <a href=\"#link#\" target=\"_blank\">link</a><br/><br/>"
			+ "Regards,<br/>On behalf of #ManagerName#<br/>Team DieboldNixdorf TravelDesk");

	
	public static StringBuffer forgotPasswordEmail = new StringBuffer(
			"<h5>This mail is electronically generated. Do not reply to this mail.</h5>" + "<p>Dear #Name#,</p>"
					+ "<p> Your password is #password# </p>"	);


	public static String getForgotPasswordMail(){
		return forgotPasswordEmail.toString();
	}
	
	public static String getUserCreatedMessage() {
		return userCreatedMessage.toString();
	}

	public static String getDispatchMessage() {
		return dispatchMessage.toString();
	}
	
	public static String getApplyForVisaMessage(){
		return applyForVisaMessage.toString();
	}
	
	public static String getProjectLevelApprovalEmail(){
		return projectLevelApprovalEmail.toString();
	}
	
	public static String getLocationLevelApprovalEmail(){
		return locationLevelApprovalEmail.toString();
	}
	
	public static String getPsApprovalEmail(){
		return psApprovalEmail.toString();
	}
}
