package by.sender.tooth.controllers;

import java.security.Principal;
import java.util.Set;

import javax.validation.Valid;

import by.sender.tooth.services.ProjectService;
import by.sender.tooth.services.TaskService;
import by.sender.tooth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import by.sender.tooth.model.Project;
import by.sender.tooth.model.Task;
import by.sender.tooth.model.User;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/projects/{projectName}")
	public String projectView(@PathVariable("projectName") String projectName, 
			Model model, Principal principal) {
		
		model.addAttribute("project", projectService.fetchByName(projectName));
		model.addAttribute("tasks", 
				taskService.getListInProject(projectService.fetchByName(projectName).getId()));
		
		model.addAttribute("userTasks", userService.fetchByName(principal.getName())
				.getListOfTasksInProject(projectService.fetchByName(projectName)));
		
		model.addAttribute("freeDevelopers", 
				userService.getListOfFreeDevs(projectService.fetchByName(projectName)));
		model.addAttribute("assignedDevelopers", 
				userService.getListInProject(projectService.fetchByName(projectName)));
		
		model.addAttribute("developer", new User());
		model.addAttribute("task", new Task());
		return "projectView";
	}
	
	@RequestMapping("projects/{projectName}/addTask")
	public String addComment(@PathVariable("projectName") String projectName,
			@Valid@ModelAttribute("task") Task task, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "redirect:/projects/" + projectName;
		}
		taskService.save(new Task(0, task.getName(), task.getDescription(), "waiting",
				projectService.fetchByName(projectName)));
		return "redirect:/projects/" + projectName;
	}
	
	@RequestMapping("projects/{projectName}/assign")
	public String assign(@PathVariable("projectName") String projectName, 
			@ModelAttribute("developer") User user, Model model) {
		
		model.addAttribute("developer", new User());		
		User userToUpdate = userService.fetchByName(user.getUsername());
		userToUpdate.getProjects().add(projectService.fetchByName(projectName));
		userService.save(userToUpdate);
		return "redirect:/projects/" + projectName;
	}
	
	@RequestMapping("projects/{projectName}/unsign")
	public String unsign(@PathVariable("projectName") String projectName, 
			@ModelAttribute("developer") User user, Model model) {
		
		model.addAttribute("developer", new User());
		User userToUpdate = userService.fetchByName(user.getUsername());
		
		Set<Project> projects=userToUpdate.getProjects();
		for(Project project : projects) {
            System.out.println("!!!!!!!!!!!!!!"+project+" "+projectService.fetchByName(projectName));
        }
		userToUpdate.removeFromProject(projectService.fetchByName(projectName));
		userService.save(userToUpdate);
		return "redirect:/projects/" + projectName;
	}
	
//	@RequestMapping("projects/{projectName}/edittask/{id}")
//	public String editTaskPage(@PathVariable("projectName") String projectName, 
//			@ModelAttribute("task") Task task, Model model) {
//		
//		
//		return "redirect:/projects/" + projectName;
//	}
}
