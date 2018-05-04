package com.diebold.travellogger.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.diebold.travellogger.dao.CostCoveredDao;
import com.diebold.travellogger.pojo.CostCovered;
import com.diebold.travellogger.util.HibernateUtil;

public class CostCoveredImp implements CostCoveredDao{
	private Session session;
	private Transaction txn;
	private List<CostCovered> list;
	
	public CostCoveredImp() {
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}
	
	@Override
	public List<CostCovered> getCostCoveredBy() {
		try {
			list = (List<CostCovered>)session.createQuery("select CONCAT(Location,'  (',Name,')') as Name from CostCovered").list();
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
