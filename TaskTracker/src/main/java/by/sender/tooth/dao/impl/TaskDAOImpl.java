package by.sender.tooth.dao.impl;

import java.util.List;

import by.sender.tooth.model.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.sender.tooth.dao.TaskDAO;

@Repository
public class TaskDAOImpl implements TaskDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Task> getList() {
		Session session = sessionFactory.getCurrentSession();

		List<Task> taskList = session.createQuery("FROM Task", Task.class).list();

		return taskList;
	}
	
	public List<Task> getListInProject(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Task WHERE project.id=:ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", id);
		List<Task> taskList = query.list();
		return taskList;
	}

	public void save(Task task) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(task);

	}
	
	public Task fetchById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Task.class, id);
	}

	public Task fetchByName(String taskName) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "FROM Task WHERE name=:NAME";
		Query query = session.createQuery(hql);
		query.setParameter("NAME", taskName);
		Task task = (Task)query.list().get(0);

		return task;
	}

	public void remove(String taskName) {
		Session session = sessionFactory.getCurrentSession();

		Query theQuery = session.createQuery("delete from Task where name=:NAME");
		theQuery.setParameter("NAME", taskName);

		theQuery.executeUpdate();

	}

}
