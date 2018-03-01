package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Repositories.*;
import com.example.demo.Config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    // Autowired Repositories
    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserRepository appUserRepository;


    /////////////// METHODS //////////////////

    @RequestMapping("/")
    public String index() {
        return "homepage";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Security and User Methods

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("newUser", new AppUser());
        return "registration";
    }

    @PostMapping("/register")
    public String addNewUser(@Valid @ModelAttribute("newUser") AppUser newUser, BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "registration";
        } else {

            model.addAttribute(newUser.getAppUsername() + " created");
            appUserRepository.save(newUser);
            AppRole r = appRoleRepository.findAppRoleByRoleName("USER");
            newUser.addRole(r);
            appUserRepository.save(newUser);
            return "redirect:/login";
        }
    }



}
