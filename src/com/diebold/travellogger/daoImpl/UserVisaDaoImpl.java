package com.diebold.travellogger.daoImpl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.*;
import com.diebold.travellogger.pojo.uservisa;
import com.diebold.travellogger.util.HibernateUtil;

public class UserVisaDaoImpl implements UserVisaDao{

	private SessionFactory sessionFactory;
	public UserVisaDaoImpl()
	{
		sessionFactory=HibernateUtil.getSessionFactory();
	}
	@Override
	public boolean addVisa(List<uservisa> list) throws Throwable {
		// TODO Auto-generated method stub
		Iterator iterator= list.iterator();
		Session session = null;
	    Transaction transaction = null;
	    try
	    {
	    	session= sessionFactory.openSession();
	    	while(iterator.hasNext())
			{
	    		transaction=session.beginTransaction();
				uservisa uservisa=(uservisa)iterator.next();	
				session.save(uservisa);
				transaction.commit();
			}
	    }
	    catch(Throwable th)
	    {
	    	System.out.println("In USerVisaDaoImpl"+th);
	    	if(transaction != null)
	    	{
	    		transaction.rollback();
	    		throw new Throwable();
	    	}
	    	return false;
	    }
		finally
		{
			if(session.isOpen())
			{
				session.close();
			}
		}
		return true;
	}
	
	public List<uservisa> getVisaDetails(Integer id){
		Session sess = null;
		Transaction trans = null;
		List<uservisa> uv = null;
		try{
			sess = sessionFactory.getCurrentSession();
			trans = sess.beginTransaction();
			Query query=sess.createQuery("from uservisa where OracleId=:id and Editable=:no");
	    	query.setParameter("id", id);
	    	query.setParameter("no",1);
			uv = (List<uservisa>)query.list();
			trans.commit();
			System.out.println("Ashutosh"+uv);
		}catch (HibernateException e) {
			if(trans!=null)
				trans.rollback();
		}finally {
			if(sess!=null)
				if(sess.isOpen())
					sess.close();
		}
		return uv;
	}
	
	public boolean update(uservisa uv){
		boolean isUpdated=false;
		Session sess=null;
		Transaction trans=null;
		try {
			sess=sessionFactory.getCurrentSession();
			trans=sess.beginTransaction();
			sess.update(uv);
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
	@Override
	public boolean addVisa(uservisa uservisa) {
		// TODO Auto-generated method stub
		Session session = null;
	    Transaction transaction = null;
	    try
	    {
	    	session= sessionFactory.openSession();
	    		transaction=session.beginTransaction();	
				session.save(uservisa);
				transaction.commit();
			
	    }
	    catch(Throwable th)
	    {
	    	System.out.println("In USerVisaDaoImpl"+th);
	    	if(transaction != null)
	    	{
	    		transaction.rollback();
	    	}
	    	return false;
	    }
		finally
		{
			if(session.isOpen())
			{
				session.close();
			}
		}
		return true;
	}
	@Override
	public boolean delete(uservisa uv) {
		// TODO Auto-generated method stub
		Session session = null;
	    Transaction transaction = null;
	    try
	    {
	    	session= sessionFactory.openSession();
	    		transaction=session.beginTransaction();	
				session.update(uv);
				transaction.commit();
			
	    }
	    catch(Throwable th)
	    {
	    	System.out.println("In USerVisaDaoImpl"+th);
	    	if(transaction != null)
	    	{
	    		transaction.rollback();
	    	}
	    	return false;
	    }
		finally
		{
			if(session.isOpen())
			{
				session.close();
			}
		}
		return true;
	}

/*	@Override
	public boolean updateEditable(int oId) {
		// TODO Auto-generated method stub
		boolean isUpdated=false;
		System.out.println(oId);
		Session sess=null;
		Transaction trans=null;
		try {
			sess=sessionFactory.getCurrentSession();
			trans=sess.beginTransaction();
			Query query=sess.createQuery("update uservisa u set editable=0 where u.oracleId=:id");
			//query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setParameter("id", oId);
			int r=query.executeUpdate();
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
	}*/


}
