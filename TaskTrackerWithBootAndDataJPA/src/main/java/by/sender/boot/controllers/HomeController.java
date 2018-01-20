package by.sender.boot.controllers;

import by.sender.boot.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("allProjects", projectRepository.findAll());
        return "managerHomePage";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

}
