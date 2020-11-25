package com.donelle_harris.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @GetMapping("/posts/index")
    public String index(Model model) {
        List<String> posts = new ArrayList<>();
        posts.add("This is the first post");
        posts.add("This is the second post");
        model.addAttribute("posts", posts);
        return "/posts/index";
    }
    @GetMapping("/posts/show/{id}")
    public String showIndividualPost(@PathVariable long id, Model model) {
        Post post = new Post();
        String title = "This is a title.";
        String body = "This is the body of the post.";
        model.addAttribute("id", "Post id#: " + id);
        model.addAttribute("title", title);
        model.addAttribute("body", body);
        return "/posts/show";
    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String showCreatePostForm() {
        return "view the form for creating a post";
    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String submitPost() {
        return "push a new post to the database";
    }
}
