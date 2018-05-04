package com.diebold.travellogger.daoImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.addTravelDao;
import com.diebold.travellogger.pojo.addTravel;
import com.diebold.travellogger.pojo.travelAgency;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.pojo.uservisa;
import com.diebold.travellogger.util.HibernateUtil;

public class addTravelDaoImp implements addTravelDao{
	private Session session;
	private Transaction txn;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession Session = (HttpSession) facesContext.getExternalContext().getSession(true);
	
	public addTravelDaoImp(){
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}

	@Override
	public boolean insertManageTravelLog(addTravel data) {
		boolean state = false;
		try {
			session.save(data);
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

	@Override
	public List<String> getTravellerName(String name) {
		List<String> list = null;
		try {
			String Query = "select concat(FirstName,' ',LastName,' (',OracleId,')') as FirstName from user where FirstName like '"+name+"%'";
			list = (List<String>)session.createQuery(Query).list();
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
	public boolean validateUser(String name) {
		boolean state = false;
		try {			
			List<user> list = (List<user>)session.createQuery("from user").list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();){
				user UserName = (user) iterator.next(); 
	           String matchString = UserName.getFirstName()+" "+UserName.getLastName()+" ("+UserName.getOracleId()+")";
	           if(matchString.equalsIgnoreCase(name)){
	        	   user user = (user)session.get(user.class, UserName.getOracleId());
	        	   Session.setAttribute("id", user.getOracleId());
	        	   Session.setAttribute("designation", user.getDesignation());
	        	   Session.setAttribute("travellerEmail", user.getEmailId());
	        	   state = true;
	           }
	         }
			txn.commit();
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
	
	@Override
	public Date expiryDate(String country) {
		Date expiry = null;
		try {
			int id = (Integer) Session.getAttribute("id");
			//List<uservisa> userVisa = (List<uservisa>)session.createQuery(q).list();
			Query query = session.createQuery("from uservisa where OracleId=:id AND Country=:country");
			query.setParameter("id", id);
			query.setParameter("country", country);
			List<uservisa> userVisa = (List<uservisa>)query.list();
			
			for (Iterator iterator = userVisa.iterator(); iterator.hasNext();){
				uservisa x = (uservisa) iterator.next();
				if(x.getOracleId()==id && x.getCountry().compareToIgnoreCase(country)==0){
					expiry = x.getExpiryDate();
				}
			}
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
				System.out.println(e);
			}
			expiry = null;
		} finally {
			session.close();
		}
		return expiry;
	}

	@Override
	public String getVisaAgencyEmail(String agencyName) {
		String name = null;
		try {
			List<travelAgency> list = (List<travelAgency>) session.createQuery("from travelAgency").list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();){
				travelAgency x = (travelAgency) iterator.next();
				if(x.getName().equals(agencyName)){
					name = x.getEmail()+";"+x.getAgentName();
				}
			}
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
}
