package by.sender.tooth.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.sender.tooth.model.Project;
import by.sender.tooth.model.Task;
import by.sender.tooth.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import by.sender.tooth.dao.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> getList() {
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery("FROM User", User.class).list();
		return userList;
	}
	
	public List<User> getListOfFreeDevs(Project project){
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery("FROM User WHERE isDev=1").list();
		List<User> result = new ArrayList<User>();
		for(User user: userList) {
			boolean contains = false;
			for(Project projectIter: user.getProjects()) {
	            if(projectIter.getId() == project.getId()) {
	            	contains = true;
	            	break;
	            }
			}
			if(contains == false) {
				result.add(user);
			}
        }
		return result;
	}

	public List<User> getListInProject(Project project){
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery("FROM User WHERE isDev=1").list();
		List<User> result = new ArrayList<User>();
		for(User user: userList) {
			boolean contains = false;
			for(Project projectIter: user.getProjects()) {
	            if(projectIter.getId() == project.getId()) {
	            	contains = true;
	            	break;
	            }
			}
			if(contains == true) {
				result.add(user);
			}
        }
		return result;
	}
	
	public List<User> getListInTask(Task task){
		Session session = sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery("FROM User WHERE isDev=1").list();
		List<User> result = new ArrayList<User>();
		for(User user: userList) {
			boolean contains = false;
			for(Task taskIter: user.getTasks()) {
	            if(taskIter.getId() == task.getId()) {
	            	contains = true;
	            	break;
	            }
			}
			if(contains == true) {
				result.add(user);
			}
        }
		return result;
	}
	
	public void save(User user) {
		Session session = sessionFactory.getCurrentSession();
		if(user.getId() == 0) {
			BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
			
			String password = bcpe.encode(user.getPassword());
			
			user.setPassword(password);
		}

		session.saveOrUpdate(user);

	}
	
	public User fetchById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, id);
		
		return user;
	}

	public User fetchByName(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "FROM User WHERE username=:Username";
		Query query = session.createQuery(hql);
		query.setParameter("Username", username);
		User user = (User)query.list().get(0);
		return user;
	}

	public void remove(String username) {
		Session session = sessionFactory.getCurrentSession();

		Query theQuery = session.createQuery("delete from User where username=:Username");
		theQuery.setParameter("Username", username);

		theQuery.executeUpdate();

	}

}
