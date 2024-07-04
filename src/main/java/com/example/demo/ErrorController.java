package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(Exception.class)
    //@ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<UserError> handleException(Exception e) {
        log.error("ERROR", e);
        if(e instanceof MethodArgumentNotValidException) {
            return new ResponseEntity<>(UserError.builder().status(400).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }else if (e instanceof NoResourceFoundException) {
            return new ResponseEntity<>(UserError.builder().status(404).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(UserError.builder().status(500).message(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}

class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

