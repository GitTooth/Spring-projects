package by.sender.tooth.dao;

import java.util.List;

import by.sender.tooth.model.Task;

public interface TaskDAO {
	public List<Task> getList();
	
	public List<Task> getListInProject(int id);

	public void save(Task task);
	
	public Task fetchById(int id);

	public Task fetchByName(String taskName);

	public void remove(String taskName);
}
