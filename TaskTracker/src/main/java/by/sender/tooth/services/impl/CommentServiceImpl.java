package by.sender.tooth.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.sender.tooth.dao.CommentDAO;
import by.sender.tooth.model.Comment;
import by.sender.tooth.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDAO commentDAO;
		
	@Transactional
	public List<Comment> getList() {
		return commentDAO.getList();
	}
	
	@Transactional
	public List<Comment> getListInProject(int id) {
		return commentDAO.getListInProject(id);
	}
	
	@Transactional
	public Comment fetchById(int id) {
		return commentDAO.fetchById(id);
	}
	
	@Transactional
	public void save(Comment comment) {
		commentDAO.save(comment);
	}

	@Transactional
	public void removeById(int id) {	
		commentDAO.removeById(id);
	}

}
