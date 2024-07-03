package com.example.demo.calculator;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculatorResultDto implements ICalculatorResponse {

    @JsonProperty("number_1")
    private float num1;
    @JsonProperty("number_2")
    private float num2;
    @JsonProperty("operation")
    private String op;
    @JsonProperty("result")
    private float res;

}
