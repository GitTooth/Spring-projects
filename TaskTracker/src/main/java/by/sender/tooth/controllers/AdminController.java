package by.sender.tooth.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.sender.tooth.model.Authority;
import by.sender.tooth.services.AuthorityService;

@Controller
public class AdminController {
	
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping(value="/adminArea", method=RequestMethod.GET)
	public String adminPage(@Valid @ModelAttribute("authority") Authority auth, BindingResult theBindingResult, Model model) {
			     
		List<Authority> authorities = authorityService.getList();
		
		for (int i = 0; i < authorities.size(); i++) {
			System.out.println(authorities.get(i).getUsername());
		}
		
		model.addAttribute("authorities", authorities);
		
		List<String> authorityNames = authorityService.getListOfNames();
		
		for (int i = 0; i < authorityNames.size(); i++) {
			System.out.println(authorityNames.get(i));
		}
	     
	    model.addAttribute("authoritiesNameList", authorityNames);
		
		return "admin";
	}
}
