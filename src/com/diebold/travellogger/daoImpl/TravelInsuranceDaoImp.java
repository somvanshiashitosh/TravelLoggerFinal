package com.diebold.travellogger.daoImpl;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.TravelInsuranceDao;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.util.HibernateUtil;

public class TravelInsuranceDaoImp implements TravelInsuranceDao {
	private Session session;
	private Transaction txn;
	
	public TravelInsuranceDaoImp(){
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}
	
	@Override
	public user getInsuranceData() {
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
