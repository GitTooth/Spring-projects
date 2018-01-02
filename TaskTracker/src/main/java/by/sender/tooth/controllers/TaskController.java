package by.sender.tooth.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import by.sender.tooth.model.Comment;
import by.sender.tooth.model.Task;
import by.sender.tooth.model.User;
import by.sender.tooth.services.CommentService;
import by.sender.tooth.services.ProjectService;
import by.sender.tooth.services.TaskService;
import by.sender.tooth.services.UserService;

@Controller
public class TaskController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/projects/{projectName}/{taskName}")
	public String projectView(@PathVariable("projectName") String projectName, 
			@PathVariable("taskName") String taskName, Model model) {
		
		model.addAttribute("comments", 
				commentService.getListInProject(taskService.fetchByName(taskName).getId()));
		
		model.addAttribute("project", projectService.fetchByName(projectName));
		model.addAttribute("task", taskService.fetchByName(taskName));		
		
		model.addAttribute("developersInProject", 
				userService.getListInProject(projectService.fetchByName(projectName)));
		
		model.addAttribute("assignedDevelopersOnTask", 
				userService.getListInTask(taskService.fetchByName(taskName)));
		model.addAttribute("developer", new User());
		model.addAttribute("comment", new Comment());
		return "taskView";
	}
	
	@RequestMapping("projects/{projectName}/{taskName}/editTask")
	public String editTask(@PathVariable("projectName") String projectName,
			@PathVariable("taskName") String taskName,
			@Valid@ModelAttribute("task") Task task, BindingResult result, Model model) {
		
		if(task.getDescription() == "") {
			return "redirect:/projects/" + projectName + "/" + taskName;
		}
		
		Task editedTask = taskService.fetchById(task.getId());
		editedTask.setDescription(task.getDescription());
		editedTask.setStatus(task.getStatus());
		taskService.save(editedTask);
		return "redirect:/projects/" + projectName + "/" + taskName;
	}
	
	@RequestMapping("projects/{projectName}/{taskName}/assign")
	public String assign(@PathVariable("projectName") String projectName,
			@PathVariable("taskName") String taskName,
			@ModelAttribute("developer") User user, Model model) {
		
		model.addAttribute("developer", new User());	
		
		User userToUpdate = userService.fetchByName(user.getUsername());
		userToUpdate.getTasks().add(taskService.fetchByName(taskName));
		userService.save(userToUpdate);
		return "redirect:/projects/" + projectName + "/" + taskName;
	}
	
	@RequestMapping("projects/{projectName}/{taskName}/unsign")
	public String unsign(@PathVariable("projectName") String projectName,
			@PathVariable("taskName") String taskName,
			@ModelAttribute("developer") User user, Model model) {
		
		model.addAttribute("developer", new User());
		User userToUpdate = userService.fetchByName(user.getUsername());
		userToUpdate.removeFromTask(taskService.fetchByName(taskName));
		userService.save(userToUpdate);
		return "redirect:/projects/" + projectName + "/" + taskName;
	}
	
	@RequestMapping("projects/{projectName}/{taskName}/addComment")
	public String addComment(@PathVariable("projectName") String projectName,
			@PathVariable("taskName") String taskName,
			@Valid@ModelAttribute("comment") Comment comment, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "redirect:/projects/" + projectName + "/" + taskName;
		}
		System.out.println(comment.getAuthor());
		User author = userService.fetchByName(comment.getAuthor());
		System.out.println(comment.getText()+ author.getUsername()+ taskService.fetchByName(taskName).getId());
		commentService.save(new Comment(0, author, comment.getText(), taskService.fetchByName(taskName)));
		return "redirect:/projects/" + projectName + "/" + taskName;
	}
	
	@RequestMapping("projects/{projectName}/{taskName}/removeComment")
	public String removeComment(@PathVariable("projectName") String projectName,
			@PathVariable("taskName") String taskName,
			@ModelAttribute("comment") Comment comment, Model model) {
		
		commentService.removeById(comment.getId());
		return "redirect:/projects/" + projectName + "/" + taskName;
	}
	
	@RequestMapping("projects/{projectName}/{taskName}/editComment")
	public String editComment(@PathVariable("projectName") String projectName,
			@PathVariable("taskName") String taskName,
			@Valid@ModelAttribute("comment") Comment comment, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "redirect:/projects/" + projectName + "/" + taskName;
		}
		Comment editedComment = commentService.fetchById(comment.getId());
		editedComment.setText(comment.getText());
		commentService.save(editedComment);
		return "redirect:/projects/" + projectName + "/" + taskName;
	}
	
}
