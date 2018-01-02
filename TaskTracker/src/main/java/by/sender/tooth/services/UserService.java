package by.sender.tooth.services;

import java.util.List;

import by.sender.tooth.model.Project;
import by.sender.tooth.model.Task;
import by.sender.tooth.model.User;

public interface UserService {
	public List<User> getList();
	
	public List<User> getListOfFreeDevs(Project project);
	
	public List<User> getListInProject(Project project);
	
	public List<User> getListInTask(Task task);

	public void save(User User);
	
	public User fetchById(int id);

	public User fetchByName(String username);

	public void remove(String username);
}
