package com.diebold.travellogger.daoImpl;

import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.diebold.travellogger.dao.ProjectDao;
import com.diebold.travellogger.pojo.Project;
import com.diebold.travellogger.util.HibernateUtil;

public class ProjectDaoImpl implements ProjectDao {

	private SessionFactory sessionFactory;

	public ProjectDaoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public List<Project> getProjectList() {
		Session session = null;
		Transaction transaction = null;
		List<Project> list = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			list = (List<Project>) session.createQuery("select projectName from Project where ProjectStatus='InProgress'").list();
			transaction.commit();
		} catch (Throwable th) {
			if (transaction != null) {
				transaction.rollback();
			}
			return null;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public String getProjectCode(String projectName) {
		Session session = null;
		Transaction transaction = null;
		String ProjectCode = null;
		List<Project> list = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			list = (List<Project>) session.createQuery("from Project").list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Project x = (Project) iterator.next();
				if (x.getProjectName().equals(projectName)) {
					ProjectCode = x.getProjectCode();
					HttpSession Session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
					Session.setAttribute("managerName", x.getManagerName());
	    			Session.setAttribute("managerEmail", x.getManagerEmail()); 
				}
				}
			transaction.commit();
		} catch (Throwable th) {
			if (transaction != null) {
				transaction.rollback();
			}
			ProjectCode = null;
			System.out.println(th);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return ProjectCode;

	}

	@Override
	public boolean addProject(Project project) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(project);
			transaction.commit();
		} catch (Throwable th) {
			if (transaction != null) {
				transaction.rollback();
			}
			return false;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

}
