package com.carlosblinf.todoapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

}
