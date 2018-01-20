package by.sender.boot.controllers;

import by.sender.boot.entities.Comment;
import by.sender.boot.entities.Project;
import by.sender.boot.entities.Task;
import by.sender.boot.entities.User;
import by.sender.boot.repository.CommentRepository;
import by.sender.boot.repository.ProjectRepository;
import by.sender.boot.repository.TaskRepository;
import by.sender.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class TaskController {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	CommentRepository commentRepository;
	
	@RequestMapping("/TaskTracker/projects/{projectName}/{taskName}")
	public String projectView(@PathVariable("projectName") String projectName, 
			@PathVariable("taskName") String taskName, Model model) {

		Project project = projectRepository.findByName(projectName).get(0);
		Task task = taskRepository.findByName(taskName).get(0);

		model.addAttribute("project", project);
		model.addAttribute("task", task);
		
		model.addAttribute("developersInProject", 
				userRepository.findNotAssignedOnTask(task));
		model.addAttribute("assignedDevelopersOnTask", 
				userRepository.findAssignedOnTask(task));

		model.addAttribute("developer", new User());
		model.addAttribute("comment", new Comment());
		return "taskView";
	}
	
	@RequestMapping("/TaskTracker/projects/{projectName}/{taskName}/editTask")
	public String editTask(@PathVariable("projectName") String projectName,
						   @PathVariable("taskName") String taskName,
						   @Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {

		if(task.getDescription() == "") {
			return "redirect:/projects/" + projectName + "/" + taskName;
		}

		Task editedTask = taskRepository.findByName(taskName).get(0);
		editedTask.setDescription(task.getDescription());
		editedTask.setStatus(task.getStatus());
		taskRepository.save(editedTask);
		return "redirect:/TaskTracker/projects/" + projectName + "/" + taskName;
	}

	@RequestMapping("/TaskTracker/projects/{projectName}/{taskName}/assign")
	public String assign(@PathVariable("projectName") String projectName,
			@PathVariable("taskName") String taskName,
			@ModelAttribute("developer") User user, Model model) {

		User userToUpdate = userRepository.findById(user.getId()).get(0);
		userToUpdate.getTasks().add(taskRepository.findByName(taskName).get(0));
		userRepository.save(userToUpdate);
		return "redirect:/TaskTracker/projects/" + projectName + "/" + taskName;
	}

	@RequestMapping("/TaskTracker/projects/{projectName}/{taskName}/unsign")
	public String unsign(@PathVariable("projectName") String projectName,
			@PathVariable("taskName") String taskName,
			@ModelAttribute("developer") User user, Model model) {

		User userToUpdate = userRepository.findById(user.getId()).get(0);
		userToUpdate.getTasks().remove(taskRepository.findByName(taskName).get(0));
		userRepository.save(userToUpdate);
		return "redirect:/TaskTracker/projects/" + projectName + "/" + taskName;
	}

	@RequestMapping("/TaskTracker/projects/{projectName}/{taskName}/addComment")
	public String addComment(@PathVariable("projectName") String projectName,
			@PathVariable("taskName") String taskName,
			@Valid@ModelAttribute("comment") Comment comment, BindingResult result, Model model) {

		if(result.hasErrors()) {
			return "redirect:/projects/" + projectName + "/" + taskName;
		}
		User author = userRepository.findByUsername(comment.getUser().getUsername()).get(0);
		commentRepository.save(new Comment(0, author, comment.getText(), taskRepository.findByName(taskName).get(0)));
		return "redirect:/TaskTracker/projects/" + projectName + "/" + taskName;
	}

	@RequestMapping("/TaskTracker/projects/{projectName}/{taskName}/removeComment")
	public String removeComment(@PathVariable("projectName") String projectName,
			@PathVariable("taskName") String taskName,
			@ModelAttribute("comment") Comment comment, Model model) {

		commentRepository.delete(comment.getId());
		return "redirect:/TaskTracker/projects/" + projectName + "/" + taskName;
	}

	@RequestMapping("/TaskTracker/projects/{projectName}/{taskName}/editComment")
	public String editComment(@PathVariable("projectName") String projectName,
			@PathVariable("taskName") String taskName,
			@Valid@ModelAttribute("comment") Comment comment, BindingResult result, Model model) {

		if(result.hasErrors()) {
			return "redirect:/projects/" + projectName + "/" + taskName;
		}
		Comment editedComment = commentRepository.findOne(comment.getId());
		editedComment.setText(comment.getText());
		commentRepository.save(editedComment);
		return "redirect:/TaskTracker/projects/" + projectName + "/" + taskName;
	}
	
}
