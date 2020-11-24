package com.donelle_harris.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String post() {
        return "Posts index page";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postById(@PathVariable int id) {
        return "Post #" + id;
    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostFormView() {
        return "view the form for creating a post";
    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String createPostFormPush() {
        return "push a new post to the database";
    }
}
