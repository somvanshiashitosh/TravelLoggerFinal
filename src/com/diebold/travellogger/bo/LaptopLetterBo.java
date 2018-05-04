package com.diebold.travellogger.bo;

import com.diebold.travellogger.beans.LaptopLetterBean;
import com.diebold.travellogger.pojo.user;

public interface LaptopLetterBo {
	public user getDetails();
	public String getBody(LaptopLetterBean object);
}
