package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.Project;
import pl.coderslab.entity.User;
import pl.coderslab.repository.ProjectRepository;
import pl.coderslab.repository.UserRepository;

import java.util.Collection;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/project/add")
    public String showAddProjectsForm(final Model model) {
        final Project project = new Project();
        model.addAttribute("project", project);
        return "addProject";
    }

    @PostMapping(path = "/project/add")
    public String processAddProjectsForm(final Project project) {

        projectRepository.add(project);
        return "redirect:listProject";
    }

    @GetMapping(path = "/project/list")
    public String showAllProjects(final Model model) {

        final Collection<Project> projects = projectRepository.findAll();

        model.addAttribute("projects", projects);
        return "project/listProject";
    }
    @ModelAttribute("user")
    public Collection<User> user() {
        final Collection<User> user = userRepository.findAll();
        return user;
    }
}