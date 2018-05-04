package com.diebold.travellogger.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.diebold.travellogger.dao.roleDao;
import com.diebold.travellogger.pojo.role;
import com.diebold.travellogger.util.HibernateUtil;

public class roleDaoImpl implements roleDao {

	private SessionFactory sessionFactory;
	
	public roleDaoImpl()
	{
		sessionFactory=HibernateUtil.getSessionFactory();
	}
	
	@Override
	public List<String> getRoleByOracleId(Integer id) {
		// TODO Auto-generated method stub
		Session session = null;
	    Transaction transaction = null;
	    List<String> roleName;
	    try
	    {
	    	session= sessionFactory.openSession();
	    	transaction=session.beginTransaction();
	    	Query q=session.createQuery("select roleName from role where oracleId=:id");
	    	q.setParameter("id", id);
	    	roleName=q.list();
	    	transaction.commit();
	    }
	    catch(Throwable th)
	    {
	    	if(transaction != null)
	    	{
	    		transaction.rollback();
	    	}
	    	System.out.println(th+"In roleDaoImpl");
	    	return null;
	    }
		finally
		{
			if(session.isOpen())
			{
				session.close();
			}
		}
		return roleName;

	}

	@Override
	public boolean addRole(role role) {
		// TODO Auto-generated method stub
		boolean flag = true;
		Session session = null;
		Transaction trans = null;
		try{
			session= sessionFactory.openSession();
			trans=session.beginTransaction();
			session.saveOrUpdate(role);
			trans.commit();
		}catch (Exception e) {
			if(trans!=null)
				flag=false;
				trans.rollback();
				System.out.println(e);
		}finally {
			if(session!=null)
				if(session.isOpen())
					session.close();
		}return flag;
	}

	public String getRole(int id){
        String role = null;
        Session session = null;
        Transaction trans = null;
        try {
               session = sessionFactory.getCurrentSession();
               trans = session.beginTransaction();
               //u = (user)sess.createCriteria(user.class).add(Restrictions.eq("oracleId", id)).uniqueResult();
               role r = (role)session.createCriteria(role.class).add(Restrictions.eq("oracleId",id)).uniqueResult();
               System.out.println(r);
               if(r!=null){
                     role = r.getRoleName();
               }
               System.out.println(role);
               trans.commit();
        } catch (HibernateException e) {
               System.out.println(e);
               if(trans!=null)
                     trans.rollback();
        }finally {
               if(session!=null)
                     if(session.isOpen())
                            session.close();
        }return role;
  }

}
