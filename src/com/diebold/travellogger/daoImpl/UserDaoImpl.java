package com.diebold.travellogger.daoImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.diebold.travellogger.beans.RegisterBean;
import com.diebold.travellogger.bo.*;
import com.diebold.travellogger.dao.UserDao;
import com.diebold.travellogger.pojo.login;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.pojo.uservisa;
import com.diebold.travellogger.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	private List<user> list;
	private static int id;

	public UserDaoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public user getUserByOracleId(Integer oracleid) {
		Session session = null;
		Transaction transaction = null;
		user user = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			user = (user) session.get(user.class, oracleid);
		} catch (Throwable th) {
			System.out.println("exception" + th);
			if (transaction != null) {
				transaction.rollback();
			}
			return user;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return user;
	}

	@Override
	public boolean addUser(user user, login login, List<uservisa> visaList) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean check = false;
		LoginBo loginBo = new LoginBoImpl();
		UserVisaBo uservisaBo = new UserVisaBoImpl();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(user);
			check = loginBo.saveCredentials(login);
			if (!visaList.isEmpty()) {
				uservisaBo.addUserVisa(visaList);
			}
			transaction.commit();
		} catch (Throwable th) {
			if (transaction != null) {
				transaction.rollback();
			}
			if (check) {
				loginBo.deleteCredentials(login);
			}
			return false;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	@Override
	public boolean getUserByName(String name) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			list = (List<user>) session.createQuery("from user").list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				user UserName = (user) iterator.next();
				if (UserName.getFirstName().equalsIgnoreCase(name)) {
					user user = (user) session.get(user.class, UserName.getOracleId());
					id = user.getOracleId();
					return true;
				}
			}
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public int getUserId() {
		return id;
	}

	@Override
	public Date expiryDate(String country) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from uservisa where OracleId=:id and Country=:country");
			query.setParameter("id", id);
			query.setParameter("country", country);

			List<uservisa> userVisa = (List<uservisa>) query.list();
			for (Iterator iterator = userVisa.iterator(); iterator.hasNext();) {
				uservisa x = (uservisa) iterator.next();

				if (x.getOracleId() == id && (x.getCountry().compareToIgnoreCase(country) == 0)) {
					return x.getExpiryDate();
				}
			}
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return null;
	}

}
