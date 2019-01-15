package pl.coderslab.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/registration")
    public String showRegistartionForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(path="/registration", method = RequestMethod.POST)
    public String registrationProcess(@ModelAttribute("user")User user) {

        String login = user.getLogin();
        String name = user.getUsername();
        String surname = user.getSurname();
        String password = user.getPassword();

        if (login != null && !login.isEmpty() && name != null && !name.isEmpty() && surname != null && !surname.isEmpty()
                && password != null && !password.isEmpty()) {

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

            Set<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            user.setActive(true);
            user.setPassword(hashedPassword);
            user.setUsername(name);
            user.setLogin(login);
            user.setSurname(surname);
            userRepository.save(user);

            return "success";
        }
        return "registration";
    }
}
