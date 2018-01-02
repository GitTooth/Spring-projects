package by.sender.tooth.services;

import java.util.List;

import by.sender.tooth.model.Project;

public interface ProjectService {
	public List<Project> getList();

	public void save(Project project);

	public Project fetchById(int id);
	
	public Project fetchByName(String projectName);

	public void remove(String projectName);
}
