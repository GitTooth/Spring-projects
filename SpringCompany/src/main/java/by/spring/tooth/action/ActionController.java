package by.spring.tooth.action;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import by.spring.tooth.model.Authority;
import by.spring.tooth.model.User;
import by.spring.tooth.services.AuthorityService;
import by.spring.tooth.services.UserService;

@Controller
public class ActionController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping("/update")
	public String update(@Valid @ModelAttribute("user") User user, BindingResult theBindingResult, Model model) {
		
		if(theBindingResult.hasErrors()) {
			return "homePage";
		}
				
		User oldUser = userService.fetchById(user.getId());
		
		if((!oldUser.getUsername().equals(user.getUsername())) || (!oldUser.getMail().equals(user.getMail()))) {
			if(!oldUser.getUsername().equals(user.getUsername())){
				Authority userFromAuth = authorityService.fetchByName(oldUser.getUsername());
				
				Authority auth = new Authority(userFromAuth.getId(), user.getUsername(), userFromAuth.getAuthority());
				
				authorityService.save(auth);
			}
			userService.save(user);
			return "redirect:/login?logout";
		}
		
		return "redirect:/";
		
	}
	
	@RequestMapping("/updateUser")
	public String updateAuth(@Valid @ModelAttribute("authority") Authority auth, BindingResult theBindingResult, Model model) {
		
		if(theBindingResult.hasErrors()) {
			System.out.println("ERROR");
			return "admin";
		}
		
		System.out.println(auth.getUsername() + " " + auth.getAuthority());
		
		authorityService.save(auth);
		return "redirect:/adminArea";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(@Valid @ModelAttribute("authority") Authority auth, BindingResult theBindingResult, Model model) {
		
		if(theBindingResult.hasErrors()) {
			System.out.println("ERROR");
			return "admin";
		}
		
		System.out.println(auth.getUsername() + " " + auth.getAuthority());
		
		authorityService.remove(auth.getUsername());
		userService.remove(auth.getUsername());
		return "redirect:/adminArea";
	}
	
	@RequestMapping("/createUser")
	public String createUser(@Valid @ModelAttribute("authority") Authority auth, BindingResult theBindingResult, Model model) {
		
		if(theBindingResult.hasErrors()) {
			System.out.println("ERROR");
			return "admin";
		}
		
		System.out.println(auth.getUsername() + " " + auth.getAuthority());
		
		User user = new User(0, auth.getUsername(), "1234", "MailToChange@gmail.com", 1);
		
		userService.save(user);
		authorityService.save(auth);
		return "redirect:/adminArea";
	}
	
	@RequestMapping("/disableUser")
	public String disableUser(@Valid @ModelAttribute("user") User user, BindingResult theBindingResult, Model model) {
		
		if(theBindingResult.hasErrors()) {
			System.out.println("ERROR");
			return "admin";
		}
		User userToChange = userService.fetchByName(user.getUsername());
		if(userToChange.getEnabled() == 1) {
			userToChange.setEnabled(0);
		}else {
			userToChange.setEnabled(1);
		}
		userService.save(userToChange);
		return "redirect:/adminArea";
	}
}
