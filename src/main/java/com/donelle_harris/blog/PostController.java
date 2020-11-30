package com.donelle_harris.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String index(Model model) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Post 1", "This is the first post"));
        posts.add(new Post("Post 2", "This is the second post"));
        posts.add(new Post("Post 3", "This is the third post"));

        model.addAttribute("posts", posts);
        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String showIndividualPost(@PathVariable long id, Model model) {
        Post post = new Post("This is a title for post #" + id, "This is the body of the post.");
        model.addAttribute("post", post);
        return "posts/show";
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
