package com.donelle_harris.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class rollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @PostMapping("/roll-dice/{num}")
    public String rollDice(@RequestParam(name = "dice") double num, Model model){

        int rolledNumber = (int) Math.random() * 6 + 1;
        boolean guessedCorrectly = rolledNumber == num;

        model.addAttribute("guessedCorrectly", guessedCorrectly);
        model.addAttribute("rolledNumber", rolledNumber);
        model.addAttribute("num", num);
        return "/roll-dice";

    }
}
