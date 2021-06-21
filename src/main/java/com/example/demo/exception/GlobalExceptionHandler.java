package com.example.demo.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ItemNotFoundException.class)
    public final ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException ex, WebRequest request) {
		 List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
        ErrorDetails error = new ErrorDetails(new Date(),request.getDescription(false) ,details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
	

	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		 List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
        ErrorDetails error = new ErrorDetails(new Date(), request.getDescription(false),details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
	
}
