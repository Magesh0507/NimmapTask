package com.assignment.Nimmap.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.Nimmap.model.BaseResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, Object>> errorDetails = new ArrayList<>();
        
        // Collect field-specific errors
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            Map<String, Object> fieldError = new HashMap<>();
            fieldError.put("field", error.getField());
            fieldError.put("rejectedValue", error.getRejectedValue());
            fieldError.put("errorMessage", error.getDefaultMessage());
            errorDetails.add(fieldError);
        }

        // Construct a detailed response
        BaseResponse response = new BaseResponse(
                String.valueOf(HttpStatus.BAD_REQUEST.value()), 
                null, 
                errorDetails, 
                "Validation failed for one or more fields."
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
