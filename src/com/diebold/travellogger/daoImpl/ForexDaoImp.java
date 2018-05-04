package com.diebold.travellogger.daoImpl;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.ForexDao;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.util.HibernateUtil;
import com.diebold.travellogger.util.SessionUtils;

public class ForexDaoImp implements ForexDao{
	private Session session;
	private Transaction txn;
	
	public ForexDaoImp(){
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}

	@Override
	public List<String> getProjectName() {
		List<String> list = null;
		try {
			list = (List<String>)session.createQuery("select projectName from Project where ProjectStatus = 'InProgress'").list();
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
	public String getManagerName(String projectName) {
		String name = null;		
		try {
			String query = "select managerName from Project where projectName='"+projectName+"'";
			List<String> list = (List<String>)session.createQuery(query).list();
			name = list.toString();
			name = name.substring(1, name.length()-1);
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			name = null;
		} finally {
			session.close();
		}
		return name;
	}

	@Override
	public String getUserEmail() {
    	int id = SessionUtils.getOracleId();
    	user list; 
    	String email = null;
    	try {
			list = (user) session.get(user.class, id);
			email = list.getEmailId();
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			list = null;
			email = null;
		} finally {
			session.close();
		}
		return email;
	}
}
