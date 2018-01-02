package by.sender.tooth.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.sender.tooth.email.EmailController;
import by.sender.tooth.model.Authority;
import by.sender.tooth.model.User;
import by.sender.tooth.model.VerificationToken;
import by.sender.tooth.services.AuthorityService;
import by.sender.tooth.services.TokenService;
import by.sender.tooth.services.UserService;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String registrationPage(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@RequestMapping("/registration/registerNewUser")
	public String registerNewUser(@Valid @ModelAttribute("user") User user, BindingResult theBindingResult, ModelMap model) {
		
		if(theBindingResult.hasErrors()) {
			System.out.println("ERROR");
			return "registration";
		}
		
		System.out.println("!!!!!!!!!!!!!!!!!!!" + user.getUsername() + " " + user.getPassword() + " " 
				+ user.getEmail() + user.getAuthority());
				
		if(user.getAuthority() == "ROLE_DEVELOPER") {
			user.setIsDev(1);
		}
		
		userService.save(user);
		
		authorityService.save(new Authority(0, user.getUsername(), user.getAuthority()));
				
		User verUser = userService.fetchByName(user.getUsername());
		
		VerificationToken token = new VerificationToken(0, verUser);
		
		tokenService.save(token);
		
		EmailController.sendEmail(user.getEmail(), "http://localhost:8080/TaskTracker/registration/verificate/" + 
				token.getToken());
		
		return "verificationSentPage";
	}
}
