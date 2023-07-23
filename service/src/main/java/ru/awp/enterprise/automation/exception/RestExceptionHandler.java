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

    @ExceptionHandler(NotFoundProductException.class)
    ResponseEntity<Object> productNotFound() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(ProductAlreadyExist.class)
    ResponseEntity<Object> productAlreadyExist() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
