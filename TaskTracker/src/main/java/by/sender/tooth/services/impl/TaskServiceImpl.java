package by.sender.tooth.services.impl;

import java.util.List;

import by.sender.tooth.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.sender.tooth.dao.TaskDAO;
import by.sender.tooth.model.Task;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDAO taskDAO;
		
	@Transactional
	public List<Task> getList() {
		return taskDAO.getList();
	}
	
	@Transactional
	public List<Task> getListInProject(int id) {
		return taskDAO.getListInProject(id);
	}
	
	@Transactional
	public void save(Task task) {
		taskDAO.save(task);
	}
	
	@Transactional
	public Task fetchById(int id) {
		return taskDAO.fetchById(id);
	}

	@Transactional
	public Task fetchByName(String taskName) {
		return taskDAO.fetchByName(taskName);
	}

	@Transactional
	public void remove(String taskName) {	
		taskDAO.remove(taskName);
	}

}
