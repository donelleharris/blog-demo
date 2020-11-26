package com.donelle_harris.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao){
        this.postDao = postDao;
    }

    @GetMapping("/posts/index")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
//        List<String> posts = new ArrayList<>();
//        posts.add("This is the first post");
//        posts.add("This is the second post");
//        model.addAttribute("posts", posts);
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

    @GetMapping("/posts/edit/{id}")
    @ResponseBody
    public String showEditPostForm() {
        return "view the form for editing a post";
    }
    @PostMapping("/posts/edit")
    @ResponseBody
    public String editPost() {
        return "push a post edit to the database";
    }

    @GetMapping("/posts/delete/{id}")
    @ResponseBody
    public String  deletePost(long id){

        return "/posts/delete";
    }
    @PostMapping("/posts/delete")
    public String deletePost(@PathVariable long id, Model model){
        //get the id of the post to be deleted and pass it to the deletePost() method
        postDao.deleteById(id);
        //return success message
        model.addAttribute("message", ("Post # " + id + " deleted."));

        return "/posts/delete";
    }
}
