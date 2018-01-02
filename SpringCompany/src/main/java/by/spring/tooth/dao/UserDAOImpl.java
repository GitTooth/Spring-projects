package by.spring.tooth.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import by.spring.tooth.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> getList() {
		Session session = sessionFactory.getCurrentSession();

		List<User> UserList = session.createQuery("FROM User", User.class).list();

		return UserList;
	}

	public void save(User theUser) {
		Session session = sessionFactory.getCurrentSession();
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		
		String password = bcpe.encode(theUser.getPassword());
		
		theUser.setPassword(password);

		session.saveOrUpdate(theUser);

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
