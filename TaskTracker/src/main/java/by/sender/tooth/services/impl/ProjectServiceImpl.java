package by.sender.tooth.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.sender.tooth.dao.ProjectDAO;
import by.sender.tooth.model.Project;
import by.sender.tooth.services.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectDAO projectDAO;
		
	@Transactional
	public List<Project> getList() {
		return projectDAO.getList();
	}
	
	@Transactional
	public void save(Project project) {
		projectDAO.save(project);
	}
	
	@Transactional
	public Project fetchById(int id) {
		return projectDAO.fetchById(id);
	}

	@Transactional
	public Project fetchByName(String projectName) {
		return projectDAO.fetchByName(projectName);
	}

	@Transactional
	public void remove(String projectName) {	
		projectDAO.remove(projectName);
	}

}
