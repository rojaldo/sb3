package com.example.demo.calculator;

public class CalculatorErrorDto implements ICalculatorResponse {
    private String message;

    public CalculatorErrorDto() {
    }

    public CalculatorErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}