package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Project;
import pl.coderslab.entity.User;
import pl.coderslab.repository.ProjectRepository;
import pl.coderslab.repository.UserRepository;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/project/add")
    public String showAddProjectsForm(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "addProject";
    }
//
//    @RequestMapping(path = "/project/add", method = RequestMethod.POST)
//    public String addProjectProcess (@ModelAttribute("project")Project project)(
//
//
//    )

    @GetMapping(path = "/project/list")
    public String showAllProjects(final Model model) {

         List<Project> projects = projectRepository.findAll();

        model.addAttribute("projects", projects);
        return "project/listProject";
    }
    @ModelAttribute("user")
    public List<User> user() {
         List<User> user = userRepository.findAll();
        return user;
    }
}