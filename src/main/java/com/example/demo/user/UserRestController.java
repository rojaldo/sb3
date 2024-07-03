package com.example.demo.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/library")
public class UserRestController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public ResponseEntity<Iterable<UserDto>> getMethodName() {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<IResponseDto> addUSer(@RequestBody @Validated UserDto userDto) {
        UserDto result = userService.addUSer(userDto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorUserDto.builder().message("User already exists: email repeated").query(userDto).build());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    
}
