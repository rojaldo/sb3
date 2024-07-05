package com.example.demo.library.user;


import org.aspectj.lang.annotation.RequiredTypes;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements IResponseDto {

    @JsonProperty("id")
    long id;

    @JsonProperty("name")
    @Pattern(regexp = "^[a-z A-Z]*$")
    @NotBlank
    private String name;

    @JsonProperty("email")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    private Address address;
    
}
