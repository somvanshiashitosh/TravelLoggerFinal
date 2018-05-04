package com.diebold.travellogger.bo;

import com.diebold.travellogger.beans.TravelInsuranceBean;
import com.diebold.travellogger.pojo.Mail;
import com.diebold.travellogger.pojo.user;

public interface TravelInsuranceBo {
	public user getInsuranceData();
	public boolean sendMail(Mail mail);
	public String getBody(TravelInsuranceBean object);
}
