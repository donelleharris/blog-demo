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
    @GetMapping("/posts")
    public String index(Model viewModel) {
        viewModel.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String showIndividualPost(@PathVariable long id, Model model) {
        Post post = new Post("This is a title for post #" + id, "This is the body of the post.");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreatePostForm() {
        return "posts/new";
    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String submitPost(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
    ){
        Post post = new Post(title, body);
        post = postDao.save(post);
        return "push a new post to the database with the id: " + post.getId();
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
    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id, Model model){
        //get the id of the post to be deleted and pass it to the deletePost() method
        postDao.deleteById(id);
        //return success message
        if(postDao.findById(id) == null) {
            model.addAttribute("message", ("Post # " + id + " deleted."));
        } else model.addAttribute("message", ("Post # " + id + " was not deleted."));
        return "/posts/delete";
    }
}
