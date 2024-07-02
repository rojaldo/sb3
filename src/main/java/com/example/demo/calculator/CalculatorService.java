package com.example.demo.calculator;

import org.springframework.stereotype.Service;

enum CalculatorState {
    INIT,
    FIRST_NUMBER,
    SECOND_NUMBER,
    RESULT,
    ERROR
}

@Service
public class CalculatorService {

    CalculatorState currentState = CalculatorState.INIT;
    float firstNumber = 0;
    float secondNumber = 0;
    String operation = "";
    float result = 0;
    String resultString = "";

    public float getFirstNumber() {
        return firstNumber;
    }

    public float getSecondNumber() {
        return secondNumber;
    }

    public String getOperation() {
        return operation;
    }

    public float getResult() {
        return result;
    }

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

    public String process(String eval) {
        // read each character
        // if it is a number, call processNumber
        // if it is an symbol, call processSymbol

        for (int i = 0; i < eval.length(); i++) {
            char c = eval.charAt(i);
            if (Character.isDigit(c)) {
                // cast char to int
                processNumber(Character.getNumericValue(c));
            } else {
                processSymbol(c);
            }

        }
        if (this.currentState == CalculatorState.RESULT) {
            this.currentState = CalculatorState.INIT;
            return this.resultString;
        } else {
            this.currentState = CalculatorState.INIT;
            return "ERROR";
        }
    }

    void clearCalculator() {
        this.currentState = CalculatorState.INIT;
        this.firstNumber = 0;
        this.secondNumber = 0;
        this.operation = "";
        this.result = 0;
        this.resultString = "";
    }

    void processNumber(int number) {
        switch (this.currentState) {
            case INIT:
                this.clearCalculator();
                this.firstNumber = number;
                this.currentState = CalculatorState.FIRST_NUMBER;
                break;
            case FIRST_NUMBER:
                this.firstNumber = this.firstNumber * 10 + number;
                break;
            case SECOND_NUMBER:
                this.secondNumber = this.secondNumber * 10 + number;
                break;
            case RESULT:
                this.currentState = CalculatorState.ERROR;
                break;
            case ERROR:
            this.resultString = "ERROR";

                break;
            default:
                break;
        }
    }

    void processSymbol(char c) {
        switch (this.currentState) {
            case INIT:
                this.clearCalculator();
                this.currentState = CalculatorState.ERROR;
                break;
            case FIRST_NUMBER:
                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    this.operation = String.valueOf(c);
                    this.currentState = CalculatorState.SECOND_NUMBER;
                } else {
                    this.currentState = CalculatorState.ERROR;
                }
                break;
            case SECOND_NUMBER:
                if (c == '=') {
                    this.result = calculate(this.firstNumber, this.secondNumber, this.operation);
                    this.resultString = String.valueOf(this.result);
                    this.currentState = CalculatorState.RESULT;
                } else {
                    this.currentState = CalculatorState.ERROR;
                }
                break;
            case RESULT:
                this.currentState = CalculatorState.ERROR;
                break;
            case ERROR:
                this.resultString = "ERROR";
                break;
            default:
                break;
        }
    }

    public boolean isCorrect() {
        return this.currentState == CalculatorState.INIT;
    }
}
