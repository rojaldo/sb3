package com.example.demo.library.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorUserDto implements IResponseDto {
    
    private String message;
    private UserDto query;

       
}
