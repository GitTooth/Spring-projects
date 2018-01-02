package by.sender.tooth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import by.sender.tooth.model.User;
import by.sender.tooth.model.VerificationToken;
import by.sender.tooth.services.TokenService;
import by.sender.tooth.services.UserService;

@Controller
public class VerificationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;

	@RequestMapping("/registration/verificate/{token}")
	public String verificate(@PathVariable("token") String token) {
		
		VerificationToken verToken = tokenService.fetchByToken(token);
		
		User user = userService.fetchById(verToken.getUser().getId());
		
		user.setEnabled(1);
		
		userService.save(user);
		
		return "login";
	}
	
}
