package com.spintech.testtask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spintech.testtask.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException userNotFoundException) {
        log.error("User not found. The error message is: {}", userNotFoundException.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFoundException.getLocalizedMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleAllExceptions(Throwable throwable) {
        log.error("Something went wrong!", throwable);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
