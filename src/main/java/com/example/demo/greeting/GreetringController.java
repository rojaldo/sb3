package com.example.demo.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GreetringController {

    @GetMapping("/greeting")
    public String greeting(
        @RequestParam(name="msg", required=false, defaultValue="hola") String message,
        @RequestParam(name="age", required=false, defaultValue="30") int age,
        Model view) {
        view.addAttribute("my_message", message);
        return "greeting";
    }
    
}
