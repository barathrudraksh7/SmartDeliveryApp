package com.smartdelivery.orders.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    
    //validationExceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidMethodArgumentException(MethodArgumentNotValidException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    //run time exception
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRunTimeException(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    //Custom Exception response building
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleInvalidOrderException(CustomException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleUnAuthException(BadCredentialsException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(buildErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED)); 
    }

    //generic method to build error response
    private Map<String, Object> buildErrorResponse(String message, HttpStatus status){
 
      Map<String, Object> error = new HashMap<>();

        error.put("timestamp",LocalDateTime.now());
        error.put("errorMessage",message);
        error.put("status",status);

        return error;
    }
}
