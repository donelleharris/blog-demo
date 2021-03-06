package com.donelle_harris.blog.controllers;

import com.donelle_harris.blog.models.Post;
import com.donelle_harris.blog.models.User;
import com.donelle_harris.blog.repositories.PostRepository;
import com.donelle_harris.blog.repositories.UserRepository;
import com.donelle_harris.blog.repositories.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;
    private UsersRepository users;
    private PasswordEncoder passwordEncoder;
    private PostRepository postDao;

    public UserController(UserRepository userDao, UsersRepository users, PasswordEncoder passwordEncoder, PostRepository postDao) {
        this.userDao = userDao;
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.postDao = postDao;
    }

    @GetMapping("/logout")
    public String logout(){
        //remove session user
        return "redirect:/index";
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }
    @GetMapping("/profile")
    public String showUserProfile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getUsername());
        model.addAttribute("posts", postDao.findAllByUser(user));
        System.out.println(postDao.findAllByUser(user));
        return "users/profile";
    }

    @GetMapping("user/{id}/edit")
    public String editUser(@PathVariable(value = "id") long id, Model model){
        model.addAttribute("userToEdit", userDao.getUserById(id));
        return "users/edit";
    }

    @PostMapping("user/{id}/edit")
    public String editUser (@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/profile";
    }
    @PostMapping("/user/{id}/delete")
    public String deletePost (@ModelAttribute User userToDelete){
        postDao.deleteAllByUserId(userToDelete.getId());
        userDao.delete(userToDelete);
        return "redirect:/posts";
    }
}
