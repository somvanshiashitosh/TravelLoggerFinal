package com.diebold.travellogger.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.StatusDao;
import com.diebold.travellogger.pojo.CostCovered;
import com.diebold.travellogger.pojo.Status;
import com.diebold.travellogger.util.HibernateUtil;

public class StatusImp implements StatusDao{
	private Session session;
	private Transaction txn;
	private List<Status> list;
	
	public StatusImp() {
		session = new HibernateUtil().getSessionFactory().openSession();
		txn = session.beginTransaction();
	}
	
	@Override
	public List<Status> getStatus() {
		try {
			list = (List<Status>)session.createQuery("select Status from Status").list();
			txn.commit();
			System.out.println(list);
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
				System.out.println(e);
			}
			return null;
		} finally {
			session.close();
		}
		
		System.out.println(list);
		return list;
	}

}
