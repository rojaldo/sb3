package com.example.demo.calculator;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1")
public class CalculatorRestController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculator")
    public ResponseEntity<ICalculatorResponse> getMethodName(
            @RequestParam(name = "n1", required = false, defaultValue = "0") float num1,
            @RequestParam(name = "n2", required = false, defaultValue = "0") float num2,
            @RequestParam(name = "op", required = false, defaultValue = "") String operator) {
        if (num1 > 1000 || num1 < -1000 || num2 > 1000 || num2 < -1000 || !isOperator(operator)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CalculatorErrorDto("Invalid input. Please check the input values: " + num1 + ", " + num2
                            + ", " + operator + ". The numbers should be between -1000 and 1000 and the operator should be +, -, * or /."));
        }

        float result = calculatorService.calculate(num1, num2, operator);

        return ResponseEntity.status(HttpStatus.OK).body(CalculatorResultDto.builder().num1(num1).num2(num2).op(operator)
                .res(result).build());
    }

    boolean isOperator(String operator) {
        if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
            return true;
        }
        return false;
    }

}
