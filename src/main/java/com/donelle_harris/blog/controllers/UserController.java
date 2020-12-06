package com.donelle_harris.blog.controllers;

import com.donelle_harris.blog.models.User;
import com.donelle_harris.blog.repositories.UserRepository;
import com.donelle_harris.blog.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserRepository userDao;
    private UsersRepository users;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, UsersRepository users, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    @GetMapping("/logout")
    public String logout(){
        //remove session user
        return "redirect:/home";
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user,
                           @RequestParam (name = "password") String password,
                           @RequestParam (name = "password-confirm") String passwordConfirm) {
        if (password.equals(passwordConfirm)) {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            users.save(user);
            return "redirect:users/login";
        } else return "users/sign-up";

    }
}
