package by.sender.tooth.dao;

import java.util.List;

import by.sender.tooth.model.Comment;

public interface CommentDAO {
	public List<Comment> getList();
	
	public List<Comment> getListInProject(int id);
	
	public Comment fetchById(int id);

	public void save(Comment comment);

	public void removeById(int id);
}
