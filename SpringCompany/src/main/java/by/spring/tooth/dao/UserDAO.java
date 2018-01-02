package by.spring.tooth.dao;

import java.util.List;

import by.spring.tooth.model.User;

public interface UserDAO {
	public List<User> getList();

	public void save(User user);
	
	public User fetchById(int id);

	public User fetchByName(String username);

	public void remove(String username);
}
