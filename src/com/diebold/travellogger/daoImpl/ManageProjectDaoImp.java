package com.diebold.travellogger.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.ManageProjectDao;
import com.diebold.travellogger.pojo.Project;
import com.diebold.travellogger.util.HibernateUtil;

public class ManageProjectDaoImp implements ManageProjectDao{
	private Session session;
	private Transaction txn;
	private List<Project> list;
	
	public ManageProjectDaoImp() {
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}
	
	@Override
	public List<Project> getProjectList() {
		try {
			list = (List<Project>)session.createQuery("from Project where projectStatus='InProgress'").list();
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
