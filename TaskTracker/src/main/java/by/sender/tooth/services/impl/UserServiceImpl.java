package by.sender.tooth.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.sender.tooth.dao.UserDAO;
import by.sender.tooth.model.Project;
import by.sender.tooth.model.Task;
import by.sender.tooth.model.User;
import by.sender.tooth.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public List<User> getList() {
		return userDAO.getList();
	}
	
	@Transactional
	public List<User> getListOfFreeDevs(Project project){
		return userDAO.getListOfFreeDevs(project);
	}
	
	@Transactional
	public List<User> getListInProject(Project project){
		return userDAO.getListInProject(project);
	}
	
	@Transactional
	public List<User> getListInTask(Task task){
		return userDAO.getListInTask(task);
	}
	
	@Transactional
	public void save(User theUser) {
		userDAO.save(theUser);
	}
	
	@Transactional
	public User fetchById(int id) {
		return userDAO.fetchById(id);
	}

	@Transactional
	public User fetchByName(String username) {
		return userDAO.fetchByName(username);
	}

	@Transactional
	public void remove(String username) {	
		userDAO.remove(username);
	}

}
