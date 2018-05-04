package com.diebold.travellogger.bo;

import java.util.List;

import com.diebold.travellogger.beans.ForexBean;
import com.diebold.travellogger.pojo.Mail;

public interface ForexBo {
	public List<String> getProjectName();
	public Mail getMail(ForexBean object);
	public boolean sendMail(Mail mail);
}
