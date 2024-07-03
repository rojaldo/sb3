package com.example.demo.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/library")
public class UserRestController {

    @Autowired
    private UserService userService;
    
    @GetMapping(path = "/users")
    public ResponseEntity<Iterable<UserDto>> getMethodName() {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @PostMapping(path = "/users")
    public ResponseEntity<IResponseDto> addUSer(@RequestBody @Validated UserDto userDto) {
        UserDto result = userService.addUSer(userDto);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorUserDto.builder().message("User already exists: email repeated").query(userDto).build());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<IResponseDto> deleteUser(@PathVariable Long id) {
        UserDto result = userService.deleteUser(id);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorUserDto.builder().message("User not found").build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(UserDto.builder().id(id).name(result.getName()).email(result.getEmail()).build());
    }
    
    
}
