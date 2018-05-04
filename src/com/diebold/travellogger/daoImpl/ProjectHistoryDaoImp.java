package com.diebold.travellogger.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.ProjectHistoryDao;
import com.diebold.travellogger.pojo.Project;
import com.diebold.travellogger.util.HibernateUtil;

public class ProjectHistoryDaoImp implements ProjectHistoryDao{
	private Session session;
	private Transaction txn;
	private List<Project> list;
	
	public ProjectHistoryDaoImp() {
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}
	
	@Override
	public List<Project> getHistory() {
		
		try {	
	    	list = (List<Project>) session.createQuery("from Project where ProjectStatus='Closed'").list();						
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

	@Override
	public boolean toggleStatus(int id) {
		boolean state = false;
		try {
			Project updateStatus = (Project) session.get(Project.class, id);
			if(updateStatus.getProjectStatus().equals("Closed")){
				updateStatus.setProjectStatus("InProgress");
			}
			else if(updateStatus.getProjectStatus().equals("InProgress")){
				updateStatus.setProjectStatus("Closed");
			}
			session.update(updateStatus);
			txn.commit();
			state = true;
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			state = false;
		} finally {
			session.close();
		}
		return state;
	}

}
