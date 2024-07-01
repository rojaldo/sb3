package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GreetringController {

    @GetMapping("/greeting")
    public String greeting(
        @RequestParam(name="msg", required=false, defaultValue="World") String message,
        @RequestParam(name="age", required=false, defaultValue="30") int age,
        Model view) {
        System.out.println("Message: " + message);
        view.addAttribute("my_message", message);
        return "greeting";
    }
    
    
    
    
    
    
    
}
