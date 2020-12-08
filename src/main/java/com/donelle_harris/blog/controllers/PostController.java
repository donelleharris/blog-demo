package com.donelle_harris.blog.controllers;

import com.donelle_harris.blog.models.User;
import com.donelle_harris.blog.repositories.ImageRepository;
import com.donelle_harris.blog.repositories.PostRepository;
import com.donelle_harris.blog.repositories.TagRepository;
import com.donelle_harris.blog.repositories.UserRepository;
import com.donelle_harris.blog.models.Post;
import com.donelle_harris.blog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final TagRepository tagDao;
    private final ImageRepository imageDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao,
                          UserRepository userDao,
                          TagRepository tagDao,
                          ImageRepository imageDao,
                          EmailService emailService
    ){
        this.imageDao = imageDao;
        this.postDao = postDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
        this.emailService = emailService;
    }


    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "term") String term, Model viewModel){
        term = "%"+term+"%";
        List searchPosts = postDao.findAllByTitleIsLike(term);
        viewModel.addAttribute("posts", searchPosts);
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
    public String submitPost(@ModelAttribute Post newPost ){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newPost.setUser(user);
        postDao.save(newPost);
        emailService.prepareAndSend(newPost, "Your post has been created ",
                "Your post: '" + newPost.getTitle() + "' has been posted on the Ipsum Blog.");
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String showIndividualPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getPostById(id));
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
        model.addAttribute("postToEdit", postDao.getPostById(id));

        return "/posts/edit";
    }
    @PostMapping("/post/{id}/edit")
    public String editPost (@ModelAttribute Post post){
        postDao.save(post);
        return "redirect:/posts/" + post.getId();
    }
}
