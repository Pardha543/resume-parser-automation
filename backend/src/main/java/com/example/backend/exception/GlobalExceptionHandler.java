package com.example.backend.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {
	        return ResponseEntity.badRequest().body(Map.of(
	                "error", ex.getMessage()
	        ));
	    }

}
