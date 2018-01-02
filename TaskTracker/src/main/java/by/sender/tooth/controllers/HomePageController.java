package by.sender.tooth.controllers;

import java.security.Principal;

import javax.validation.Valid;

import by.sender.tooth.model.Project;
import by.sender.tooth.services.AuthorityService;
import by.sender.tooth.services.ProjectService;
import by.sender.tooth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="/")
	public String loginPage(Model model, Principal principal) {
		
		String auth = authorityService.fetchByName(principal.getName()).getAuthority();
		
		model.addAttribute("project", new Project());
		
		if(auth.equals("ROLE_MANAGER")) {
			model.addAttribute("allProjects", projectService.getList());
			return "managerHomePage";
		}else {
			model.addAttribute("userProjects", 
					userService.fetchByName(principal.getName()).getProjects());
			return "developerHomePage";
		}
	}
	
	@RequestMapping(value="/projects/createproject")
	public String createProject(@Valid@ModelAttribute("project") Project project, 
			BindingResult result, Model model, Principal principal) {
		
		if(result.hasErrors()) {
			return "redirect:/";
		}
		project.setUser(userService.fetchByName(principal.getName()));		
		projectService.save(project);
		
		return "redirect:/";
	}
	
}
