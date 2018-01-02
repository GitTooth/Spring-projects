package by.sender.tooth.services;

import java.util.List;

import by.sender.tooth.model.Task;

public interface TaskService {
	public List<Task> getList();
	
	public List<Task> getListInProject(int id);

	public void save(Task task);
	
	public Task fetchById(int id);

	public Task fetchByName(String taskName);

	public void remove(String taskName);
}
