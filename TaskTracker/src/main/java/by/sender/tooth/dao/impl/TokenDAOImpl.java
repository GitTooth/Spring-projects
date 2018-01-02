package by.sender.tooth.dao.impl;

import java.util.List;

import by.sender.tooth.model.VerificationToken;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.sender.tooth.dao.TokenDAO;

@Repository
public class TokenDAOImpl implements TokenDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<VerificationToken> getList() {
		Session session = sessionFactory.getCurrentSession();

		List<VerificationToken> tokenList = session.createQuery("FROM VerificationToken", 
				VerificationToken.class).list();

		return tokenList;
	}

	public void save(VerificationToken token) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(token);

	}

	public VerificationToken fetchById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "FROM VerificationToken WHERE id=:ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", id);
		VerificationToken token = (VerificationToken)query.list().get(0);

		return token;
	}

	public void remove(int id) {
		Session session = sessionFactory.getCurrentSession();

		Query theQuery = session.createQuery("delete from VerificationToken where id=:ID");
		theQuery.setParameter("ID", id);

		theQuery.executeUpdate();

	}

	@Override
	public VerificationToken fetchByToken(String token) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "FROM VerificationToken WHERE token=:Token";
		Query query = session.createQuery(hql);
		query.setParameter("Token", token);
		VerificationToken verToken = (VerificationToken)query.list().get(0);

		return verToken;
	}

}
