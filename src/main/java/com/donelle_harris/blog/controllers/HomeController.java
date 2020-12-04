package com.donelle_harris.blog.controllers;

import com.donelle_harris.blog.models.Post;
import com.donelle_harris.blog.repositories.PostRepository;
import com.donelle_harris.blog.repositories.TagRepository;
import com.donelle_harris.blog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final TagRepository tagDao;

    public HomeController(PostRepository postDao, UserRepository userDao, TagRepository tagDao){
        this.postDao = postDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        List postList = postDao.findAll();
        int lastPostIndex = postList.size()-1;
        Post lastPost = (Post) postList.get(lastPostIndex);
        model.addAttribute("post", lastPost);
        return "/home";
    }

    @GetMapping("/home")
    public String welcome(Model model){
        List postList = postDao.findAll();
        int lastPostIndex = postList.size()-1;
        Post lastPost = (Post) postList.get(lastPostIndex);
        model.addAttribute("post", lastPost);
        return "/home";
    }
}