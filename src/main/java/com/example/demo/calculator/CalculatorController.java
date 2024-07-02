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
    public String getMethodName(@RequestParam(name = "n1", required = false, defaultValue = "0") float op1,
            @RequestParam(name = "n2", required = false , defaultValue = "0") float op2,
            @RequestParam(name = "op", required = false, defaultValue = "") String operation,
            @RequestParam(name = "eval", required = false, defaultValue = "") String eval,
            Model view) {
                if (!eval.equals("") &&  op1 == 0 && op2 == 0 && operation.equals("")) {
                    String evalStr = this.calculatorService.process(eval);
                    view.addAttribute("res", this.calculatorService.getResult());
                    view.addAttribute("num1", this.calculatorService.getFirstNumber());
                    view.addAttribute("num2", this.calculatorService.getSecondNumber());
                    view.addAttribute("op", this.calculatorService.getOperation());
                    view.addAttribute("correct", evalStr.equals("ERROR") ? false : true);
                    
                }else if (eval.equals("") ) {
                    view.addAttribute("res", 0);
                    view.addAttribute("num1", 0);
                    view.addAttribute("num2", 0);
                    view.addAttribute("op", "");
                    view.addAttribute("correct", true);
                }
        float result = this.calculatorService.calculate(op1, op2, operation);
        view.addAttribute("res", result);
        view.addAttribute("num1", op1);
        view.addAttribute("num2", op2);
        view.addAttribute("op", operation);
        view.addAttribute("correct", true);
        return "calculator";
    }

    

}
