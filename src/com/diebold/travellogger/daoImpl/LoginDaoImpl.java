package com.diebold.travellogger.daoImpl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.LoginDao;
import com.diebold.travellogger.pojo.login;
import com.diebold.travellogger.util.HibernateUtil;

public class LoginDaoImpl implements LoginDao, Serializable {

    private SessionFactory sessionFactory;
	
	public LoginDaoImpl()
	{
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	@Override
	public login getLoginDetailsByOracleId(Integer oracleid) {
		// TODO Auto-generated method stub
	    Session session = null;
	    Transaction transaction = null;
	    login login=null;
	    try
	    {
	    	session= sessionFactory.openSession();
	    	transaction=session.beginTransaction();
	    	login=(login)session.get(login.class,oracleid);
	    }
	    catch(Throwable th)
	    {
	    	System.out.println("exception"+th);
	    	if(transaction != null)
	    	{
	    		transaction.rollback();
	    	}
	    	return login;
	    }
		finally
		{
			if(session.isOpen())
			{
				session.close();
			}
		}
		return login;
	}
	
	@Override
	public boolean updateLoginAttempts(login login) {
		// TODO Auto-generated method stub
		
		/*Session session = null;
	    Transaction transaction = null;
	    try
	    {
	    	session= sessionFactory.openSession();
	    	transaction=session.beginTransaction();
	    	login login2=(login)session.get(login.class,login.getOracleID());
	    	login2=login;
	    	System.out.println(login2+"ashutosh");
	    	session.save(login2);
	    }
	    catch(Throwable th)
	    {
	    	if(transaction != null)
	    	{
	    		System.out.println("exception"+th);
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
*/		return true;
	}
	
	@Override
	public boolean saveCredentials(login login) throws Throwable {
		// TODO Auto-generated method stub
		Session session = null;
	    Transaction transaction = null;
	    try
	    {
	    	session= sessionFactory.openSession();
	    	transaction=session.beginTransaction();
	    	session.save(login);
	    	transaction.commit();
	    }
	    catch(Throwable th)
	    {
	    	System.out.println("exception in loginDaoImpl"+th);
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
	@Override
	public boolean deleteCredentials(login login) {
		// TODO Auto-generated method stub
		Session session = null;
	    Transaction transaction = null;
	    try
	    {
	    	session= sessionFactory.openSession();
	    	transaction=session.beginTransaction();
	    	session.delete(login);
	    	transaction.commit();
	    }
	    catch(Throwable th)
	    {
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
}
