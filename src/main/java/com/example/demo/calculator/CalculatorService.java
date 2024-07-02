package com.example.demo.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    
    public float calculate(float op1, float op2, String operation) {
        float result = 0;
        switch (operation) {
            case "+":
                result = op1 + op2;
                break;
            case "-":
                result = op1 - op2;
                break;
            case "*":
                result = op1 * op2;
                break;
            case "/":
                result = op1 / op2;
                break;
            default:
                break;
        }
        return result;
    }
}
