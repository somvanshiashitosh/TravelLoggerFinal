package com.diebold.travellogger.bo;

import java.util.List;

import com.diebold.travellogger.daoImpl.TravelHistoryDaoImp;
import com.diebold.travellogger.pojo.addTravel;

public class TravelHistoryBoImp implements TravelHistoryBo{

	@Override
	public List<addTravel> getTravelHistory() {
		return new TravelHistoryDaoImp().getTravelHistory();
	}

}
