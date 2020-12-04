package com.donelle_harris.blog.controllers;

import com.donelle_harris.blog.models.User;
import com.donelle_harris.blog.repositories.PostRepository;
import com.donelle_harris.blog.repositories.TagRepository;
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
    private final TagRepository tagDao;

    public PostController(PostRepository postDao, UserRepository userDao, TagRepository tagDao){
        this.postDao = postDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
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
    public String showCreatePostForm(Model model) {
        model.addAttribute("user", userDao.getOne(2L));
        model.addAttribute("post", new Post());
        List tagList = tagDao.findAll();
        model.addAttribute("tagList", tagList);
        return "posts/create";
    }
    @PostMapping("/posts/create")
    public String submitPost(@ModelAttribute Post post){
        post.setUser(userDao.getOne(2L));
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
    public String editPost (@ModelAttribute Post post){
        postDao.save(post);
        return "redirect:/posts/" + post.getId();
    }
}
