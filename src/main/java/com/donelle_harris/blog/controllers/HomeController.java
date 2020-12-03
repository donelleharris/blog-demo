package com.donelle_harris.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "/posts/index";
    }

    @GetMapping("/home")
    public String welcome(){
        return "/posts/index";
    }
}