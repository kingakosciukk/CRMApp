package pl.coderslab.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UserLoginController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/loginError")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "login";

    }
}
