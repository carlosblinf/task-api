package com.carlosblinf.todoapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ApiResponse {
    private Map<String, Object> response;

    public ApiResponse() {
        this.response = new HashMap<>();
    }

    private ResponseEntity<?> successResponse(String message, Object data, HttpStatus code){
        this.response.put("message", message);
        this.response.put("data", data);
        return new ResponseEntity(this.response, code);
    }

    public ResponseEntity<?> okResponse(String message, Object data){
        return this.successResponse(message, data, HttpStatus.OK);
    }

    public ResponseEntity<?> createResponse(String message, Object data){
        return this.successResponse(message, data, HttpStatus.CREATED);
    }

    public ResponseEntity<?> errorResponse(BindingResult result, HttpStatus code){
        List<Object> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put("field", err.getField());
                    error.put("message", err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        this.response.put("errors", errors);
        return new ResponseEntity(this.response, code);
    }

}
