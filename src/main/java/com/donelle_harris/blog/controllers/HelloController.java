package com.donelle_harris.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
    @GetMapping("/hello")
    public String showGenericHelloMessage(){
        return "/hello";
    }

    @GetMapping("/join")
    public String showJoinForm(Model model) {
        List<String> cohortNames = new ArrayList<>();
        cohortNames.add("COBOL");
        cohortNames.add("Draco");

        model.addAttribute("cohortNames", cohortNames);
        return "/join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "/join";
    }
}
