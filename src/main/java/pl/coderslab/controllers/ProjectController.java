package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Project;
import pl.coderslab.entity.User;
import pl.coderslab.repository.ProjectRepository;
import pl.coderslab.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping(path = "/project/add")
    public String showAddProjectsForm(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "projectAdd";
    }
    @RequestMapping(path="/project/add", method = RequestMethod.POST)
    public String projectProccess(@ModelAttribute("project") Project project) {

        String name = project.getName();
        String desc = project.getDescription();
        String website = project.getWebsite();
        List<User> userproject = project.getUsers();
        if (name != null && !name.isEmpty() && desc != null && !desc.isEmpty()
                && website != null && !website.isEmpty()) {

            project.setActive(true);
            project.setDescription(desc);
            project.setName(name);
            project.setWebsite(website);
            project.setUsers(userproject);
            projectRepository.save(project);

            return "success";
        }
        return "projectAdd";
    }

    @GetMapping(path = "/project/list")
    public String showAllProjects(final Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projectList";
    }
}