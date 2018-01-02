package by.spring.tooth.form;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import by.spring.tooth.services.AuthorityService;
import by.spring.tooth.services.UserService;

@Controller
public class HomePageController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping(value="/")
	public String loginPage(Model model, Principal principal) {
		model.addAttribute("user", userService.fetchByName(principal.getName()));
		model.addAttribute("role", authorityService.fetchByName(principal.getName()));
		return "homePage";
	}
	
}
