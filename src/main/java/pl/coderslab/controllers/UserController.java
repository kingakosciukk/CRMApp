package pl.coderslab.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/registration")
    public String showRegistartionForm(Model model) {
        final User user = new User();
        model.addAttribute("user", user);
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

            user.setPassword(hashedPassword);
            user.setUsername(name);
            user.setLogin(login);
            user.setSurname(surname);
            user.setEnabled(true);
            userRepository.add(user);

            return "success";
        }
        return "registration";
    }

    @GetMapping(path = "/user/list")
    public String showAllUSers(Model model) {

        Collection<User> users = userRepository.findAll();

        model.addAttribute("users", users);
        return "listUser";
    }

    @GetMapping(path = "/user/edit")
    public String showEditForm(final @RequestParam(name = "id") Long id, final Model model) {

        final User user = userRepository.findById(id);

        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(path = "/user/edit")
    public String editBook(final @Valid User user, final BindingResult bresult) {

        if(bresult.hasErrors()) {
            return "editUser";
        }

        userRepository.edit(user);

        return "redirect:list";
    }

    @GetMapping(path = "/user/remove")
    public String showDeleteConfirmForm(final @RequestParam(name = "id") Long id, final Model model) {

        final User user = userRepository.findById(id);

        model.addAttribute("user", user);

        return "removeUser";
    }

    @PostMapping(path = "/user/remove")
    public String deleteBook(final @RequestParam(name = "id", required = true) Long id) {

        userRepository.removeById(id);

        return "redirect:list";
    }
}
