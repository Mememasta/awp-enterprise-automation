package ru.awp.enterprise.automation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.awp.enterprise.automation.models.response.ApiError;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    ResponseEntity<Object> clientNotFound(ClientNotFoundException exception) {
        var error = getApiError(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    ResponseEntity<Object> clientAlreadyExist(ClientAlreadyExistsException exception) {
        var error = getApiError(HttpStatus.FOUND, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FOUND);
    }

    @ExceptionHandler(NotFoundProductException.class)
    ResponseEntity<Object> productNotFound(NotFoundProductException exception) {
        var error = getApiError(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ProductAlreadyExist.class)
    ResponseEntity<Object> productAlreadyExist(ProductAlreadyExist exception) {
        var error = getApiError(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private static ApiError getApiError(HttpStatus unauthorized, String exception) {
        return ApiError.builder()
                .timestamp(LocalDateTime.now())
                .code(unauthorized.value())
                .message(exception)
                .build();
    }
    @ExceptionHandler(ProductAlreadyExist.class)
    ResponseEntity<Object> productAlreadyExist() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
