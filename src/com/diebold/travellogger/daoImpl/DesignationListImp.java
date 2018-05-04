package com.diebold.travellogger.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.DesignationListDao;
import com.diebold.travellogger.pojo.country;
import com.diebold.travellogger.util.HibernateUtil;

public class DesignationListImp implements DesignationListDao{
	private Session session;
	private Transaction txn;
	private List<String> list;
	
	public DesignationListImp() {
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}
	
	@Override
	public List<String> getDesignation() {
		try {
			list = (List<String>) session.createQuery("select Designation from Designation").list();
			System.out.println(list+"in designation dao");
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
				System.out.println(e);
			}
			return null;
		} finally {
			session.close();
		}
		return list;
	}

}
