package com.example.eatgo.controller;

import com.example.eatgo.domain.RestaurantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorAdvice {


    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity handleException(RestaurantNotFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
