package com.diebold.travellogger.bo;

import java.util.List;
import com.diebold.travellogger.bo.ManageAgencyBo;
import com.diebold.travellogger.daoImpl.ManageAgencyImp;
import com.diebold.travellogger.pojo.travelAgency;

public class ManageAgencyBoImp implements ManageAgencyBo{

	@Override
	public List<travelAgency> getAgency() {
		return new ManageAgencyImp().getAgency();
	}

}
