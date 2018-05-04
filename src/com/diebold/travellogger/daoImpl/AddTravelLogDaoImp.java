package com.diebold.travellogger.daoImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.AddTravelLogDao;
import com.diebold.travellogger.pojo.Project;
import com.diebold.travellogger.pojo.travelAgency;
import com.diebold.travellogger.pojo.user;
import com.diebold.travellogger.pojo.uservisa;
import com.diebold.travellogger.pojo.addTravel;
import com.diebold.travellogger.util.HibernateUtil;

public class AddTravelLogDaoImp implements AddTravelLogDao {
	private Session session;
	private Transaction txn;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession Session = (HttpSession) facesContext.getExternalContext().getSession(true);
	
	public AddTravelLogDaoImp(){
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCountry() {
		List<String> list = null;
		try {
			list = (List<String>)session.createQuery("select CountryName from country").list();
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

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getApprovalStatus() {
		List<String> list = null;
		try {
			list = (List<String>)session.createQuery("select Status from Status").list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCostCoveredBy() {
		List<String> list = null;
		try {
			list = (List<String>)session.createQuery("select CONCAT(Location,'  (',Name,')') as Name from CostCovered").list();
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
	           user userName = (user) iterator.next(); 
	           String matchString = userName.getFirstName()+" "+userName.getLastName()+" ("+userName.getOracleId()+")";
	           if(matchString.equalsIgnoreCase(name)){
	        	   user user = (user)session.get(user.class, userName.getOracleId());
	        	   Session.setAttribute("designation", user.getDesignation());
	        	   Session.setAttribute("id", user.getOracleId());
	        	   Session.setAttribute("travellerEmail", user.getEmailId());
	        	   Session.setAttribute("passportIssue", user.getIssueDate());
	        	   Session.setAttribute("passportExpiry", user.getExpiryDate());
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
	public String getProjectCode(String projectName) {
		String ProjectCode = null;
		 List<Project> list = null;	
		    try
		    {
		    	list = (List<Project>) session.createQuery("from Project").list();
		    	for (Iterator iterator = list.iterator(); iterator.hasNext();){
		    		Project x = (Project) iterator.next(); 
		    		if(x.getProjectName().equals(projectName)){
		    			ProjectCode = x.getProjectCode();
		    			HttpSession Session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		    			Session.setAttribute("managerName", x.getManagerName());
		    			Session.setAttribute("managerEmail", x.getManagerEmail());
		    		}
		    	}
		    	txn.commit();
		    }
		    catch(Throwable th)
		    {
		    	if(txn != null)
		    	{
		    		txn.rollback();
		    	}
		    	ProjectCode = null;
		    }
			finally
			{
				if(session.isOpen())
				{
					session.close();
				}
			}
		    
			return ProjectCode;
	}

	@Override
	public Date[] expiryDate(String country) {
		Date[] expiry = null;
		try {
			int id = (Integer) Session.getAttribute("id");
			Query query = session.createQuery("from uservisa where OracleId=:id AND Country=:country");
			query.setParameter("id", id);
			query.setParameter("country", country);
			List<uservisa> userVisa = (List<uservisa>)query.list();
			
			for (Iterator iterator = userVisa.iterator(); iterator.hasNext();){
				uservisa x = (uservisa) iterator.next();
				if(x.getOracleId()==id && x.getCountry().compareToIgnoreCase(country)==0){
					expiry = new Date[2];
					expiry[0] = x.getExpiryDate();
					expiry[1] = x.getIssueDate();
				}
			}
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			expiry = null;
		} finally {
			session.close();
		}
		return expiry;
	}

	@Override
	public List<String> getAgency() {
		List<String> list = null;
		try {
			list = (List<String>) session.createQuery("select name from travelAgency").list();
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
	public String getVisaAgencyDetails(String agencyName) {
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

	@Override
	public String getEADetails(String projectCode) {
		String email = null;
		try {
			String x = "select CONCAT(EAHead,';',EAEmail,';',EACC,';',ProjectName,'(',ProjectCode,')',';',ProjectCountry,';',managerName,';',managerEmail) as x from project where ProjectCode='"+projectCode+"'";
			SQLQuery query = session.createSQLQuery(x);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			email = (String) query.list().iterator().next().toString();
			email = email.substring(3, email.length() - 1);
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			email = null;
		} finally {
			session.close();
		}
		
		return email;
	}

	@Override
	public String getPADetails(String projectCode) {
		String email = null;
		try {
			String x = "select CONCAT(ProjectHead,';',PHEmail,';',PHCC,';',ProjectName,'(',ProjectCode,')',';',ProjectCountry,';',managerName,';',managerEmail) as x from project where ProjectCode='"+projectCode+"'";
			SQLQuery query = session.createSQLQuery(x);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			email = (String) query.list().iterator().next().toString();
			email = email.substring(3, email.length() - 1);
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			email = null;
		} finally {
			session.close();
		}
		return email;
	}

	@Override
	public String getLADetails(String projectCode) {
		String email = null;
		try {
			String x = "select CONCAT(LocationHead,';',LHEmail,';',LHCC,';',ProjectName,'(',ProjectCode,')',';',ProjectCountry,';',managerName,';',managerEmail) as x from project where ProjectCode='"+projectCode+"'";
			SQLQuery query = session.createSQLQuery(x);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			email = (String) query.list().iterator().next().toString();
			email = email.substring(3, email.length() - 1);
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			email = null;
		} finally {
			session.close();
		}
		return email;
	}

	@Override
	public String getPsDetails(String projectCode) {
		String email = null;
		try {
			String x = "select CONCAT(PSHead,';',PSEmail,';',PSCC,';',ProjectName,'(',ProjectCode,')',';',ProjectCountry,';',managerName,';',managerEmail) as x from project where ProjectCode='"+projectCode+"'";
			SQLQuery query = session.createSQLQuery(x);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			email = (String) query.list().iterator().next().toString();
			email = email.substring(3, email.length() - 1);
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			email = null;
		} finally {
			session.close();
		}
		return email;
	}

	@Override
	public boolean insert(addTravel object) {
		boolean state = false;
		try {
			session.save(object);
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
}
