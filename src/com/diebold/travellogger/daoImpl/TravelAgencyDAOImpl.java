package com.diebold.travellogger.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.TravelAgencyDao;
import com.diebold.travellogger.pojo.travelAgency;
import com.diebold.travellogger.util.HibernateUtil;

public class TravelAgencyDAOImpl implements TravelAgencyDao {
	
	private SessionFactory sessionfactory;
	
	
	
	public TravelAgencyDAOImpl() {
		sessionfactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public boolean addTravelAgency(travelAgency ta){
		Session session=null;
		Transaction transaction = null;
		try {
			session = sessionfactory.openSession();
			transaction = session.beginTransaction();
			session.save(ta);
			transaction.commit();
		} catch (HibernateException he) {
			if(he!=null)
				transaction.rollback();
			return false;
		}finally {
			if(session!=null){
				if(session.isOpen())
					session.close();
			}
		}
		return true;
	}
	
	public List<travelAgency> getTravelAgencies(){
		List<travelAgency> tl=new ArrayList<travelAgency>();
		Session session=null;
		Transaction transaction=null;
		try {
			session = sessionfactory.openSession();
			transaction = session.beginTransaction();
			tl = session.createQuery("from travelagency").list();
			transaction.commit();
		} catch (HibernateException e) {
			if(e!=null){
			transaction.rollback();
			}
		}finally {
			if(session!=null){
				if(session.isOpen())
					session.close();
			}
		}
		return tl;
	}
	
	public List<String> getAgencyName()
	{
		List<String> tl=new ArrayList<String>();
		Session session=null;
		Transaction transaction=null;
		try {
			session = sessionfactory.openSession();
			transaction = session.beginTransaction();
			tl = session.createQuery("select name from travelAgency").list();
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("in travelAgency"+e);
			if(e!=null){
			transaction.rollback();
			}
		}finally {
			if(session!=null){
				if(session.isOpen())
					session.close();
			}
		}
		return tl;
	}
}
