package com.diebold.travellogger.daoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.diebold.travellogger.dao.EditTravellerDAO;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.util.HibernateUtil;

public class EditTravellerDAOImpl implements EditTravellerDAO {
	private SessionFactory sf;

	public EditTravellerDAOImpl() {
		this.sf=HibernateUtil.getSessionFactory();
	}
	
	@Override
	public user getTravellerById(Integer id){
		user u = null;
		Session sess = null;
		Transaction trans = null;
		try {
			sess = sf.getCurrentSession();
			trans = sess.beginTransaction();
			u = (user)sess.createCriteria(user.class).add(Restrictions.eq("oracleId", id)).uniqueResult();
			trans.commit();
		} catch (HibernateException e) {
			if(trans!=null)
				trans.rollback();
		}finally {
			if(sess!=null)
				if(sess.isOpen())
					sess.close();
		}
		return u;
	}
	
	public boolean update(user u){
		boolean isUpdated=false;
		Session sess=null;
		Transaction trans=null;
		try {
			sess=sf.getCurrentSession();
			trans=sess.beginTransaction();
			sess.update(u);
			trans.commit();
			isUpdated=true;
		} catch (HibernateException e) {
			System.out.println(e);
			if(trans!=null)
				trans.rollback();
			
		}finally {
			if(sess!=null)
				if(sess.isOpen())
					sess.close();
		}
		return isUpdated;
	}
}
