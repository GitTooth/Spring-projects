package by.spring.tooth.services;

import java.util.List;

import by.spring.tooth.model.User;

public interface UserService {
	public List<User> getList();

	public void save(User User);
	
	public User fetchById(int id);

	public User fetchByName(String username);

	public void remove(String username);
}
