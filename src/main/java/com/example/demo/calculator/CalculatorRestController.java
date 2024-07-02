package com.example.demo.calculator;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1")
public class CalculatorRestController {

    @GetMapping("/calculator")
    public ResponseEntity<Map<String, String>> getMethodName(
            @RequestParam(name = "n1", required = false, defaultValue = "0") float op1,
            @RequestParam(name = "n2", required = false, defaultValue = "0") float op2,
            @RequestParam(name = "op", required = false, defaultValue = "") String operation) {
                
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Hello World"));
    }

}
