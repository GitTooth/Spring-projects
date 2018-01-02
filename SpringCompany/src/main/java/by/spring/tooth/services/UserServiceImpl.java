package by.spring.tooth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.spring.tooth.dao.AuthorityDAO;
import by.spring.tooth.dao.UserDAO;
import by.spring.tooth.model.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AuthorityDAO authorityDAO;
	
	@Transactional
	public List<User> getList() {
		return userDAO.getList();
	}
	
	@Transactional
	public void save(User theUser) {
//		authorityDAO.rename(theUser.getId(), theUser.getUsername());
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
