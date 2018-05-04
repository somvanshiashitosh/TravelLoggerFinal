package com.diebold.travellogger.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.ManageTravelDao;
import com.diebold.travellogger.pojo.addTravel;
import com.diebold.travellogger.util.HibernateUtil;

public class ManageTravelImp implements ManageTravelDao {
	private Session session;
	private Transaction txn;
	private List<addTravel> list;

	public ManageTravelImp() {
		session = HibernateUtil.getSessionFactory().openSession();
		txn = session.beginTransaction();
	}

	@Override
	public List<addTravel> getAllTravels() {
		try {
			//list = (List<addTravel>) session.createQuery("from addTravel where ").list();
			String sql = "SELECT * FROM travellog where TravelEndDate>now()";
			list = session.createSQLQuery(sql).addEntity(addTravel.class).list();
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
	public boolean EngagementLink(int id, String EAmessage) {
		boolean state = false;
		try {
			addTravel updateObj = (addTravel) session.get(addTravel.class, id);
			updateObj.setEngagementAgreement(EAmessage);
			session.save(updateObj);
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
	public boolean projectApprovalLink(int id, String EAmessage) {
		boolean state = false;
		try {
			addTravel updateObj = (addTravel) session.get(addTravel.class, id);
			updateObj.setProjectLevel(EAmessage);
			session.save(updateObj);
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
	public boolean locationApprovalLink(int id, String EAmessage) {
		boolean state = false;
		try {
			addTravel updateObj = (addTravel) session.get(addTravel.class, id);
			updateObj.setLocationHead(EAmessage);
			session.save(updateObj);
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
	public boolean psApprovalLink(int id, String EAmessage) {
		boolean state = false;
		try {
			addTravel updateObj = (addTravel) session.get(addTravel.class, id);
			updateObj.setPsHead(EAmessage);
			session.save(updateObj);
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
	public String getEADetails(int id) {
		String email = null;
		try {
			String x = "select CONCAT(EAHead,';',EAEmail,';',EACC,';',ProjectName,'(',ProjectCode,')',';',ProjectCountry,';',managerName,';',managerEmail) as x from project where ProjectCode=(select ProjectCode from travellog where Id="
						+id+")";
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
	public String getPADetails(int id) {
		String email = null;
		try {
			String x = "select CONCAT(ProjectHead,';',PHEmail,';',PHCC,';',ProjectName,'(',ProjectCode,')',';',ProjectCountry,';',managerName,';',managerEmail) as x from project where ProjectCode=(select ProjectCode from travellog where Id="
					+ id + ")";
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
	public String getLADetails(int id) {
		String email = null;
		try {
			String x = "select CONCAT(LocationHead,';',LHEmail,';',LHCC,';',ProjectName,'(',ProjectCode,')',';',ProjectCountry,';',managerName,';',managerEmail) as x from project where ProjectCode=(select ProjectCode from travellog where Id="
					+ id + ")";
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
	public String getPSDetails(int id) {
		String email = null;
		try {
			String x = "select CONCAT(PSHead,';',PSEmail,';',PSCC,';',ProjectName,'(',ProjectCode,')',';',ProjectCountry,';',managerName,';',managerEmail) as x from project where ProjectCode=(select ProjectCode from travellog where Id="
					+ id + ")";
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
	public String getTravellerDetails(int id) {
		String details = null;
		try {
			/*User x = (User) session.get(User.class, id);
			 * select EmailId from travel.user where FirstName='vishal' and LastName='sonar';
			details = x.getFirstName() + x.getLastName() +";"+x.getEmail()+";";*/
			addTravel y = (addTravel) session.get(addTravel.class, id);
			
			details = y.getTravellerName() 
					+";"
					+ y.getTravelStartDate().getDate()+"/"+(y.getTravelStartDate().getMonth()+1)+"/"+(y.getTravelStartDate().getYear()-100)
					+";"
					+y.getTravelEndDate().getDate()+"/"+(y.getTravelEndDate().getMonth()+1)+"/"+(y.getTravelEndDate().getYear()-100)
					+";"
					+y.getScopeWork();
			
			String[] UserName = y.getTravellerName().split(" ");
			String x = "select EmailId as x from user where FirstName='"+UserName[0]+"' and LastName='"+UserName[1]+"'";
			SQLQuery query = session.createSQLQuery(x);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			String email = (String) query.list().iterator().next().toString();
			email = email.substring(3, email.length()-1);
			details += ";"+email;			
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}
			details = null;
		} finally {
			session.close();
		}
		return details;
	}
	
	public boolean updateVisaStatus(int id, String EAmessage) {
		boolean state = false;
		try {
			addTravel updateObj = (addTravel) session.get(addTravel.class, id);
			updateObj.setVisaStatus(EAmessage);
			session.save(updateObj);
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
