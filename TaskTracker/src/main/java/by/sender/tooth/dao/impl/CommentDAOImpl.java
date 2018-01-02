package by.sender.tooth.dao.impl;

import java.util.List;

import by.sender.tooth.model.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.sender.tooth.dao.CommentDAO;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Comment> getList() {
		Session session = sessionFactory.getCurrentSession();

		List<Comment> commentList = session.createQuery("FROM Comment", Comment.class).list();

		return commentList;
	}
	
	public List<Comment> getListInProject(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Comment WHERE task.id=:ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", id);
		List<Comment> commentList = query.list();
		return commentList;
	}
	
	public Comment fetchById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Comment.class, id);
	}

	public void save(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(comment);

	}

	public void removeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Comment comment = fetchById(id);
		System.out.println("!!!!!!!!!!!!!!!!!!!"+id+comment.getText());
		session.delete(comment);
	}

}
