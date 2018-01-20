package by.sender.boot.controllers;

import java.security.Principal;

import by.sender.boot.entities.Project;
import by.sender.boot.entities.Task;
import by.sender.boot.entities.User;
import by.sender.boot.repository.ProjectRepository;
import by.sender.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;
	
	@RequestMapping("/TaskTracker/projects/{projectName}")
	public String projectView(@PathVariable("projectName") String projectName, 
			Model model, Principal principal) {
        Project project = projectRepository.findByName(projectName).get(0);

		model.addAttribute("project", project);

        model.addAttribute("freeDevelopers",
		        userRepository.findNotAssignedOnProject(project));

		model.addAttribute("assignedDevelopers",
				userRepository.findAssignedOnProject(project));
		
		model.addAttribute("developer", new User());
		model.addAttribute("task", new Task());
		return "projectView";
	}

    @RequestMapping("/TaskTracker/projects/{projectName}/assign")
    public String assign(@PathVariable("projectName") String projectName,
                         @ModelAttribute("developer") User user, Model model) {
        User userToUpdate = userRepository.findById(user.getId()).get(0);
        userToUpdate.getProjects().add(projectRepository.findByName(projectName).get(0));
        userRepository.save(userToUpdate);
        return "redirect:/TaskTracker/projects/" + projectName;
    }

	@RequestMapping("TaskTracker/projects/{projectName}/unsign")
	public String unsign(@PathVariable("projectName") String projectName,
						 @ModelAttribute("developer") User user, Model model) {

		User userToUpdate = userRepository.findById(user.getId()).get(0);
		Project project = projectRepository.findByName(projectName).get(0);
		userToUpdate.getProjects().remove(project);

		userRepository.save(userToUpdate);
		return "redirect:/TaskTracker/projects/" + projectName;
	}
}
