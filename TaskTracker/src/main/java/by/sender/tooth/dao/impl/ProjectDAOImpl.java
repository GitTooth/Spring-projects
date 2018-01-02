package by.sender.tooth.dao.impl;

import java.util.List;

import by.sender.tooth.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.sender.tooth.dao.ProjectDAO;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Project> getList() {
		Session session = sessionFactory.getCurrentSession();

		List<Project> projectList = session.createQuery("FROM Project", Project.class).list();

		return projectList;
	}

	public void save(Project project) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(project);

	}
	
	public Project fetchById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Project.class, id);
	}

	public Project fetchByName(String projectName) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "FROM Project WHERE name=:NAME";
		Query query = session.createQuery(hql);
		query.setParameter("NAME", projectName);
		Project project = (Project)query.list().get(0);

		return project;
	}

	public void remove(String projectName) {
		Session session = sessionFactory.getCurrentSession();

		Query theQuery = session.createQuery("delete from Project where name=:NAME");
		theQuery.setParameter("NAME", projectName);

		theQuery.executeUpdate();

	}

}
