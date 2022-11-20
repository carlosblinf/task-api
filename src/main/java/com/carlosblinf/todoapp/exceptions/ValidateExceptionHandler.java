package com.carlosblinf.todoapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ValidateExceptionHandler {

    public ValidateExceptionHandler() {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<Object> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put("field", err.getField());
                    error.put("message", err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
//        Map<String, String> errors = new HashMap<>();
//        System.out.println(ex.getBindingResult().getAllErrors());
//
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
        return errors;
    }
}
