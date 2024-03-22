package com.yasir.jobapp.controller;


import com.yasir.jobapp.entities.User;
import com.yasir.jobapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    //  Show Sign Up Page
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Assuming User is your model object
        return "register"; // Assuming "register" is the name of your Thymeleaf template
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, Model model) {
        boolean isPresent = userService.checkByEmail(user.getEmail());
        if (isPresent) {
            model.addAttribute("errorMessage", "User Already Exists Please login");
        } else {
            User user1 = userService.saveUser(user);
            if (user1 != null) {
                model.addAttribute("msg", "User Registered Successfully Please login");
            }
            else {
                model.addAttribute("errorMessage","Something Went Wrong");
            }
        }
        return "redirect:/register";
    }

    //  Show Sign IN Page
    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @PostMapping("/signInUser")
    public String signInUser(@RequestParam String email, @RequestParam String password, Model model) {
        boolean isPresent = userService.authenticateUser(email, password);
        if (isPresent) {
            return "redirect:/home"; // Redirect to the home page upon successful sign-in
        }
        model.addAttribute("errorMessage", "User does not exist. Please try again."); // Adding error message to display in the view
        return "signin"; // Return the name of the login template to display the error message
    }


    @GetMapping("/home")
    public String userHome() {
        return "/home"; // Assuming "user/home" is the name of your Thymeleaf template for the home page
    }

}
