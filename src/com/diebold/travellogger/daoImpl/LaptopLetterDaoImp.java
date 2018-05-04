package com.diebold.travellogger.daoImpl;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.LaptopLetterDao;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.util.HibernateUtil;

public class LaptopLetterDaoImp implements LaptopLetterDao{
	private Session session;
	private Transaction txn;
	
	public LaptopLetterDaoImp(){
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}
	
	@Override
	public user getDetails() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession Session = (HttpSession) context.getExternalContext().getSession(true);
    	int id = (Integer) Session.getAttribute("oracleID"); 
    	user list;
    	
    	try {
			list = (user) session.get(user.class, id);
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

}
