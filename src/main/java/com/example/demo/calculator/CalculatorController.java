package com.example.demo.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculator")
    public String getMethodName(@RequestParam(name = "n1", required = false, defaultValue = "3") float op1,
            @RequestParam(name = "n2", required = false, defaultValue = "2") float op2,
            @RequestParam(name = "op", required = false, defaultValue = "*") String operation,
            Model view) {
        float result = this.calculatorService.calculate(op1, op2, operation);
        view.addAttribute("res", result);
        view.addAttribute("num1", op1);
        view.addAttribute("num2", op2);
        view.addAttribute("op", operation);
        return "calculator";
    }

    

}
