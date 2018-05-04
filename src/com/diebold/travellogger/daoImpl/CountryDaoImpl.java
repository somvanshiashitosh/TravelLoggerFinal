package com.diebold.travellogger.daoImpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.CountryDao;
import com.diebold.travellogger.pojo.country;
import com.diebold.travellogger.util.HibernateUtil;

public class CountryDaoImpl implements CountryDao, Serializable{
	private Session session;
	private Transaction txn;
	private List<country> list;
	
	public CountryDaoImpl() {
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}
	
	@Override
	public List<country> getCountry() {
		try {
			list = (List<country>)session.createQuery("select CountryName from country").list();
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			return null;
		} finally {
			session.close();
		}
		return list;
	}

}
