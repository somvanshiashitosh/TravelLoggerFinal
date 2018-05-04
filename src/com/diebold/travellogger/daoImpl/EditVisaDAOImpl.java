package com.diebold.travellogger.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.diebold.travellogger.dao.EditVisaDAO;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.pojo.uservisa;
import com.diebold.travellogger.util.HibernateUtil;

public class EditVisaDAOImpl implements EditVisaDAO {
	private SessionFactory sf=null;

	public EditVisaDAOImpl() {
		sf = HibernateUtil.getSessionFactory();
	}
	
	}
