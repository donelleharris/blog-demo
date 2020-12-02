package com.donelle_harris.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DiceController {
    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

//    My roll dice solution:
//    @PostMapping("/roll-dice")
//    public String rollDice(@RequestParam(name = "num") int num, Model model){
//        int rolledNumber = (int) (Math.random() * 6 + 1);
//        if(num == rolledNumber){
//            model.addAttribute("rolledNumber", rolledNumber);
//            model.addAttribute("num", num);
//            model.addAttribute("message1", "You chose: " + num + ". The die rolled: "+ rolledNumber);
//            model.addAttribute("message2", " You guessed correctly!");
//        } else if (num != rolledNumber){
//            model.addAttribute("rolledNumber", rolledNumber);
//            model.addAttribute("num", num);
//            model.addAttribute("message1", "You chose: " + num + ". The die rolled: "+ rolledNumber);
//            model.addAttribute("message2", "Your guess was not correct.");
//        }
//
//        return "roll-dice";
//    }

//    Their roll dice solution:
@GetMapping("/roll-dice/{guess}")
public String rollDice(@PathVariable int guess, Model model){
    int rolledNumber = (int) (Math.random() * 6 + 1);
    model.addAttribute("rolledNumber", rolledNumber);
    model.addAttribute("user-guess", guess);
    model.addAttribute("isCorrect", guess == rolledNumber);
    if(guess == rolledNumber){
        model.addAttribute("message1", "You chose: " + guess + ". The die rolled: "+ rolledNumber);
        model.addAttribute("message2", " You guessed correctly!");
    } else {
        model.addAttribute("message1", "You chose: " + guess + ". The die rolled: " + rolledNumber);
        model.addAttribute("message2", " Your guess was not correct.");
    }

    return "roll-dice";
}
}
