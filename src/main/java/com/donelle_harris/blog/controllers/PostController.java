package com.donelle_harris.blog.controllers;

import com.donelle_harris.blog.models.User;
import com.donelle_harris.blog.repositories.PostRepository;
import com.donelle_harris.blog.repositories.UserRepository;
import com.donelle_harris.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String index(Model viewModel) {
        viewModel.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }
    @GetMapping("/posts/search")
    public String search(@RequestParam(name = "term") String term, Model viewModel){
        term = "%"+term+"%";
        List posts = postDao.findAllByTitleIsLike(term);
        viewModel.addAttribute("posts", posts);
        return "/posts/index";
    }

    @GetMapping("/posts/create")
    public String showCreatePostForm() {
        return "posts/new";
    }
    @PostMapping("/posts/create")
    public String submitPost(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
    ){
        User user = userDao.getOne(1L);
        Post post = new Post(user, title, body);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String showIndividualPost(@PathVariable long id, Model model) {
        Post post = postDao.getPostById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @PostMapping("/post/{id}/delete")
    public String deletePost (@PathVariable(value = "id") long id){
        Post post = postDao.getPostById(id);
        postDao.delete(post);
        return "redirect:/posts";
    }

    @GetMapping("/post/{id}/edit")
    public String editPost(@PathVariable(value = "id") long id, Model model){
        Post post = postDao.getPostById(id);
        model.addAttribute("postToEdit", post);
        return "/posts/edit";
    }
    @PostMapping("/post/{id}/edit")
    public String editPost (
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body,
            @PathVariable long id){
        Post post = new Post(id, title, body);
        postDao.save(post);
        return "redirect:/posts";
    }
}
