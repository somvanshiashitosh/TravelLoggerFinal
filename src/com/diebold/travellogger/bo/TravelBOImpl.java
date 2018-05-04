package com.diebold.travellogger.bo;

import java.util.List;


import com.diebold.travellogger.daoImpl.TravelAgencyDAOImpl;
import com.diebold.travellogger.dao.EditTravellerDAO;
import com.diebold.travellogger.dao.EditVisaDAO;
import com.diebold.travellogger.daoImpl.*;
import com.diebold.travellogger.dao.TravelAgencyDao;
import com.diebold.travellogger.pojo.travelAgency;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.pojo.uservisa;

public class TravelBOImpl implements TravelBO{
	private TravelAgencyDao tdao=null;
	private EditTravellerDAO etdao=null;

	public TravelBOImpl() {
		tdao=new TravelAgencyDAOImpl();
		etdao=new EditTravellerDAOImpl();
	}
	
	public boolean addTravelAgency(travelAgency ta){
		return tdao.addTravelAgency(ta);
	}
	
	public List<travelAgency> getTravelAgencies(){
		return tdao.getTravelAgencies();
	}
	
	public user getTravellerById(Integer id){
		return etdao.getTravellerById(id);
	}
	
	
}
