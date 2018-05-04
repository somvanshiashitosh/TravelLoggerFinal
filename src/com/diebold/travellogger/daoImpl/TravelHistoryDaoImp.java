package com.diebold.travellogger.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.TravelHistoryDao;
import com.diebold.travellogger.pojo.addTravel;
import com.diebold.travellogger.util.HibernateUtil;

public class TravelHistoryDaoImp implements TravelHistoryDao{
	private Session session;
	private Transaction txn;
	private List<addTravel> list;
	
	public TravelHistoryDaoImp() {
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}
	
	@Override
	public List<addTravel> getTravelHistory() {
		try {
			//list = (List<addTravel>)session.createQuery("from addTravel where ").list();
			String sql = "SELECT * FROM travellog where TravelEndDate<now()";
			list = session.createSQLQuery(sql).addEntity(addTravel.class).list();			
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			list = null;
		} finally {
			session.close();
		}
		return list;
	}

}
