package com.diebold.travellogger.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.foodDao;
import com.diebold.travellogger.pojo.country;
import com.diebold.travellogger.pojo.foodprefrences;
import com.diebold.travellogger.util.HibernateUtil;

public class foodDaoImpl implements foodDao {

	private Session session;
	private Transaction txn;
	
	public foodDaoImpl()
	{
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}
	
	@Override
	public List<foodprefrences> getDetails() {
		// TODO Auto-generated method stub
		List<foodprefrences> list;
		try {
			list = (List<foodprefrences>)session.createQuery("select Name from foodprefrences").list();
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				System.out.println(e);
				txn.rollback();
			}
			return null;
		} finally {
			session.close();
		}
		return list;
	}

}
