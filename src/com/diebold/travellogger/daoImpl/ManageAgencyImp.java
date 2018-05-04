package com.diebold.travellogger.daoImpl;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.diebold.travellogger.dao.ManageAgencyDao;
import com.diebold.travellogger.pojo.travelAgency;
import com.diebold.travellogger.util.HibernateUtil;

public class ManageAgencyImp implements ManageAgencyDao{
	private Session session;
	private Transaction txn;
	private List<travelAgency> list;
	
	public ManageAgencyImp() {
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}
	
	@Override
	public List<travelAgency> getAgency() {
		try {
			list = (List<travelAgency>)session.createQuery("from travelAgency").list();
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			return null;
		} finally {
			session.close();
		}
		
		System.out.println(list);
		return list;
	}
}
