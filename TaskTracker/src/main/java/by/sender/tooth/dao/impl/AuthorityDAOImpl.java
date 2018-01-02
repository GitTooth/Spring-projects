package by.sender.tooth.dao.impl;

import java.util.List;

import by.sender.tooth.model.Authority;
import by.sender.tooth.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.sender.tooth.dao.AuthorityDAO;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Authority> getList() {
		Session session = sessionFactory.getCurrentSession();

		List<Authority> AuthorityList = session.createQuery("FROM Authority", Authority.class).list();

		return AuthorityList;
	}
	
	public List<String> getListOfNames() {
		Session session = sessionFactory.getCurrentSession();

		List<String> AuthorityNameList = session.createQuery("SELECT authority FROM Authority").list();

		return AuthorityNameList;
	}

	public void save(Authority theAuthority) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(theAuthority);

	}
	
	public void rename(int id, String username) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql1 = "FROM User WHERE id=:user_id";
		Query query1 = session.createQuery(hql1);
		query1.setParameter("user_id", id);
		User user = (User)query1.list().get(0);
		
		String hql2 = "FROM Authority WHERE username = :Username";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("Username", user.getUsername());
		Authority authority = (Authority)query2.list().get(0);

		String hql3 = "UPDATE Authority set username = :Username WHERE id=:auth_id";
		Query query3 = session.createQuery(hql3);
		query3.setParameter("auth_id", authority.getId());
		query3.setParameter("Username", username);
		query3.executeUpdate();
	}

	public Authority fetchByName(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "FROM Authority WHERE username=:Username";
		Query query = session.createQuery(hql);
		query.setParameter("Username", username);
		Authority user = (Authority)query.list().get(0);

		return user;
	}

	public void remove(String username) {
		Session session = sessionFactory.getCurrentSession();

		Query theQuery = session.createQuery("delete from Authority where username=:Username");
		theQuery.setParameter("Username", username);

		theQuery.executeUpdate();

	}

}
