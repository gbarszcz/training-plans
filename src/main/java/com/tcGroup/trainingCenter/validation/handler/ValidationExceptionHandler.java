package com.tcGroup.trainingCenter.validation.handler;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tcGroup.trainingCenter.validation.response.ValidationErrorResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidationErrorResponse> errors = new LinkedList<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            errors.add(new ValidationErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errors", errors);
        
        return new ResponseEntity<>(body, status);
    }
}