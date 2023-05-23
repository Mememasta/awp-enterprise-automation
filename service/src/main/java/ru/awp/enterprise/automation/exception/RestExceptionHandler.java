package ru.awp.enterprise.automation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    ResponseEntity<Object> clientNotFound() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    ResponseEntity<Object> clientAlreadyExist() {
        return new ResponseEntity<>(HttpStatus.FOUND);
    }
}
