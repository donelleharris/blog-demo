package com.donelle_harris.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public int doAddition(@PathVariable int num1, int num2){
        return num1 + num2;
    }
    @GetMapping("/subtract{num1}/from/{num2}")
    @ResponseBody
    public int doSubtraction(@PathVariable int num1, int num2){
        return num2 - num1;
    }
    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public int doMultiplication(@PathVariable int num1, int num2){
        return num1*num2;
    }
    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public double doDivision(@PathVariable double num1, double num2){
        return num1/num2;
    }
}
